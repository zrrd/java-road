package cn.learn.java8.lambda;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Lambda 测试类
 *
 * @author shaoyijiong
 * @date 2018/7/3
 */
public class Stream {

  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Integer[] a = new Integer[]{3, 1, 2, 4, 6, 5};
    System.out.println(Arrays.stream(a).max(Integer::compareTo).orElse(null));

    Arrays.stream(a).forEach(System.out::print);

    List<String> list = ImmutableList.of("a", "b", "c", "a", "ok");

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
    List list6 = list.stream().map(c -> c.split("")).flatMap(Arrays::stream)
        .collect(Collectors.toList());
    System.out.println(list6);

    //排序  随便一个排序
    List list7 = list.stream().sorted((d, e) -> -(d.hashCode() - e.hashCode()) + 10)
        .collect(Collectors.toList());
    System.out.println(list7);

    //对每个对象执行提供的action操作
    List list8 = list.stream().peek(f -> System.out.println(f + "peek"))
        .collect(Collectors.toList());
    System.out.println(list8);

    //判断流中所有元素都用a开头 类似的还有 anyMatch 有一个匹配 nonMatch 没有匹配
    boolean allMatch = list.stream().allMatch(g -> g.startsWith("a"));

    //a#b#c#a#okok 该操作是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。
    Optional<String> reduce = list.stream().reduce((h1, h2) -> h1 + "#" + h2);
    // start#a#b#c#a#okok 添加初始值
    String reduce1 = list.stream().reduce("start", (h1, h2) -> h1 + "#" + h2);
    reduce.ifPresent(System.out::print);

    //聚合 max min count
    System.out.println(list.stream().max(Comparator.naturalOrder()).orElse(""));
    System.out.println(list.stream().min(Comparator.naturalOrder()).orElse(""));
    System.out.println(list.stream().count());

    //匹配
    //有匹配OK的
    System.out.println("匹配");
    System.out.println(list.stream().anyMatch("ok"::equals));
    System.out.println(list.stream().allMatch("ok"::equals));
    System.out.println(list.stream().noneMatch("pp"::equals));

    //list转map
    String text = "1.你好;3.撒旦;56.介绍";
    //toMap  key 映射 value映射
    Map<String, String> map = Arrays.stream(text.split(";"))
        .collect(Collectors.toMap(b -> b.split("\\.")[0], b -> b.split("\\.")[1]));
    System.out.println(map);

    //并行操作  将 stream 换为 parallelStream

    //循环4次
    IntStream.range(1, 4).forEach(System.out::println);

    //收集函数

    //分区函数 分成长度大于2的 和长度小于2的
    Map<Boolean, List<String>> collect1 = list.stream()
        .collect(Collectors.partitioningBy(c -> c.length() > 2));
    //根据字符串长度分组
    Map<Integer, List<String>> collect2 = list.stream()
        .collect(Collectors.groupingBy(String::length));

    //Collectors
    //toList()    将元素收集到一个 List 中。
    //toSet()   将元素收集到一个 Set 中。
    //toCollection(Supplier<Collection>)    将元素收集到一个特定类型的 Collection 中。
    //toMap(Function<T, K>, Function<T, V>)   将元素收集到一个 Map 中，依据提供的映射函数将元素转换为键值。
    //summingInt(ToIntFunction<T>)    计算将提供的 int 值映射函数应用于每个元素（以及 long 和 double 版本）的结果的总和。
    //summarizingInt(ToIntFunction<T>)    计算将提供的 int 值映射函数应用于每个元素（以及 long 和 double 版本）的结果的 sum、min、max、count 和 average。
    //reducing()    向元素应用缩减（通常用作下游收集器，比如用于 groupingBy）（各种版本）。
    //partitioningBy(Predicate<T>)    将元素分为两组：为其保留了提供的预期的组和未保留预期的组。
    //partitioningBy(Predicate<T>, Collector)   将元素分区，使用指定的下游收集器处理每个分区。
    //groupingBy(Function<T,U>)   将元素分组到一个 Map 中，其中的键是所提供的应用于流元素的函数，值是共享该键的元素列表。
    //groupingBy(Function<T,U>, Collector)    将元素分组，使用指定的下游收集器来处理与每个组有关联的值。
    //minBy(BinaryOperator<T>)    计算元素的最小值（与 maxBy() 相同）。
    //mapping(Function<T,U>, Collector)   将提供的映射函数应用于每个元素，并使用指定的下游收集器（通常用作下游收集器本身，比如用于 groupingBy）进行处理。
    //joining()     假设元素为 String 类型，将这些元素联结到一个字符串中（或许使用分隔符、前缀和后缀）。
    //counting()    计算元素数量。（通常用作下游收集器。）
  }
}
