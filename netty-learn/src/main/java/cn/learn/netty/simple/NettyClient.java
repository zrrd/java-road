package cn.learn.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @author shaoyijiong
 * @date 2023/8/24
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new StringEncoder());
            }
        });

        Channel channel = bootstrap.connect("127.0.0.1", 8000)
                // 是否链接成功 可以用来实现自动重连
                .addListener(future -> {
                    if (future.isSuccess()) {
                        System.out.println("连接成功!");
                    } else {
                        System.err.println("连接失败!");
                    }
                }).channel();
        //option() 方法可以给连接设置一些 TCP 底层相关的属性，比如上面，我们设置了三种 TCP 属性，其中
        //ChannelOption.CONNECT_TIMEOUT_MILLIS 表示连接的超时时间，超过这个时间还是建立不上的话则代表连接失败
        //ChannelOption.SO_KEEPALIVE 表示是否开启 TCP 底层心跳机制，true 为开启
        //ChannelOption.TCP_NODELAY 表示是否开始 Nagle 算法，true 表示关闭，false 表示开启，通俗地说，如果要求高实时性，有数据发送时就马上发送，就设置为 true 关闭，如果需要减少发送次数减少网络交互，就设置为 false 开启
        bootstrap
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true);
        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
