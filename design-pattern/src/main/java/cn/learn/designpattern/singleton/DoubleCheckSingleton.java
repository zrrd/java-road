package cn.learn.designpattern.singleton;

/**
 * 双重校验单例
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class DoubleCheckSingleton {

  /**
   * 需要 volatile 可能语句重排导致问题,导致在没有初始化的时候返回
   */
  private static volatile DoubleCheckSingleton instance;

  private DoubleCheckSingleton() {
  }

  /**
   * 双重校验 有效减少锁竞争  写法不够优雅
   * 懒加载 线程安全
   */
  public static DoubleCheckSingleton getInstance() {
    if (instance == null) {
      synchronized (DoubleCheckSingleton.class) {
        if (instance == null) {
          instance = new DoubleCheckSingleton();
        }
      }
    }
    return instance;
  }

}
