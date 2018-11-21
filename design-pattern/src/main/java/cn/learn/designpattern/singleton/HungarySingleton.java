package cn.learn.designpattern.singleton;

/**
 * 饿汉式单例
 *
 * @author 邵益炯
 * @date 2018/10/9
 */
public class HungarySingleton {

  /**
   * 没有懒加载的功能
   */
  private final static HungarySingleton INSTANCE = new HungarySingleton();

  private HungarySingleton() {
  }

  public HungarySingleton getInstance() {
    return INSTANCE;
  }
}
