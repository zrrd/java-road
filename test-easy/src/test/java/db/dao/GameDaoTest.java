package db.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import cn.learn.test.bean.Game;
import cn.learn.test.dao.GameDao;
import cn.learn.test.mapper.GameMapper;
import db.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("unit")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@Sql({"classpath:init.sql"})
@Sql(statements = {"truncate table `game`"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GameDaoTest {

  @Autowired
  GameDao gameDao;
  @Autowired
  GameMapper gameMapper;

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
    assertThat(gameDao.deleteGame(1), notNullValue());
  }
}