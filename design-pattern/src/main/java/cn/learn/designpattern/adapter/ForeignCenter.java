package cn.learn.designpattern.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 外籍中锋 不懂中文
 *
 * @author shaoyijiong
 * @date 2019/4/8
 */
@Slf4j
public class ForeignCenter {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * 外籍中锋只懂 中文的进攻与防守
   */
  public void jingGong() {
    log.info("进攻{}", name);
  }

  public void fangShou() {
    log.info("防守{}", name);
  }
}
