package cn.learn.test.manager;

import cn.learn.test.bean.Game;
import cn.learn.test.dao.GameDao;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author shaoyijiong
 * @date 2021/8/18
 */
@Component
public class GameManager {

  private final GameDao gameDao;

  public GameManager(GameDao gameDao) {
    this.gameDao = gameDao;
  }

  public Game getGame(Integer id) {
    return gameDao.getGame(id);
  }

  public Game insertGame(Game game) {
    return gameDao.insertGame(game);
  }

  public Integer deleteGame(@PathVariable Integer id) {
    return gameDao.deleteGame(id);
  }
}
