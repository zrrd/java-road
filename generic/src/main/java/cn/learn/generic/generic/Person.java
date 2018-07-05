package cn.learn.generic.generic;

/*通过静态工厂实例化一个类   而不是通过构造方法    缺点：如果类不含public和protect的构造方法将不能被继承*/

/**
 * @author jiudao
 */
public class Person {

  private String firstName;
  private String lastName;

  public Person() {

  }

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Person(String firstName) {
    this.firstName = firstName;
  }

/*  如果想给他单独设定lastName 会与上面的构造方法冲突  并且没有具体的语义 比较难以理解
    public staticFactoryMethod(String lastName) {
        this.lastName = lastName;
    }*/

  /**
   * 静态构造方法 返回只带出售firstName的person
   */
  public static Person firstNamePerson(String firstName) {
    return new Person(firstName, "");
  }

  /**
   * 静态构造方法 返回只带出售lastName的person
   */
  public static Person lastNamePerson(String lastName) {
    return new Person("", lastName);
  }

  @Override
  public String toString() {
    return "Person{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}

