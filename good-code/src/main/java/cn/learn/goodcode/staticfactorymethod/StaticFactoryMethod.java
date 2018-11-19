package cn.learn.goodcode.staticfactorymethod;

/**
 * 使用静态工厂方法来代替构造函数
 *
 * @author 邵益炯
 * @date 2018/9/28
 */
public class StaticFactoryMethod {

  public static void main(String[] args) {
    //方法的意义更加明显
    User user = User.createUser(1, "a");
    //可以使用链式调用
    Integer age = User.createUser(1, "a").getAge();
  }
}
