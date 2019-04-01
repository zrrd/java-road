package cn.learn.designpattern.listener;

/**
 * 打boss类
 *
 * @author shaoyijiong
 * @date 2019/4/1
 */
public class KillBoss {

  private Boss boss;

  public KillBoss(Boss boss) {
    this.boss = boss;
  }

  private void hit() {
    boss.setBlood(boss.getBlood() - 1);
    boss.getBloodListener().action();
  }

  public void kill() {
    while (boss.getBlood() > 0) {
      hit();
    }
    System.out.println("boss 死了 爆了装备");
    boss.getKillListener().action();
  }

}
