package cn.learn.designpattern.adapter;

/**
 * 测试
 *
 * @author shaoyijiong
 * @date 2019/4/8
 */
public class Go {

  public static void main(String[] args) {
    Player a = new Center("奥尼尔");
    Player ym = new Translator("姚明");
    ym.attack();
    ym.defense();
  }
}
