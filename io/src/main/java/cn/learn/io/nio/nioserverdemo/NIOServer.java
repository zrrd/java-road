package cn.learn.io.nio.nioserverdemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 通过多路复用的方式来实现server
 *
 * @author 邵益炯
 * @date 2018/8/27
 */
public class NIOServer extends Thread {

  @Override
  public void run() {
    try (Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open()) {
      serverSocket.bind(new InetSocketAddress("127.0.0.1",8888));
      System.out.println(serverSocket.getLocalAddress());
      //表明非阻塞
      serverSocket.configureBlocking(false);
      //注册到select 并说明关注点
      serverSocket.register(selector, SelectionKey.OP_ACCEPT);
      while (true) {
        //阻塞等待就绪的Channel,这是关键的之一
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          SelectionKey selectionKey = iterator.next();
          sayHelloWorld((ServerSocketChannel) selectionKey.channel());
          iterator.remove();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void sayHelloWorld(ServerSocketChannel server) throws IOException {
    try (SocketChannel clint = server.accept()) {
      clint.write(Charset.defaultCharset().encode("你好"));
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    NIOServer server = new NIOServer();
    server.start();
    Thread.sleep(5000);
    try (SocketChannel client = SocketChannel.open()) {
      client.connect(new InetSocketAddress("127.0.0.1", 8888));
      while (client.finishConnect()) {
        byte[] b = new byte[10];
        byte[] bytes = new byte[1024];
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = client.read(buf);
        while (bytesRead != -1) {
          buf.flip();
          //每次最多读10个字节
          while (buf.remaining()>10) {
            buf.get(b);
            bytes = ArrayUtils.addAll(bytes, b);
          }
          buf.clear();
          bytesRead = client.read(buf);
        }
        System.out.println(new String(bytes));
      }

    }
  }

}
