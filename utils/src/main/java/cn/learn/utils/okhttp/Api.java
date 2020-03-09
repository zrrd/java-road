package cn.learn.utils.okhttp;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作为测试
 *
 * @author shaoyijiong
 * @date 2020/3/4
 */
@RequestMapping("api")
@RestController
public class Api {

  private static final String R = "hello world";

  /**
   * get 请求带路径参数
   */
  @GetMapping("a")
  public Resp<String> getApi(String param1, Integer param2) {
    return Resp.success(R);
  }

  /**
   * post 请求  Content-Type:application/x-www-form-urlencoded
   */
  @PostMapping("b")
  public Resp<String> postFormApi(String param1, Integer param2) {
    return Resp.success(R);
  }

  /**
   * json 请求    Content-Type:application/json
   */
  @RequestMapping("c")
  public Resp<String> postJsonApi(@RequestBody List<String> list) {
    return Resp.success(R);
  }

  @RequestMapping("d")
  public Resp<String> simpleApi(@RequestBody List<String> list) {
    return Resp.success(R);
  }
}
