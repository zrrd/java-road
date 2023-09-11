package cn.learn.netty.twoway;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoyijiong
 * @date 2023/9/4
 */
public class NettyClient {

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
        }).connect("127.0.0.1",8000).channel();
        TimeUnit.SECONDS.sleep(10);
    }

    private static class FirstClientHandler extends ChannelInboundHandlerAdapter {

        /**
         * 在客户端连接建立成功之后被调用
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.out.println(new Date() + ": 客户端写出数据");

            // 1. 获取数据
            ByteBuf buffer = getByteBuf(ctx);

            // 2. 写数据
            ctx.channel().writeAndFlush(buffer);
        }

        /**
         * 收到服务端数据后调用
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;

            System.out.println(new Date() + ": 客户端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
        }

        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
            // 1. 获取二进制抽象 ByteBuf
            ByteBuf buffer = ctx.alloc().buffer();

            // 2. 准备数据，指定字符串的字符集为 utf-8
            byte[] bytes = "你好，闪电侠!".getBytes(StandardCharsets.UTF_8);

            // 3. 填充数据到 ByteBuf
            buffer.writeBytes(bytes);

            return buffer;
        }
    }

}