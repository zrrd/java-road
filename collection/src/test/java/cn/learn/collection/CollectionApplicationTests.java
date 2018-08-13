package cn.learn.collection;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectionApplicationTests {

	@Test
	public void contextLoads() {
		String mac = "0000faefdefa";
		mac = mac.toUpperCase();
		String[] strings = mac.split("");
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<strings.length;i++) {
			if (i % 2 == 0) {
				sb.append(strings[i]);

			} else {
				sb.append(strings[i]).append(":");
			}
		}
		String a = StringUtils.removeEnd(sb.toString(), ":");
		System.out.println(a);
	}

}
