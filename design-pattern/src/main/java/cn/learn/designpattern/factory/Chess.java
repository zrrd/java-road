package cn.learn.designpattern.factory;

/**
 * 西洋象棋
 *
 * @author shaoyijiong
 * @date 2019/4/1
 */
public class Chess implements Game {

  private Chess() {
  }

  private int moves = 0;
  private static final int MOVES = 4;

  @Override
  public boolean move() {
    System.out.println("Chess move" + moves);
    return ++moves != MOVES;
  }

  public static GameFactory gameFactory = new GameFactory() {
    @Override
    public Game getGame() {
      return new Chess();
    }
  };
}
