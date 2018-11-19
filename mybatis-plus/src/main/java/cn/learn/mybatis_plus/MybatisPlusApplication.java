package cn.learn.mybatis_plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author win
 */
@EnableTransactionManagement
@SpringBootApplication
public class MybatisPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybatisPlusApplication.class, args);
  }
}
