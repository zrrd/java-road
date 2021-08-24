package cn.learn.test.dao;

import cn.learn.test.bean.Game;
import cn.learn.test.dao.mapper.GameMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author shaoyijiong
 * @date 2021/8/18
 */
@Component
public class GameDao {

  private final GameMapper gameMapper;

  public GameDao(GameMapper gameMapper) {
    this.gameMapper = gameMapper;
  }

  public Game getGame(@PathVariable Integer id) {
    return gameMapper.getGameById(id);
  }

  public Game insertGame(Game game) {
    int i = gameMapper.insertGame(game);
    System.out.println(i);
    return game;
  }

  public Integer deleteGame(@PathVariable Integer id) {
    return gameMapper.deleteGameById(id);
  }
}
