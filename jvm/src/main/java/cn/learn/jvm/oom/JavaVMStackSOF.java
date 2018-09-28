package cn.learn.jvm.oom;

/**
 * 栈溢出
 *
 * @author 邵益炯
 * @date 2018/9/27
 */
public class JavaVMStackSOF {

  private int stackLength = 1;

  @SuppressWarnings("all")
  public void stackLeak() {
    stackLength++;
    stackLeak();
  }

  public static void main(String[] args) {
    JavaVMStackSOF stackOver = new JavaVMStackSOF();
    try {
      stackOver.stackLeak();
    } catch (Throwable e) {
      System.out.println(stackOver.stackLength);
      throw e;
    }
  }
}
