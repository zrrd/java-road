package cn.learn.jvm.oom;

import java.util.HashMap;
import java.util.Map;

/**
 * 堆溢出
 * Vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author 邵益炯
 * @date 2018/9/26
 */
public class HeapDumpOOM {

  @SuppressWarnings("all")
  public static void main(String[] args) throws InterruptedException {
    System.out.println(args.length);
    Map<String, OOMObject> list = new HashMap<>();
    for (int i=1; ;i++ ) {
      Thread.sleep(1);
      list.put(i+"", new OOMObject());
    }
  }
}
