package cn.learn.utils.okhttp;

import lombok.Data;

/**
 * @author shaoyijiong
 * @date 2020/3/4
 */
@Data
public class Resp<T> {

  private Integer code;
  private T data;
  private String msg;


  public static <T> Resp<T> success(T data) {
    return new Resp<>(200, data, "ok");
  }

  public static <T> Resp<T> error(Integer code, T data, String msg) {
    return new Resp<>(code, data, msg);
  }

  private Resp(Integer code, T data, String msg) {
    this.code = code;
    this.data = data;
    this.msg = msg;
  }
}
