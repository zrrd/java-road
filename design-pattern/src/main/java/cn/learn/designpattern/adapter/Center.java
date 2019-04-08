package cn.learn.designpattern.adapter;

import lombok.extern.slf4j.Slf4j;

/**
 * 美国前锋(会讲英文)
 *
 * @author shaoyijiong
 * @date 2019/4/8
 */
@Slf4j
public class Center extends Player {

  public Center(String name) {
    super(name);
  }

  @Override
  public void attack() {
    log.info("中锋进攻{}", name);
  }

  @Override
  public void defense() {
    log.info("中锋防守{}", name);
  }
}
