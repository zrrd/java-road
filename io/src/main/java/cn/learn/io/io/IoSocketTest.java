package cn.learn.io.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 通过老的io来实现socket编程
 *
 * @author 邵益炯
 * @date 2018/8/21
 */
public class IoSocketTest {

  /**
   * 服务端
   */
  static class Server implements Runnable {

    private static final int PORT = 8089;

    @Override
    public void run() {
      ExecutorService pool = Executors.newCachedThreadPool();
      try (ServerSocket serverSocket = new ServerSocket(PORT)) {
        while (true) {
          Socket socket = serverSocket.accept();
          //使用异步的方式处理请求
          Runnable runnable = () -> {
            try (InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream()) {
              //接收客户端的信息
              byte[] b = new byte[10];
              int length;
              byte[] all = new byte[1024];
              while ((length = inputStream.read(b)) != -1) {
                all = ArrayUtils.addAll(all, Arrays.copyOf(b, length));
              }
              System.out.println(new String(all));

              //向客户端发送信息
              outputStream.write("接收到了".getBytes());

            } catch (IOException e) {
              System.out.println(e);
            }
          };
          pool.submit(runnable);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * 客户端
   */
  static class Client implements Runnable {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8089;

    @Override
    public void run() {
      try (Socket socket = new Socket(HOST, PORT);
          OutputStream outputStream = socket.getOutputStream();
          InputStream inputStream = socket.getInputStream()) {
        outputStream.write("客户端发请求了".getBytes());

        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();

        //接收服务端的信息
        byte[] b = new byte[10];
        int length;
        byte[] all = new byte[1024];
        while ((length = inputStream.read(b)) != -1) {
          all = ArrayUtils.addAll(all, Arrays.copyOf(b, length));
        }
        System.out.println("服务器的输出:  " + new String(all));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(5);
    pool.submit(new Server());
    pool.submit(new Client());
    pool.submit(new Client());
    pool.submit(new Client());
    pool.submit(new Client());
    System.out.println("开始了");
  }
}
