package cn.learn.java8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
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
    Predicate<String> predicate = (s) -> s.length() > 0;
    // true
    System.out.println("test1:" + predicate.test("foo"));
    // false
    System.out.println("test2:" + predicate.negate().test("foo"));
    Predicate<Boolean> nonNull = Objects::nonNull;
    Predicate<Boolean> isNull = Objects::isNull;
    Predicate<String> isEmpty = String::isEmpty;
    System.out.println("test3:" + isEmpty.test(""));
    Predicate<String> isNotEmpty = isEmpty.negate();
  }

  /**
   * Function接口接收一个参数，并返回单一的结果
   * apply 返回值 compose 在方法前调用 andThen 在方法后调用
   */
  private static void testFunctions() {
    //参数 String 返回 Integer  input result
    java.util.function.Function<String, Integer> toInteger = Integer::valueOf;
    //在toInteger后调用
    java.util.function.Function<String, String> backToString = toInteger.andThen(String::valueOf);
    //在toInteger前调用
    java.util.function.Function<String, Integer> backToInteger = toInteger.compose(String::valueOf);
    // "123"
    System.out.println(backToString.apply("123")+1);
    System.out.println(backToInteger.apply("123")+1);
  }

  /**
   * Supplier接口产生一个给定类型的结果。与Function不同的是，Supplier没有输入参数
   */
  private static void testSuppliers() {
    Supplier<List<String>> listSupplier = ArrayList::new;
    List<String> list = listSupplier.get();
    list.add("a");
    System.out.println(list);
  }

  /**
   * Consumer代表了在一个输入参数上需要进行的操作
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

  public static void main(String[] args) {
    testPredicates();
    testFunctions();
    testSuppliers();
    testConsumers();
    testComparators();
  }
}
