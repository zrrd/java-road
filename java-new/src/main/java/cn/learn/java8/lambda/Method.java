package cn.learn.java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 内置的函数式接口
 *
 * @author 邵益炯
 * @date 2018/8/17
 */
public class Method {

  /**
   * Predicate是一个布尔类型的函数，该函数只有一个输入参数 判断true 或者 false test 判断是否为空 and 且 or 或 negate 反转
   */
  private static void testPredicates() {
    //参数 String  返回 boolean
    Predicate<String> predicate = s -> s.length() > 0;
    // true
    System.out.println("test1:" + predicate.test("foo"));
    // false
    System.out.println("test2:" + predicate.negate().test("foo"));

    Predicate<String> predicate1 = s -> s.length() > 2;
    Predicate<String> predicate2 = s -> s.length() < 5;
    //且关系
    Predicate<String> predicate3 = predicate1.and(predicate2);
    //false
    System.out.println("test3:" + predicate3.test("hello world"));
    //或关系
    Predicate<String> predicate4 = predicate1.or(predicate2);
    //true
    System.out.println("test4:" + predicate4.test("hello world"));
  }

  /**
   * Function接口接收一个参数，并返回单一的结果 apply 返回值 compose 在方法前调用 andThen 在方法后调用
   */
  private static void testFunctions() {
    //参数 String 返回 Integer  input result
    Function<String, Integer> toInteger = Integer::valueOf;
    //在toInteger后调用
    Function<String, String> backToString = toInteger.andThen(String::valueOf);
    //在toInteger前调用
    Function<String, Integer> backToInteger = toInteger.compose(String::valueOf);
    // "123"
    System.out.println(backToString.apply("123") + 1);
    System.out.println(backToInteger.apply("123") + 1);
  }

  /**
   * Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数 实现get方法
   */
  private static void testSuppliers() {
    Supplier<List<String>> listSupplier = ArrayList::new;
    List<String> list = listSupplier.get();
    list.add("a");
    System.out.println(list);
  }

  /**
   * Consumer代表了在一个输入参数上需要进行的操作 接收一个参数  没有返回  一个方法
   */
  private static void testConsumers() {
    Consumer<String> stringConsumer = System.out::println;
    stringConsumer.accept("你好");
  }

  private static void testComparators() {
    //比较器
    Comparator<String> stringComparator = (s1, s2) -> s2.hashCode() - s1.hashCode();
    System.out.println(stringComparator.compare("a", "b"));
    System.out.println(stringComparator.reversed().compare("a", "b"));
  }

  private static void BiFunctionTest() {
    //对应第一个参数 第二给参数 返回值
    BiFunction<Integer, String, String> biFunction = (o, o2) -> o + o2;
    String a = biFunction.apply(1, "a");
  }

  public static void main(String[] args) {
    //其他函数式接口 http://www.runoob.com/java/java8-functional-interfaces.html
    testPredicates();
    testFunctions();
    testSuppliers();
    testConsumers();
    testComparators();
  }
}
