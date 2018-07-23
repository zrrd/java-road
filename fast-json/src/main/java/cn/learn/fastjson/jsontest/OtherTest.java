package cn.learn.fastjson.jsontest;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shaoyijiong
 * @date 2018/7/23
 */
public class OtherTest {

  @Getter
  @Setter
  static class Pet {

    String name;

    public Pet(String name) {
      this.name = name;
    }
  }

  @Getter
  @Setter
  static class Dog extends Pet {

    String jiao;

    public Dog(String name, String jiao) {
      super(name);
      this.jiao = jiao;
    }
  }

  @Getter
  @Setter
  static class Cat extends Pet {

    String zhua;

    public Cat(String name, String zhua) {
      super(name);
      this.zhua = zhua;
    }
  }

  public static void main(String[] args) {
    Pet pet1 = new Dog("aa", "bb");
    Pet pet2 = new Cat("cc", "dd");
    Pet pet3 = new Pet("ff");
    System.out.println(JSON.toJSONString(pet1));
    System.out.println(JSON.toJSONString(pet2));
    System.out.println(JSON.toJSONString(pet3));
  }
}
