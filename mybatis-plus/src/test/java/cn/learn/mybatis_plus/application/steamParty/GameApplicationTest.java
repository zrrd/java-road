package cn.learn.mybatis_plus.application.steamParty;

import cn.learn.mybatis_plus.application.steamParty.dto.GameDto;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameApplicationTest {

  @Autowired
  GameApplication gameApplication;

  @Test
  public void insertGame() throws Exception {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = simpleDateFormat.parse("2008-01-05");
    //如果加了枚举的话是加不 score 这个是加不进去数据库的
    GameDto gameDto = new GameDto("aa",10.2,date,1.0,"");
    gameApplication.insertGame(gameDto);
  }

}