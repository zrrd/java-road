package cn.learn.test.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;


import cn.learn.test.bean.Game;
import cn.learn.test.dao.mapper.GameMapper;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("unit")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DaoTestApplication.class)
@Sql({"classpath:init-game.sql"})
@Sql(statements = {"truncate table game"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GameDaoTest {

  @Autowired
  GameDao gameDao;

  @Test
  public void getGame() {
    Game game = gameDao.getGame(1);
    assertThat(game, notNullValue());

  }

  @Test
  public void insertGame() {
  }

  @Test
  public void deleteGame() {
  }
}