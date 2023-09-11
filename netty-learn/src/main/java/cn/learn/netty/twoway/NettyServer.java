package cn.learn.netty.twoway;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
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
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
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
            System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));

            // 回复数据到客户端
            System.out.println(new Date() + ": 服务端写出数据");
            ByteBuf out = getByteBuf(ctx);
            ctx.channel().writeAndFlush(out);
        }


        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
            byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(StandardCharsets.UTF_8);

            ByteBuf buffer = ctx.alloc().buffer();

            buffer.writeBytes(bytes);

            return buffer;
        }
    }
}