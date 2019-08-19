package cn.learn.utils;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;

@SuppressWarnings("Duplicates")
public class GYSAPoolTest {


  private static final String testUrl = "http://36.110.85.15:9600/callcenter";

  private static final String prdUrl = "http://36.110.85.12:8686/callcenter";
  // 平台的私钥
  private static final String privateKey = "03ca8a8152694ad384556e5c46303c14";
  // 平台的公钥
  private static final String publicKey = "WG2UK63A4I1SVD0Q";
  // 手机呼叫能力
  private static final String bindingBackTex = "/api/bindingBackTex";
  // 带号码池手机呼叫能力
  private static final String bindAXB = "/api/bindAXB";
  // 获取小号池能力接口
  private static final String getNumberPool = "/api/getNumberPool";

  /**
   * 获取小号池能力接口
   *
   * @autor tangjinge
   * @since 2019-08-01
   */
  @Test
  public void getNumberPool() throws Exception {
    String ts = (System.currentTimeMillis() / 1000) + "";
    Map<String, String> params = new HashMap<>();
    params.put("publicKey", publicKey);
    params.put("ts", ts);
    //String data = KeySort.getKeySortString(params, "secret", privateKey);
    params.put("secret", privateKey);
    //params.put("sign", MD5.signStr(data));
    callAXBindTest(testUrl + getNumberPool, params);
  }

  @Test
  public void a() {
    String ts = (System.currentTimeMillis() / 1000) + "";
    System.out.println(ts);
  }

  /**
   * 带号码池得手机呼叫能力
   *
   * @autor tangjinge
   * @since 2019-08-01
   */
  @Test
  public void newBindingBackTex() throws Exception {
    // 测试环境加密号码：8f04970869b831bce050007f01004bbb   8f04970869ba31bce050007f01004bbb  8f04970869bc31bce050007f01004bbb
    String ts = (System.currentTimeMillis() / 1000) + "";
    String groupId = "1001";
    String calledId = "17826808394";
    //String phoneId = "864CC70D6289549CE053080B080A548B";//曹全龙
    String phoneId = "8f04970869bc31bce050007f01004bbb";//余秋萍
    //String phoneId = "864CC70D628D549CE053080B080A548B";// 宣卢斌
    //String phoneId = "864CC70D62A1549CE053080B080A548B";//余秋萍

    Map<String, String> params = new HashMap<>();
    params.put("calledId", calledId);
    params.put("groupId", groupId);
    params.put("phoneId", phoneId);
    params.put("publicKey", publicKey);
    params.put("timeDuration", "10");
    params.put("ts", ts.trim());
    params.put("type", "0");
    String data1 = getKeySortString(params, "secret", privateKey);
    String data2 = supplySignParam(params, privateKey);
    System.out.println(data1 + "\n" + data2);
    params.put("secret", privateKey);
    String sign1 = Md5Util.encrypt32(data2);
    String sign2 = MD5.signStr(data2);
    params.put("sign", sign1);
    callAXBindTest(testUrl + bindAXB, params);
  }

  private static String supplySignParam(Map<String, String> params, String secret) {
    StringBuilder sb = new StringBuilder();
    sb = sb.append("calledId=").append(params.get("calledId"))
        .append("&groupId=").append(params.get("groupId"))
        .append("&phoneId=").append(params.get("phoneId"))
        .append("&publicKey=").append(params.get("publicKey"))
        .append("&timeDuration=").append(params.get("timeDuration"))
        .append("&ts=").append(params.get("ts"));
    if (params.get("type") != null) {
      sb = sb.append("&type=").append(params.get("type"));
    }
    sb = sb.append("&secret=").append(secret);
    return sb.toString();
  }


  public static String getKeySortString(Map<String, String> map, String endKey, String endValue) {
    String data = "";
    if (map == null && map.size() == 0) {
      return null;
    }
    List<Entry<String, String>> list = new ArrayList<Entry<String, String>>(map.entrySet());
    Collections.sort(list, new Comparator<Entry<String, String>>() {
      public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
        return o1.getKey().compareTo(o2.getKey());
      }
    });
    for (int i = 0; i < list.size(); i++) {
      data += String.valueOf(list.get(i)) + "&";
    }
    return data + endKey + "=" + endValue;
  }

  /**
   * 步骤：step-0 手机呼叫能力
   */
  @Test
  public void bindingBackTex() throws Exception {
    // ts时间戳
    String ts = (System.currentTimeMillis() / 1000) + "";
    String calledId = "13575799434";
    String phoneId = "864CC70D628B549CE053080B080A548B";
//        String phoneId = "864CC70D6289549CE053080B080A548B";//曹全龙
//        String phoneId = "864CC70D628B549CE053080B080A548B";//余秋萍
//        String phoneId = "864CC70D628D549CE053080B080A548B";// 宣卢斌
    Map<String, String> params = new HashMap();
    params.put("calledId", calledId);
    params.put("phoneId", phoneId);
    params.put("publicKey", publicKey);
    params.put("timeDuration", "30");
    params.put("ts", ts.trim());
    params.put("type", "0");
    //String data = KeySort.getKeySortString(params, "secret", privateKey);
    params.put("secret", privateKey);
    //params.put("sign", MD5.signStr(data));
    callAXBindTest(bindingBackTex, params);
  }


  /**
   * http 请求方法。
   **/

  public void callAXBindTest(String url, Map<String, String> unSignMap) throws Exception {
    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost post = new HttpPost(url);
    post.setHeader("Accept", "application/json;charset=utf-8");
    post.setHeader("Content-Type", "application/json;charset=utf-8");
    if (unSignMap != null || unSignMap.size() > 0) {
      JSONObject content = new JSONObject();
      for (Map.Entry<String, String> entry : unSignMap.entrySet()) {
        content.put(entry.getKey(), entry.getValue());
      }
      StringEntity entity = new StringEntity(content.toJSONString());
      entity.setContentEncoding("utf-8");
      post.setEntity(entity);
    }
    CloseableHttpResponse response = client.execute(post);
    BufferedReader buffer = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
    String res = null;
    while ((res = buffer.readLine()) != null) {
      System.out.println(res);
    }
  }


}
