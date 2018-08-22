package cn.learn.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author 邵益炯
 * @date 2018/8/22
 */
public class SockerChannelTest {

  static class Client implements Runnable {

    @Override
    public void run() {
      try (SocketChannel socketChannel = SocketChannel.open()) {
        //如果我们设置了一个SocketChannel是非阻塞的，那么调用connect()后，方法会在链接建立前就直接返回。
        //为了检查当前链接是否建立成功，我们可以调用finishConnect()
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 2333));
        while (socketChannel.finishConnect()) {
          ByteBuffer buf = ByteBuffer.allocate(48);
          //向SocketChannel写数据
          String newData = "New String to write to file..." + System.currentTimeMillis();
          buf.put(newData.getBytes());
          while (buf.hasRemaining()) {
            //每次写48个字节
            socketChannel.write(buf);
          }
          //发送停止请求
          String end = "end";
          buf.clear();
          buf.put(end.getBytes());
          while (buf.hasRemaining()) {
            //每次写48个字节
            socketChannel.write(buf);
          }
          break;
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  static class Server implements Runnable {

    static byte[] bytes = new byte[1024];

    @Override
    public void run() {
      try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
        serverSocketChannel.configureBlocking(false);
        while (true) {
          SocketChannel socketChannel = serverSocketChannel.accept();
          ByteBuffer buf = ByteBuffer.allocate(48);
          int bytesRead = socketChannel.read(buf);
          byte[] b = new byte[10];
          while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
              buf.get(b);
              bytes = ArrayUtils.addAll(bytes, b);
            }
            buf.clear();
            bytesRead = socketChannel.read(buf);
          }
          //判断是否停止
          String str = new String(bytes);
          if (str.contains("end")) {
            System.out.println(str);
            break;
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.submit(new Server());
    pool.submit(new Client());
  }

}
