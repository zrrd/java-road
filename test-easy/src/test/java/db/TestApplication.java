package db;

import static org.powermock.api.mockito.PowerMockito.mock;

import cn.learn.test.service.GameService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

/**
 * 指定扫描的包的位置 减少引入的依赖
 * <br> 特别是针对多module的项目,比如当需要测service层的代码时,不扫描dao层的代码,不引入db相关的依赖,让单测效率提高
 *
 * @author shaoyijiong
 * @date 2021/8/28
 */
@ActiveProfiles("go")
//@SpringBootApplication(scanBasePackages = {"cn.learn.test.dao", "cn.learn.test.mapper"})
@SpringBootApplication(scanBasePackages = "cn.learn.test")
public class TestApplication {

  /**
   * 当你需要spring环境而又不想将下层的依赖扫入spring中的时候 , 可以使用 @TestConfiguration 将一个mock ben放入spring中
   */
  @TestConfiguration
  static class JustTestConfiguration {

    /**
     * @return 一个mock的bean
     */
    //@Bean
    public GameService gameService() {
      return mock(GameService.class);
    }
  }
}
