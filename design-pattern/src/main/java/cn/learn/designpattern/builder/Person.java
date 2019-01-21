package cn.learn.designpattern.builder;

import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @author shaoyijiong
 * @date 2019/1/21
 */
@Data
public class Person {

  /**
   * 参数过多导致构造函数不好用
   */
  private Integer age;
  private String sex;
  private String firstName;
  private String lastName;
  private String education;
  private Double height;

  @Data
  public static class PersonBuilder {

    private Integer age;
    private String sex;
    private String firstName;
    private String lastName;
    private String education;
    private Double height;

    /**
     * 必要的参数可以在构造函数中传入
     */
    public PersonBuilder(String sex) {
      this.sex = sex;
    }

    public PersonBuilder buildAge(Integer age) {
      this.age = age;
      return this;
    }

    public PersonBuilder buildFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public PersonBuilder buildLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public PersonBuilder buildEducation(String education) {
      this.education = education;
      return this;
    }

    public PersonBuilder buildHeight(Double height) {
      this.height = height;
      return this;
    }


    public Person build() {
      Person person = new Person();
      BeanUtils.copyProperties(this, person);
      return person;
    }
  }

}
