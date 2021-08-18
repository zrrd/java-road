package cn.learn.test.service;

import cn.learn.test.bean.Game;
import cn.learn.test.manager.GameManager;
import org.springframework.stereotype.Component;

/**
 * @author shaoyijiong
 * @date 2021/8/18
 */
@Component
public class GameService {

  private final GameManager gameManager;

  public GameService(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  public Game getGame(Integer id) {
    return gameManager.getGame(id);
  }

  public Game insertGame(Game game) {
    return gameManager.insertGame(game);
  }

  public Integer deleteGame(Integer id) {
    return gameManager.deleteGame(id);
  }
}
