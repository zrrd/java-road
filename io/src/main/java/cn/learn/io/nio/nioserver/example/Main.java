package cn.learn.io.nio.nioserver.example;


import cn.learn.io.nio.nioserver.IMessageProcessor;
import cn.learn.io.nio.nioserver.Message;
import cn.learn.io.nio.nioserver.Server;
import cn.learn.io.nio.nioserver.http.HttpMessageReaderFactory;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by jjenkov on 19-10-2015.
 */
public class Main {

  public static void main(String[] args) throws IOException {

    String httpResponse = "HTTP/1.1 200 OK\r\n" +
        "Content-Length: 38\r\n" +
        "Content-Type: text/html\r\n" +
        "\r\n" +
        "<html><body>Hello World!</body></html>";

    byte[] httpResponseBytes = httpResponse.getBytes(StandardCharsets.UTF_8);

    IMessageProcessor messageProcessor = (request, writeProxy) -> {
      System.out.println("Message Received from socket: " + request.socketId);

      Message response = writeProxy.getMessage();
      response.socketId = request.socketId;
      response.writeToMessage(httpResponseBytes);

      writeProxy.enqueue(response);
    };

    Server server = new Server(9999, new HttpMessageReaderFactory(), messageProcessor);

    server.start();

  }


}
