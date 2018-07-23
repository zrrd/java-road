package cn.learn.fastjson.jsontest;

import java.util.List;
import lombok.Data;

/**
 * 学生类.
 *
 * @author shaoyijiong
 * @date 2018/7/23
 */
@Data
public class Student {



  private String name;
  private Integer age;
  private List<String> hobby;
  private Pet pet;

  public Student(String name, Integer age, List<String> hobby, Pet pet) {
    this.name = name;
    this.age = age;
    this.hobby = hobby;
    this.pet = pet;
  }
}
