package cn.learn.java8.lambda;


/**
 * @author shaoyijiong
 */
@FunctionalInterface
public interface FunctionTest {

  /**
   * 函数式接口，一个接口包含一个方法  默认方法和静态方法不算在内
   */
  void method();


  /**
   * <p>默认方法与抽象方法不同之处在于抽象方法必须要求实现，但是默认方法则没有这个要求。</p>
   * <p>相反，每个接口都必须提供一个所谓的默认实现，这样所有的接口实现者将会默认继承它 </p>
   * <p>（如果有必要的话，可以覆盖这个默认实现）</p>
   */

  default void defaultMethod() {
    System.out.println("默认方法");
  }

  static String staticMethod(String aa) {
    return aa;
  }
}

class Test implements FunctionTest {

  @Override
  public void method() {

  }

  /**
   * 覆盖了默认方法
   */
  @Override
  public void defaultMethod() {

  }

  public static void main(String[] args) {
    //因为Function中只有一个抽象方法  默认实现了那个方法
    FunctionTest fun = () -> System.out.println("实现了method方法");
    fun.method();
  }
}




