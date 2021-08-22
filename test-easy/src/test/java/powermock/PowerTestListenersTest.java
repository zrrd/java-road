package powermock;

import cn.learn.test.controller.GameController;
import cn.learn.test.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockListener;
import org.powermock.core.testlisteners.FieldDefaulter;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * FieldDefaulter 会清空所有属性
 *
 * @author shaoyijiong
 * @date 2021/8/22
 */
@RunWith(PowerMockRunner.class)
@PowerMockListener(FieldDefaulter.class)
public class PowerTestListenersTest {

  @Mock
  GameService gameService;

  GameController gameController;

  @Before
  public void setUp() {
    gameController = new GameController(gameService);
  }

  @Test
  public void testGet() {
    gameController.getGame(1);
  }
}
