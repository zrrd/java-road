package cn.learn.jvm.oom;

/**
 * @author 邵益炯
 * @date 2018/9/26
 */
public class OOMObject {

  int anInt;
  byte[] b;

  public OOMObject() {
    anInt = 10;
    b = new byte[1024 * 1024];
  }
}
