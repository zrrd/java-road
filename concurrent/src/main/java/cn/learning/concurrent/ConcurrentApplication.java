package cn.learning.concurrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableScheduling
public class ConcurrentApplication {

	public static void main(String[] args) {
		ConcurrentApplication.run(ConcurrentApplication.class, args);
	}
}
