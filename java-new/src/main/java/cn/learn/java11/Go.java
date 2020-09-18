package cn.learn.java11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.stream.Stream;
import lombok.SneakyThrows;

/**
 * @author shaoyijiong
 * @date 2020/9/18
 */
public class Go {

  private static void strings() {
    // 判断是否为空白
    boolean blank = " ".isBlank();
    // 去除首尾空格
    " hello ".strip();
    // 去除尾部空格
    "hello ".stripTrailing();
    // 去除首部空格
    " hello".stripLeading();
    // 复制字符串 javajavajava
    "java".repeat(3);
    // 转行
    Stream<String> lines = "A\nB\n".lines();
  }

  @SneakyThrows
  private static void client() {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.baidu.com")).build();
    HttpClient client = HttpClient.newBuilder().build();
    // 同步
    HttpResponse<String> send = client.send(request, BodyHandlers.ofString());
    System.out.println(send.body());

    // 异步
    client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println);
  }

  public static void main(String[] args) {

  }
}
