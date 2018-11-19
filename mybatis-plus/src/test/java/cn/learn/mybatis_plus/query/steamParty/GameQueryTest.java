package cn.learn.mybatis_plus.query.steamParty;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameQueryTest {

  @Autowired
  GameQuery gameQuery;

  @Test
  public void list() throws Exception {
    System.out.println("通过rowBounds分页");
    int pageNum = 3;
    int pageSize = 2;
    gameQuery.list(pageNum, pageSize);
  }



  @Test
  public void quertGames() throws Exception {
    System.out.println("通过pageHelper分页");
    int pageNum = 3;
    int pageSize = 2;
    double score= 0;
    gameQuery.quertGames(pageNum,pageSize, score);
  }

}