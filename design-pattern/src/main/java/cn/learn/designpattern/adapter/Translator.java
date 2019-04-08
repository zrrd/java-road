package cn.learn.designpattern.adapter;

/**
 * 翻译类
 *
 * @author shaoyijiong
 * @date 2019/4/8
 */
public class Translator extends Player {

  /**
   * 内部持有一个外籍中锋
   */
  private ForeignCenter wjzf = new ForeignCenter();

  public Translator(String name) {
    super(name);
    wjzf.setName(name);
  }

  /**
   * 将attack 翻译为 进攻
   */
  @Override
  public void attack() {
    wjzf.jingGong();
  }

  /**
   * 将defense 翻译为 防守
   */
  @Override
  public void defense() {
    wjzf.fangShou();
  }
}
