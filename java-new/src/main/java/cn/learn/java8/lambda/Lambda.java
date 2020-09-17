package cn.learn.java8.lambda;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Function;

/**
 * Lambda 表达式匿名函数 将行为传给函数 ()->{}  ()里面放参数 {}这个里面放方法体  Java 8 函数式编程风格性能低
 *
 * @author 邵益炯
 */
@SuppressWarnings("all")
public class Lambda {

  public void lambdaTest() {
    List<String> list = ImmutableList.of("a", "b", "c");

    /*java8之前*/
    for (String s : list) {
      System.out.println(s);
    }

    // java8之后 .forEach是java8在iterable接口中新加的default方法
    // 所有继承实现iterable的类或者接口都有这个方法
    list.forEach(System.out::println);

    // 可以加花括号
    list.forEach(e -> {
      System.out.print(e);
      System.out.print(e);
    });

    // 可以调用成员变量和局部变量  并且会隐含转化为final 在内部不能改变
    String separator = ",";
    list.forEach(e -> System.out.print(e + separator));

    final String separator1 = ",";
    list.forEach(e -> System.out.print(e + separator1));

    //一行的话不需要显式返回值,有花括号的话 需要显式返回
    //Lambda返回值  上下两个等价
    list.sort((e1, e2) -> e1.compareTo(e2));

    list.sort((e1, e2) -> {
      int result = e1.compareTo(e2);
      return result;
    });

    // 用lanbda表达式实现Runnable
    //java8之前  匿名内部类 -> lambda
    new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("java8之前这样做");
      }
    }).start();

    // java8之后  Runnable @FunctionalInterface 有这个注解 说明它只有一个要实现的方法
    new Thread(() -> System.out.println("java8这样做")).start();

    /**
     * lambda访问权限
     */
    int a = 0;
    Function<Integer, String> toString = integer -> {
      //在lambda中的临时变量默认是final的不能改变引用,但是在lambda中修改成员变量与静态变量(传入当前对象,修改当前对象的值)
      //a = 2;
      return String.valueOf(integer);
    };

  }

}


