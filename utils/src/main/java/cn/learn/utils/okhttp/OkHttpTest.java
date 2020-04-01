package cn.learn.utils.okhttp;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 常用请求方式 其他详见官网 https://square.github.io/okhttp/recipes/
 *
 * @author shaoyijiong
 * @date 2020/3/4
 */
@SuppressWarnings("all")
public class OkHttpTest {

  OkHttpClient client = new OkHttpClient();
  public static final String URI = "http://127.0.0.1:8080/";

  public static void main(String[] args) throws IOException {
    new OkHttpTest().eventListener();
  }


  public void clientBuild() {
    // 通过build创建client 可以设置超时时间
    new OkHttpClient.Builder().build();
  }

  /**
   * get 请求
   */
  public void getExample() throws IOException {
    String api = URI + "api/a";
    // 封装路径
    HttpUrl httpUrl = HttpUrl
        // 解析一个路径 并且封装到 HttpUrl 内部
        .parse(api).newBuilder()
        // 添加一个路径参数
        .addQueryParameter("param1", "a")
        .addQueryParameter("param2", "1").build();

    Request request = new Request.Builder()
        // 请求路径 可以为自带的 HttpUrl 也可以用String
        .url(httpUrl)
        .build();

    try (Response response = client.newCall(request).execute()) {
      // http code码 200
      response.code();
      // http 返回body
      response.body();
      // 返回值json {"code":200,"data":"hello world","msg":"ok"}
      response.body().string();

      // 请求头,返回
      // Content-Type: application/json;charset=UTF-8
      // Transfer-Encoding: chunked
      // Date: Wed, 04 Mar 2020 08:45:09 GMT
      response.headers().toString();

      response.networkResponse();

    }
  }

  /**
   * post 表单请求 1
   */
  public void postFormExample() throws IOException {
    String api = URI + "api/b";
    // 默认请求头 Content-Type:application/x-www-form-urlencoded
    FormBody formBody = new FormBody.Builder().add("param1", "a").add("param2", "1").build();
    Request request = new Request.Builder().url(api).post(formBody).build();
    try (Response response = client.newCall(request).execute()) {
      response.body().string();
    }
  }

  /**
   * post 表单请求 2
   */
  public void postMultiExample() throws IOException {
    String api = URI + "api/b";
    // 默认请求头 Content-Type:multipart/mixed
    MultipartBody formBody = new MultipartBody.Builder().addFormDataPart("param1", "a").addFormDataPart("param2", "1")
        .build();
    Request request = new Request.Builder().url(api).post(formBody).build();
    try (Response response = client.newCall(request).execute()) {
      response.body().string();
    }
  }

  /**
   * post json请求
   */
  public void postJsonExample() throws IOException {
    String api = URI + "api/c";
    MediaType json = MediaType.get("application/json; charset=utf-8");
    String s = JSONObject.toJSONString(Lists.newArrayList("a", "b", "c"));
    RequestBody body = RequestBody.create(json, s);
    Request request = new Request.Builder().url(api).post(body).build();
    try (Response response = client.newCall(request).execute()) {
      response.body().string();
    }
  }

  /**
   * 打印请求生命周期
   */
  class PrintingEventListener extends EventListener {

    private long callStartNanos;

    private void printEvent(String name) {
      long nowNanos = System.nanoTime();
      if ("callStart".equals(name)) {
        callStartNanos = nowNanos;
      }
      long elapsedNanos = nowNanos - callStartNanos;
      System.out.printf("%.3f %s%n", elapsedNanos / 1000000000d, name);
    }

    @Override
    public void callStart(Call call) {
      printEvent("callStart");
    }

    @Override
    public void callEnd(Call call) {
      printEvent("callEnd");
    }

    @Override
    public void dnsStart(Call call, String domainName) {
      printEvent("dnsStart");
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
      printEvent("dnsEnd");
    }

    // 还有很多其他的请求 , 详见父类 EventListener
  }

  /**
   * 一次请求的生命周期,有很多事件
   *
   * @link https://square.github.io/okhttp/events/
   */
  public void eventListener() throws IOException {
    // 创建一个有监听器的client
    OkHttpClient client = new OkHttpClient.Builder().eventListener(new PrintingEventListener()).build();
    Request request = new Request.Builder()
        .url(URI + "api/d")
        .build();

    System.out.println("REQUEST 1 (new connection)");
    try (Response response = client.newCall(request).execute()) {
      // Consume and discard the response body.
      response.body().source().readByteString();
    }

    System.out.println("REQUEST 2 (pooled connection)");
    try (Response response = client.newCall(request).execute()) {
      // Consume and discard the response body.
      response.body().source().readByteString();
    }
  }

  /**
   * <pre>
   * 自定义一个拦截器 继承父拦截器
   * 这里只是打印日志 , 也可以改写请求和响应
   * </pre>
   */
  @Slf4j
  static class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
      // 获取该次请求的request
      Request request = chain.request();
      long t1 = System.nanoTime();
      log.info(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
      // 实际请求response
      Response response = chain.proceed(request);
      long t2 = System.nanoTime();
      log.info(String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d,
          response.headers()));
      return response;
    }
  }

  /**
   * Interceptors
   */
  public void interceptors() throws IOException {
    // 添加拦截器
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).build();
    client.newCall(new Request.Builder().url(URI + "api/d").build()).execute();
  }

  /**
   * <pre>
   *   其他功能 https://square.github.io/okhttp/recipes/
   *   1. post 请求一个流
   *   2. post 请求一个文件
   *   3. post 请求multipart request
   *   4. 响应缓存
   *   5. 单个请求配置
   *   6. authentication 自动验证重试
   * </pre>
   */
}
