package cn.learn.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author shaoyijiong
 * @date 2023/8/24
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // boss 对应 IOServer.java 中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // worker 对应 IOClient.java 中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                // boos用于监听线程，worker用于处理每一条连接数据的读写线程组
                .group(boss, worker)
                // 指定IO模型
                .channel(NioServerSocketChannel.class)
                // 每条链接的数据读写业务逻辑处理
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                }).bind(8000)
                // 监听绑定端口是否成功 可以实现自动绑定递增端口的功能
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    public void operationComplete(Future<? super Void> future) {
                        if (future.isSuccess()) {
                            System.out.println("端口绑定成功!");
                        } else {
                            System.err.println("端口绑定失败!");
                        }
                    }

                });
        // 用于指定在服务端启动过程中的一些逻辑
        serverBootstrap.handler(new ChannelInitializer<NioServerSocketChannel>() {
            protected void initChannel(NioServerSocketChannel ch) {
                System.out.println("服务端启动中");
            }
        });
        // attr()方法可以给服务端的 channel，也就是NioServerSocketChannel指定一些自定义属性，然后我们可以通过channel.attr()取出这个属性，比如，上面的代码我们指定我们服务端channel的一个serverName属性，属性值为nettyServer，其实说白了就是给NioServerSocketChannel维护一个map而已，通常情况下，我们也用不上这个方法。
        // 那么，当然，除了可以给服务端 channel NioServerSocketChannel指定一些自定义属性之外，我们还可以给每一条连接指定自定义属性
        serverBootstrap.attr(AttributeKey.newInstance("serverName"), "nettyServer");
        // childAttr可以给每一条连接指定自定义属性，然后后续我们可以通过channel.attr()取出该属性。
        serverBootstrap.childAttr(AttributeKey.newInstance("clientKey"), "clientValue");
        // childOption()可以给每条连接设置一些TCP底层相关的属性，比如上面，我们设置了两种TCP属性，其中
        // ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开启
        // ChannelOption.TCP_NODELAY表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.TCP_NODELAY, true);
        // 除了给每个连接设置这一系列属性之外，我们还可以给服务端channel设置一些属性，最常见的就是so_backlog，如下设置
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);



    }
}

