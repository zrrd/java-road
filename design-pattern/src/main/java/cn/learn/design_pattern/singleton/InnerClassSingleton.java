package cn.learn.design_pattern.singleton;

/**
 * 静态内部类
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class InnerClassSingleton {

  private InnerClassSingleton() {
  }

  private static class SingletonHolder {
    private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
  }

  /**
   * 在调用getInstance时才会加载静态内部类
   * 静态内部类的加载不需要依附外部类，在使用时才加载。不过在加载静态内部类的过程中也会加载外部类
   * 懒加载  线程安全
   */
  public InnerClassSingleton getInstance() {
    return SingletonHolder.INSTANCE;
  }
}
