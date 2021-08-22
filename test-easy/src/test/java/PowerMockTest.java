import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.powermock.api.mockito.PowerMockito.mock;

import cn.learn.test.bean.Game;
import cn.learn.test.service.GameService;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shaoyijiong
 * @date 2021/8/20
 */
@Slf4j
public class PowerMockTest {

  public static class ServiceHolder {

    private final Set<Object> services = new HashSet<Object>();

    public void addService(Object service) {
      services.add(service);
    }

    public void removeService(Object service) {
      services.remove(service);
    }
  }

  /**
   * <b>获取内部的状态</b>
   * <br>
   * 对于定义在内部的对象,我们要知道他的状态可能需要给类添加一个get方法来获取,而获取这个内部属性,这个get可能被不安全的使用 而使用Whitebox能够更安全的获取内部状态
   */
  @Test
  public void getInternalState() throws Exception {
    ServiceHolder tested = new ServiceHolder();
    final Object service = new Object();

    tested.addService(service);

    // This is how you get the private services set using PowerMock
    Set<String> services = Whitebox.getInternalState(tested,
        "services");

    // 上面的方法也能这么写 , 当然只有一个set的时候
    // Set<String> service1 = Whitebox.getInternalState(tested, Set.class);

    assertEquals("Size of the \"services\" Set should be 1", 1, services
        .size());
    assertSame("The services Set should didn't contain the expect service",
        service, services.iterator().next());
  }

  /**
   * 依赖注入的方式提供服务
   */
  public static class GameApi {

    @Autowired
    private GameService gameService;

    public Game generateReport(Integer gameId) {
      Game game = gameService.getGame(gameId);
      /*
       * Imagine some other code here that generates the report based on the
       * template id.
       */
      return game;
    }
  }

  /**
   * <b>设置内部状态</b>
   */
  @Test
  public void setInternalState() {
    GameApi gameApi = new GameApi();
    GameService gameServiceMock = mock(GameService.class);
    Whitebox.setInternalState(gameApi, "gameService", gameServiceMock);
    // 也可以用下面的方式
    // Whitebox.setInternalState(gameApi, GameService.class, gameServiceMock);
  }

  /**
   * 如果A 继承了 B 并且都有同名的字段,可以使用以下方式指定获取哪个类中的字段
   */
  @Test
  public void setInternalStateSupper() {
    // String myPrivateString = Whitebox.<String> getInternalState(instanceOfA, "myPrivateString", B.class);
    // String myPrivateString = Whitebox.getInternalState(instanceOfA, String.class, B.class);
    // String myPrivateString = Whitebox.getInternalState(instanceOfA, "myPrivateString", B.class, String.class);

    // Whitebox.setInternalState(instanceOfA, "myPrivateString", "this is my private string", B.class);
    // Whitebox.setInternalState(instanceOfA, String.class, "this is my private string", B.class);
  }

  private int sum(int a, int b) {
    return a + b;
  }

  /**
   * <b>调用私有方法</b>
   */
  @Test
  public void invokeMethod() throws Exception {
    int sum = Whitebox.<Integer>invokeMethod(new PowerMockTest(), "sum", 1, 2);

    // 对于重载的方法 , 通过指定参数类型找到对应的方法
    // private int myMethod(int id) {
    //	  return 2*id;
    // }
    //
    // private int myMethod(Integer id) {
    // 		return 3*id;
    // }
    int result = Whitebox.<Integer>invokeMethod(new PowerMockTest(), new Class<?>[] {int.class}, "myMethod", 1);
    // 调用静态方法
    int sum1 = Whitebox.<Integer>invokeMethod(PowerMockTest.class, "sum", 1, 2);
    // 通过不指定方法名的方式调用
    // Whitebox.invokeMethod(myInstance, param1, param2);

    // 调用私有构造函数
    // PrivateConstructorInstantiationDemo instance = Whitebox.invokeConstructor(PrivateConstructorInstantiationDemo.class, new Class<?>[]{Integer.class}, 43);

  }
}


