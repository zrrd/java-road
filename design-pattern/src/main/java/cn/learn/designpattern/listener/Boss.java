package cn.learn.designpattern.listener;

import lombok.Data;

/**
 * @author shaoyijiong
 * @date 2019/4/1
 */
@Data
public class Boss {

  /**
   * bose 的血量
   */
  private int blood;

  private String name;

  /**
   * 扣血时boss的行为每个boss不一样
   */
  private BloodListener bloodListener;

  /**
   * 死了每个boss爆的装备也不一样
   */
  private KillListener killListener;

  public Boss(int blood, String name, BloodListener bloodListener,
      KillListener killListener) {
    this.blood = blood;
    this.name = name;
    this.bloodListener = bloodListener;
    this.killListener = killListener;
  }
}
