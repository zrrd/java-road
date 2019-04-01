package cn.learn.designpattern.listener;

/**
 * @author shaoyijiong
 */
public interface BloodListener {

  /**
   * 血量降低时boss的行为 每个boss不一样
   */
  void action();
}
