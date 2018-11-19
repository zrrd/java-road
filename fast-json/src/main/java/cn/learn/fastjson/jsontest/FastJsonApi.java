package cn.learn.fastjson.jsontest;

import com.alibaba.fastjson.JSONObject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 阿里fast-json api 学习.
 *
 * @author shaoyijiong
 * @date 2018/7/23
 */
@SuppressWarnings("unused")
public class FastJsonApi {

  private static final String JSON_STRING = "{'name':'hhh','age':18,'hobby':['game','read','food'],"
      + "'pet':{'type':'cat','name':'JACK'}}";
  private static final String JSON_LIST = "[{'name':'hhh','age':18,'hobby':['game','read','food'],"
      + "'pet':{'type':'cat','name':'JACK'}}]";

  public static void main(String[] args) {
    String[] hobby = {"game", "read", "food"};
    Student student = new Student("aa", 18, Arrays.asList(hobby), new Pet("cat", "JACK"));

    //对象转JSON 字符串
    String jsonString = JSONObject.toJSONString(student);
    System.out.println(jsonString);

    //字符串转JSON
    Student student1 = JSONObject.parseObject(JSON_STRING, Student.class);

    //字符串转列表List
    List<Student> students = JSONObject.parseArray(JSON_LIST, Student.class);

    //字符串转Map public static JSONObject parseObject(String text) 这个方法返回的
    //JSONObject 其实实现 就是Map<String, Object>
    Map<String, Object> map = JSONObject.parseObject(JSON_STRING);
    System.out.println(map.get("hobby"));
  }
}
