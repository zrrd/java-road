package cn.learn.design_pattern.singleton;

/**
 * 枚举单例(不太好  可读性差)
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public enum EnumSingleton {
  /**
   * 枚举类型  默认有final static 关键字
   */
  INSTANCE;

  private Singleton singleton;

  EnumSingleton() {
    singleton = new Singleton();
  }

  public Singleton getInstance() {
    return singleton;
  }

  /**
   * 要单例的类
   */
  class Singleton {

  }
}
