package cn.learn.jvm.oom;

/**
 * 通过创建线程来使栈内存溢出.
 * -Xss 来现在虚拟机栈大小
 * @author 邵益炯
 * @date 2018/9/27
 */
public class JavaVMStackOOM {

  @SuppressWarnings("all")
  public static void main(String[] args) {
    while (true) {
      Runnable runnable = () -> {
      };
      Thread thread = new Thread(runnable);
      thread.start();
    }
  }
}
