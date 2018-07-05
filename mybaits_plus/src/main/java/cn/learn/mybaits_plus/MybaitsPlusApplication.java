package cn.learn.mybaits_plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author win
 */
@EnableTransactionManagement
@SpringBootApplication
public class MybaitsPlusApplication {

  public static void main(String[] args) {
    SpringApplication.run(MybaitsPlusApplication.class, args);
  }
}
