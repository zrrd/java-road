package cn.learn.java8.lambda;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**Lambda 表达式匿名函数 将行为传给函数 ()->{}  ()里面放参数 {}这个里面放方法体  Java 8 函数式编程风格性能低
 * @author jiudao
 */
public class Lambda {
    public void LambdaTest() {
        /*java8之前*/
        List<String> list = Arrays.asList("a","b","c");
        for (String s : list){
            System.out.println(s);
        }

        // java8之后 .forEach是java8在iterable接口中新加的default方法
        // 所有继承实现iterable的类或者接口都有这个方法
        Arrays.asList("a", "b", "c").forEach(System.out::println);

        // 可以加花括号
        Arrays.asList("a", "b", "d").forEach(e -> {
            System.out.print(e);
            System.out.print(e);
        });

        // 可以调用成员变量和局部变量  并且会隐含转化为final
        String separator = ",";
        Arrays.asList("a", "b", "d").forEach(
                (String e) -> System.out.print(e + separator));


        final String separator1 = ",";
        Arrays.asList("a", "b", "d").forEach(
                (String e) -> System.out.print(e + separator1));


        //一行的话不需要显式返回值,有花括号的话 需要显式返回
        //Lambda返回值  上下两个等价
        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );

        Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );

        // 用lanbda表达式实现Runnable
        //java8之前  匿名内部类 -> lambda
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("java8之前这样做");
            }
        }).start();

        // java8之后  Runnable @FunctionalInterface 有这个注解 说明它只有一个要实现的方法
        new Thread(()->System.out.println("java8这样做")).start();
    }

}


