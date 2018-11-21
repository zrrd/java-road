package cn.learn.designpattern.singleton;

/**
 * 懒汉式单例 线程不安全
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class SafeLazySingleton {

  private static SafeLazySingleton instance;

  private SafeLazySingleton() {
  }

  /**
   * 加了synchronized关键字 防止了线程安全问题
   * 但是每次访问这个方法都导致线程之间锁的竞争
   */
  public synchronized static SafeLazySingleton getInstance() {
    //判断是否为空的时候可能会导致线程安全问题
    if (instance == null) {
      instance = new SafeLazySingleton();
    }
    return instance;
  }
}
