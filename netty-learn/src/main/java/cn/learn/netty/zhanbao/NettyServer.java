package cn.learn.netty.zhanbao;

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
         * 实际读到的数据
         * Fri Apr 12 19:39:29 CST 2024: 服务端读到数据 -> 你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你�
         * Fri Apr 12 19:39:29 CST 2024: 服务端读到数据 -> �� 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。你好 欢迎来到中国。
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            ByteBuf byteBuf = (ByteBuf) msg;

            System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
        }

    }
}