package cn.learn.designpattern.singleton;

/**
 * 饿汉式单例
 * 类加载到后,就实例化单例,Jvm保证线程安全
 * 简单实用,推荐使用,唯一缺点类装载就完成实例化 (不使用,为啥装载 )
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
