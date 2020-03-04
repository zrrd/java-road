package cn.learn.generic.generic;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author shaoyijiong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpecificPerson extends Person {

  private Integer age;
  private Double height;

  public SpecificPerson() {
    super();
  }

  public SpecificPerson(String firstName, String lastName, Integer age, Double height) {
    super(firstName, lastName);
    this.age = age;
    this.height = height;
  }
}
