package cn.learn.utils.fastjson;

import java.util.List;
import lombok.Data;

/**
 * 学生类.
 *
 * @author shaoyijiong
 * @date 2018/7/23
 */
@Data
class Student {

  private String name;
  private Integer age;
  private List<String> hobby;
  private Pet pet;

  Student(String name, Integer age, List<String> hobby, Pet pet) {
    this.name = name;
    this.age = age;
    this.hobby = hobby;
    this.pet = pet;
  }
}
