package cn.learn.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量池内存溢出
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * @author 邵益炯
 * @date 2018/9/27
 */
public class RuntomeConstantPoolOOM {

  /**
   * JDK1.6会导致OOM
   * JDK1.7+不会
   */
  @SuppressWarnings("all")
  public static void main(String[] args) {
    //使用List保持常量池的引用 避免GC
    List<String> list = new ArrayList<>();
    int i = 0;
    while (true) {
      list.add(String.valueOf(i++).intern());
    }
  }
}
