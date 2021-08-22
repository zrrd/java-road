package cn.learn.test.controller;

import cn.learn.test.bean.Game;
import cn.learn.test.service.GameService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
public class GameControllerTest {

  @Mock
  GameService gameService;
  GameController gameController;
  Game game = new Game();


  @Before
  public void setUp() throws Exception {
    gameController = new GameController(gameService);
    when(gameService.getGame(any())).thenReturn(new Game());
    game.setId(1);
    when(gameService.insertGame(any())).thenReturn(game);
    when(gameService.deleteGame(any())).thenReturn(1);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getGame() {
    Game game = gameController.getGame(1);
    // mock gameService 被调用一次
    verify(gameService, times(1)).getGame(any());
    assertThat(game, notNullValue());

  }

  @Test
  public void insertGame() {
    Game result = gameController.insertGame(game);
    verify(gameService, times(1)).insertGame(game);
    assertThat(result, sameInstance(game));
  }

  @Test
  public void deleteGame() {
    Integer result = gameController.deleteGame(1);
    verify(gameService, times(1)).deleteGame(any());
    assertThat(result, is(1));
  }
}