package cn.learn.test.controller;

import cn.learn.test.bean.Game;
import cn.learn.test.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shaoyijiong
 * @date 2021/8/18
 */
@Slf4j
@RestController
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/game/{id}")
  public Game getGame(@PathVariable Integer id) {
    return gameService.getGame(id);
  }

  @PostMapping("/game")
  public Game insertGame(Game game) {
    return gameService.insertGame(game);
  }

  @DeleteMapping("/game/{id}")
  public Integer deleteGame(@PathVariable Integer id) {
    return gameService.deleteGame(id);
  }
}
