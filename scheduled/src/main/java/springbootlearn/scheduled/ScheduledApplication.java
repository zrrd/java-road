package springbootlearn.scheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author shaoyijiong
 */
@SpringBootApplication
@EnableScheduling
public class ScheduledApplication {

  public static void main(String[] args) {
    SpringApplication.run(ScheduledApplication.class, args);
  }
}
