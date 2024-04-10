package cn.learn.netty.im.message;

import cn.learn.netty.im.packet.command.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author shaoyijiong
 * @date 2023/9/4
 */
public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap.group(boss, worker).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<NioSocketChannel>() {
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new FirstServerHandler());
            }
        }).bind(8000);
    }

    private static class FirstServerHandler extends ChannelInboundHandlerAdapter {


        /**
         * 收到客户端发送的数据之后被调用
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf byteBuf = (ByteBuf) msg;
            Packet decode = PacketCode.decode(byteBuf);
            CommandEnum commandEnum = decode.getCommandEnum();
            switch (commandEnum) {
                case login:
                    boolean login = login((LoginRequest) decode.getDate());
                    if (login) {
                        ctx.channel().attr(Attributes.LOGIN).set(true);
                    }
                    LoginResponse loginResponse = LoginResponse.newBuilder().setSuccess(login).build();
                    ByteBuf loginResponseByte = PacketCode.encode(CommandEnum.login, loginResponse);
                    ctx.channel().writeAndFlush(loginResponseByte);
                    break;
                case message:
                    // 判断是否已经登录
                    if (ctx.channel().attr(Attributes.LOGIN) == null) {
                        MessageResponse messageResponse = MessageResponse.newBuilder().setMessage("未登录").build();
                        ByteBuf encode = PacketCode.encode(CommandEnum.message_rsp, messageResponse);
                        ctx.channel().writeAndFlush(encode);
                    } else {
                        Packet message = PacketCode.decode(byteBuf);

                        MessageResponse messageResponse = MessageResponse.newBuilder().setMessage("收到").build();
                        ByteBuf encode = PacketCode.encode(CommandEnum.message_rsp, messageResponse);
                        ctx.channel().writeAndFlush(encode);
                    }
                default:
                    break;
            }
        }


        private boolean login(LoginRequest loginRequest) {
            // 校验账户密码
            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();
            if (!"zhangsan".equals(username) || !"123456".equals(password)) {
                System.out.println(new Date() + ": 客户端登录失败");
                return false;
            } else {
                System.out.println(new Date() + ": 客户端登录成功");
                return true;
            }
        }

    }
}