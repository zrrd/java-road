package cn.learn.java9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author shaoyijiong
 * @date 2020/9/17
 */
@SuppressWarnings("unused")
public class Go {

  /**
   * 创建不可变类
   */
  private static void collection() {
    //List<String> list = List.of("a");
    //Set<String> set = Set.of("hello", "world");
    //Map<String, String> map = Map.of("1", "a", "2", "b");

    // 将普通集合转化为不可变集合
    List<Object> list1 = Collections.unmodifiableList(new ArrayList<>());

  }

  private static void streamSupper() {

    // takeWhile 处理所有小于3的流 直到不符合条件
    //IntStream.of(1, 2, 3, 4, 5, 1).boxed().takeWhile(i -> i < 3).forEach(System.out::println);

    System.out.println(" ----- ");

    // 抛弃满足条件的值 直到 第一个不满足条件的数据出现
    //IntStream.of(1, 2, 3, 4, 5, 1).boxed().dropWhile(i -> i < 3).forEach(System.out::println);

    System.out.println(" ---- ");

    // 遍历所有数据 直到不满足循环 类型 for(i = 0;i<10;i++){}
    //IntStream.iterate(3, x -> x < 20, x -> x + 3).forEach(System.out::println);
    //

    //long count = Stream.ofNullable(100).count();
    //System.out.println(count);
    // 如果值为空返回一个空的流
    //count = Stream.ofNullable(null).count();
    //System.out.println(count);

  }

  private static void tryWithResources() {
    // 1.8 try-with-resources 这样用
    Reader inputString = new StringReader("message");
    BufferedReader br = new BufferedReader(inputString);
    // 这边必须还要定义一个引用来接收
    try (BufferedReader br1 = br) {
      br1.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 1.9 可以这样用
    //Reader inputString1 = new StringReader("message");
    //BufferedReader br1 = new BufferedReader(inputString);
    //try (br1) {
    //  br.readLine();
    //} catch (IOException e) {
    //  e.printStackTrace();
    //}
  }

  private static void diamondOperator() {
    // 1.8
    Hello<String> hello = new Hello<String>() {
      @Override
      public String sayHello() {
        return "hello world";
      }
    };
    // 1.9 可以去掉
    //Hello<String> hello1 = new Hello<>() {
    //  @Override
    //  public String sayHello() {
    //    return "hello world";
    //  }
    //};
  }

  static abstract class Hello<T> {

    public abstract T sayHello();
  }

  private static void optionTest() {
    // 返回包含值的流，如果值不存在，则返回空流
    //Stream<String> a = Optional.of("a").stream();
    // 如果值存在执行前面一个方法 否则执行后面一个方法
    //Optional.ofNullable(null).ifPresentOrElse(System.out::println, () -> System.out.println("null"));
    // 如果值存在，则返回一个描述该值的 Option ，否则使用 supplier 生成一个值
    //Optional.empty().or(() -> Optional.of("a"));
  }

  private static void completableFuture() {
    //支持延误和超时 ( timeout ) 机制
    //支持子类化
    //添加了一些新的工厂方法
    // https://www.twle.cn/c/yufei/java9/java9-baisic-completablefuture-api.html
  }


  private static void ioNew() throws IOException {
    // io 包中增加了新的方法来读取和复制 InputStream 中包含的数据

    //InputStream inputStream = new FileInputStream("");
    // readAllBytes：读取 InputStream 中的所有剩余字节。
    //byte[] bytes = inputStream.readAllBytes();
    // readNBytes： 从 InputStream 中读取指定数量的字节到数组中。
    //final byte[] data = new byte[5];
    //inputStream.readNBytes(data, 0, 5);
    // transferTo：读取 InputStream 中的全部字节并写入到指定的 OutputStream 中 。
    //inputStream.transferTo(new FileOutputStream(""));
  }


  public static void main(String[] args) {
    streamSupper();
  }

}
