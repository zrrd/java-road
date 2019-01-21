package cn.learn.designpattern.builder;

import cn.learn.designpattern.builder.Person.PersonBuilder;

/**
 * @author shaoyijiong
 * @date 2019/1/21
 */
public class TestBuild {

  public static void main(String[] args) {
    Person person = new PersonBuilder("man").buildAge(12).buildLastName("aa").build();
    System.out.println(person);
  }
}
