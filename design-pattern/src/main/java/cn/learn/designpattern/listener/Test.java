package cn.learn.designpattern.listener;

/**
 * @author shaoyijiong
 * @date 2019/4/1
 */
public class Test {

  public static void main(String[] args) {
    Boss boss = new Boss(10, "大魔王", new BloodListener() {
      @Override
      public void action() {
        System.out.println("大魔王掉血了");
      }
    }, new KillListener() {
      @Override
      public void action() {
        System.out.println("大魔王掉了一把黄金剑");
      }
    });
    new KillBoss(boss).kill();
  }
}
