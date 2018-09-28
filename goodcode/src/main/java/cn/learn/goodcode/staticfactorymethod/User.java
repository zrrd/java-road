package cn.learn.goodcode.staticfactorymethod;

import lombok.Getter;

/**
 * model类
 *
 * @author 邵益炯
 * @date 2018/9/28
 */
@Getter
public class User {

  private Integer age;
  private String name;

  private User(Integer age, String name) {
    this.age = age;
    this.name = name;

  }

  /**
   * 用静态工厂方法来替代构造方法
   */
  public static User createUser(Integer age, String name) {
    return new User(age, name);
  }

}
