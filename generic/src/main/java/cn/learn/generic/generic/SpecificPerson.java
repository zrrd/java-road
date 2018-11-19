package cn.learn.generic.generic;


/**
 * @author shaoyijiong
 */
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

  @Override
  public String toString() {
    return super.toString() + "SpecificPerson{" +
        "age=" + age +
        ", height=" + height +
        '}';
  }
}
