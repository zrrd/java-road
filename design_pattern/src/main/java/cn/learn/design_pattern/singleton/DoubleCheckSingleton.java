package cn.learn.design_pattern.singleton;

/**
 * 双重校验单例
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class DoubleCheckSingleton {

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
