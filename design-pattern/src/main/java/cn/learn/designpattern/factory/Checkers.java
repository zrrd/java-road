package cn.learn.designpattern.factory;

/**
 * 跳棋
 *
 * @author shaoyijiong
 * @date 2019/4/1
 */
public class Checkers implements Game {

  private Checkers() {
  }

  private int moves = 0;
  private static final int MOVES = 3;

  @Override
  public boolean move() {
    System.out.println("Checkers move" + moves);
    return ++moves != MOVES;
  }

  /**
   * 通过匿名内部类的方式多谢 factory的实现类
   */
  public static GameFactory gameFactory = new GameFactory() {
    @Override
    public Game getGame() {
      return new Checkers();
    }
  };
}
