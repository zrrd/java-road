package cn.learn.designpattern.singleton;

/**
 * 懒汉式单例 线程不安全
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class LazySingleton {

  private static LazySingleton instance;

  private LazySingleton() {
  }

  public static LazySingleton getInstance() {
    //判断是否为空的时候可能会导致线程安全问题
    if (instance == null) {
      instance = new LazySingleton();
    }
    return instance;
  }
}
