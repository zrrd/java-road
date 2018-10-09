package cn.learn.scoket.socketgo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * socket server
 *
 * @author shaoyijiong
 * @date 2018/7/13
 */
@Component
public class Server implements Runnable {

  @Value("${myserver.port}")
  private int port;

  @Override
  public void run() {
    //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
    ExecutorService threadPool = Executors.newFixedThreadPool(100);
    try {
      File file = FileUtils.getFile("server.txt");
      if (!file.exists()) {
        file.createNewFile();
      }
      while (true) {
        System.out.println("等待连接");
        //等待连接
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        Runnable r = () -> {
          try {
            //获得输入流
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            System.out.println("写入文件");
            while ((len = inputStream.read(bytes)) != -1) {
              sb.append(new String(bytes, 0, len, "UTF-8"));
              FileUtils.writeStringToFile(file, sb.toString(), "UTF-8", true);
            }
            System.out.println(sb);
            System.out.println("关闭链接");
            inputStream.close();
            socket.close();
            serverSocket.close();
          } catch (IOException e) {
            e.printStackTrace();
          }

        };
        threadPool.submit(r);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
