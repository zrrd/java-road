package cn.learn.netty.im.message;

import cn.learn.netty.im.packet.command.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoyijiong
 * @date 2023/9/4
 */
public class NettyClient {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        // 简单的netty客户端
        Bootstrap bootstrap = new Bootstrap();
        // 事件循环组
        NioEventLoopGroup group = new NioEventLoopGroup();
        // 配置
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline()
                        // 添加处理器
                        .addLast(new FirstClientHandler());
            }
        }).connect("127.0.0.1", 8000).channel();
        TimeUnit.SECONDS.sleep(10);
    }

    private static class FirstClientHandler extends ChannelInboundHandlerAdapter {

        /**
         * 在客户端连接建立成功之后被调用
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            login(ctx);
        }

        private void login(ChannelHandlerContext ctx) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入您的账户：");
            String username = scanner.nextLine();
            System.out.println("请输入您的密码：");
            String password = scanner.nextLine();

            LoginRequest loginRequest = LoginRequest.newBuilder().setUsername(username).setPassword(password).build();
            // encode
            ByteBuf byteBuf = PacketCode.encode(CommandEnum.login, loginRequest);
            ctx.channel().writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf read = (ByteBuf) msg;
            // decode
            Packet decode = PacketCode.decode(read);
            CommandEnum commandEnum = decode.getCommandEnum();
            if (commandEnum == CommandEnum.message_rsp) {
                MessageResponse messageResponse = (MessageResponse) decode.getDate();
                System.out.println("收到服务端消息：" + messageResponse.getMessage());
                if ("未登录".equals(messageResponse.getMessage())) {
                    login(ctx);
                }
            }
            if (commandEnum == CommandEnum.login_rsp) {
                LoginResponse loginResponse = (LoginResponse) decode.getDate();
                if (loginResponse.getSuccess()) {
                    System.out.println("登录成功");
                } else {
                    System.out.println("登录失败");
                    login(ctx);
                }
            }
            System.out.println("请输入下一句：");
            String next = scanner.nextLine();
            MessageRequest messageRequest = MessageRequest.newBuilder().setMessage(next).build();
            ByteBuf byteBuf = PacketCode.encode(CommandEnum.message, messageRequest);
            ctx.channel().writeAndFlush(byteBuf);
        }
    }
}