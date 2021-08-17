package cn.learn.java9;

/**
 * @author shaoyijiong
 * @date 2020/9/17
 */
public interface Phone {

  void call();

  default void callHello() {
    System.out.println("hellow");
  }

  /**
   * 相比jdk8新增了私有方法
   */
  //private void callWorld() {
  //  System.out.println("world");
  //}
}
