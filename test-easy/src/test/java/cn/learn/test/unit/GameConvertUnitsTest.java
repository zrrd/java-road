package cn.learn.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


import cn.learn.test.bean.Game;
import cn.learn.test.bean.GameVo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class GameConvertUnitsTest {

  private Game game;


  /**
   * 会在所有的方法执行前被执行，static 方法 （全局只会执行一次，而且是第一个运行）
   */
  @BeforeClass
  public static void beforeClass() {
    System.out.println("BeforeClass");
  }

  /**
   * 会在所有的方法执行之后进行执行，static 方法 （全局只会执行一次，而且是最后一个运行）
   */
  @AfterClass
  public static void afterClass() {
    System.out.println("AfterClass");

  }

  /**
   * 会在每一个测试方法被运行前执行一次
   */
  @Before
  public void setUp() throws Exception {
    game = new Game();
    game.setId(1);
    game.setName("英雄联盟");
    game.setPrice(BigDecimal.valueOf(9.95));
    game.setPublishDate(LocalDate.of(2022, 1, 1));
    game.setScore(9.28);
    game.setImage("www.baidu.com");
    game.setType("rpg");
    game.setStatus(1);
    System.out.println("Before");
  }

  /**
   * 会在每一个测试方法运行后被执行一次
   */
  @After
  public void tearDown() throws Exception {
    System.out.println("After");
  }

  @Test
  public void convert() {
    GameVo gameVo = GameConvertUnits.convert(game);
    assertNotNull(gameVo);
    assertEquals(gameVo.getName(), "英雄联盟");
    assertEquals(gameVo.getPrice(), "10.0");
    assertEquals(gameVo.getPublishDate(), "2022-01-01");
    assertEquals(gameVo.getScore(), "9.3");
    assertEquals(gameVo.getImage(), "www.baidu.com");
    assertEquals(gameVo.getStatus(), "在售");

  }

  /**
   * 抛出指定异常才测试成功
   */
  @Test(expected = RuntimeException.class)
  public void convert1() {
    GameVo gameVo = GameConvertUnits.convert(null);
    assertNull(gameVo);
  }

  @Test
  public void convertList() {
    List<GameVo> gameVos = GameConvertUnits.convertList(Collections.singletonList(game));
    assertEquals(gameVos.size(), 1);
  }

  /**
   * 忽略测试 , 不会打出下面
   */
  @Test
  @Ignore
  public void ignoreTest() {
    System.out.println("not ignore");
  }

  /**
   * Junit 提供了一个暂停的方便选项。如果一个测试用例比起指定的毫秒数花费了更多的时间，那么 Junit 将自动将它标记为失败
   */
  @Test(timeout = 1)
  public void timeOut() {
    try {
      TimeUnit.SECONDS.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}