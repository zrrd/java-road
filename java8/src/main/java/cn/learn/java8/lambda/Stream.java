package cn.learn.java8.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lambda 测试类
 *
 * @author shaoyijiong
 * @date 2018/7/3
 */
public class Stream {


  public static void main(String[] args) {
    Integer[] a = new Integer[]{3, 1, 2, 4, 6, 5};
    System.out.println(Arrays.stream(a).max(Integer::compareTo).orElse(null));

    Arrays.stream(a).forEach(System.out::print);
    System.out.println();

    String[] strings = new String[]{"a","b","c","a","ok"};
    List<String> list = Arrays.asList(strings);

    //通过流去重
    List list1 = list.stream().distinct().collect(Collectors.toList());
    System.out.println(list1);

    //跳过前几个
    List list2 = list.stream().skip(3).collect(Collectors.toList());
    System.out.println(list2);

    //截取前几个
    List list3 = list.stream().limit(3).collect(Collectors.toList());
    System.out.println(list3);

    //过滤对象
    List list4 = list.stream().filter("a"::equals).collect(Collectors.toList());
    System.out.println(list4);

    //通过类型进行转换 如果字母为a 转化成 z 其他不变  (一行内不用写返回值)
    List list5 = list.stream().map(b -> "a".equals(b) ? "z" : b).collect(Collectors.toList());
    System.out.println(list5);

    //flatMap也是将Stream进行转换，flatMap与map的区别在于
    //flatMap是将一个Stream中的每个值都转成一个个Stream，然后再将这些流扁平化成为一个Stream。
    //["Hello","World"] -> ["H","e","l", "o","W","r","d"] 将嵌套类型扁平化
    List list6 = list.stream().map(c -> c.split("")).flatMap(Arrays::stream).collect(Collectors.toList());
    System.out.println(list6);

    //排序  随便一个排序
    List list7 = list.stream().sorted((d,e)-> -(d.hashCode() - e.hashCode())+10).collect(Collectors.toList());
    System.out.println(list7);

    //对每个对象执行提供的action操作
    List list8 = list.stream().peek(f -> System.out.println(f + "peek")).collect(Collectors.toList());
    System.out.println(list8);

    //聚合 max min count
    System.out.println(list.stream().max(Comparator.naturalOrder()).orElse(""));
    System.out.println(list.stream().min(Comparator.naturalOrder()).orElse(""));
    System.out.println(list.stream().count());

    //匹配
    //有匹配OK的
    System.out.println("匹配");
    System.out.println(list.stream().anyMatch(g -> "ok".equals(g)));
    System.out.println(list.stream().allMatch(g -> "ok".equals(g)));
    System.out.println(list.stream().noneMatch(g -> "pp".equals(g)));
  }
}
