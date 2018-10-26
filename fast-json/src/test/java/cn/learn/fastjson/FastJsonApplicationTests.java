package cn.learn.fastjson;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class FastJsonApplicationTests {




	@Test
	public void contextLoads() {
		Map<String, String> a = new HashMap<>();
		a.put("url", "a");
		String s = JSONObject.toJSONString(a);
		System.out.println(s);
	}

}
