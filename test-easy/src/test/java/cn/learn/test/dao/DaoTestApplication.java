package cn.learn.test.dao;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author shaoyijiong
 * @date 2021/8/24
 */
@ActiveProfiles("unit")
@SpringBootApplication(scanBasePackages = "cn.learn.test.dao")
public class DaoTestApplication {

}
