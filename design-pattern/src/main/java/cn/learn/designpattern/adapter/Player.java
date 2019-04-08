package cn.learn.designpattern.adapter;

/**
 * 篮球
 *
 * @author shaoyijiong
 * @date 2019/4/8
 */
public abstract class Player {

  protected String name;

  public Player(String name) {
    this.name = name;
  }

  /**
   * 进攻接口
   */
  public abstract void attack();

  /**
   * 防御接口
   */
  public abstract void defense();
}
