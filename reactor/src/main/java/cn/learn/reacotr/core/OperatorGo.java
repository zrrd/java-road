package cn.learn.reacotr.core;

import java.time.Duration;
import java.util.Arrays;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 操作符
 *
 * @author shaoyijiong
 * @date 2019/5/12
 */
@SuppressWarnings("ALL")
public class OperatorGo {

  /**
   * <pre>
   *   buffer 和 bufferTimeout
   * 这两个操作符的作用是把当前流中的元素收集到集合中，并把集合对象作为流中的新元素。在进行收集时可以指定不同的条件：所包含的元素的最大数量或收集的时间间隔。方法 buffer()仅使用一个条件，而 bufferTimeout()可以同时指定两个条件。指定时间间隔时可以使用 Duration 对象或毫秒数，即使用 bufferMillis()或 bufferTimeoutMillis()两个方法。
   * 除了元素数量和时间间隔之外，还可以通过 bufferUntil 和 bufferWhile 操作符来进行收集。这两个操作符的参数是表示每个集合中的元素所要满足的条件的 Predicate 对象。bufferUntil 会一直收集直到 Predicate 返回为 true。使得 Predicate 返回 true 的那个元素可以选择添加到当前集合或下一个集合中；bufferWhile 则只有当 Predicate 返回 true 时才会收集。一旦值为 false，会立即开始下一次收集。
   * 代码清单 5 给出了 buffer 相关操作符的使用示例。第一行语句输出的是 5 个包含 20 个元素的数组；第二行语句输出的是 2 个包含了 10 个元素的数组；第三行语句输出的是 5 个包含 2 个元素的数组。每当遇到一个偶数就会结束当前的收集；第四行语句输出的是 5 个包含 1 个元素的数组，数组里面包含的只有偶数。
   * 需要注意的是，在代码清单 5 中，首先通过 toStream()方法把 Flux 序列转换成 Java 8 中的 Stream 对象，再通过 forEach()方法来进行输出。这是因为序列的生成是异步的，而转换成 Stream 对象可以保证主线程在序列生成完成之前不会退出，从而可以正确地输出序列中的所有元素。
   * </pre>
   */
  private static void buffer() {
    //1-100 -> [1-20] [21-40] [41-60] ...
    Flux.range(1, 100).buffer(20).subscribe(System.out::println);
    //1-10 -> [1,2] [3,4]..
    Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
    //1-10 -> [2] [4] [6] ..
    Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
  }

  /**
   * 对流中包含的元素进行过滤，只留下满足 Predicate 指定条件的元素。代码清单 6 中的语句输出的是 1 到 10 中的所有偶数。
   */
  private static void filter() {
    Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);
  }

  /**
   * window 操作符的作用类似于 buffer，所不同的是 window 操作符是把当前流中的元素收集到另外的 Flux 序列中，因此返回值类型是 Flux<Flux<T>>。在代码清单 7
   * 中，两行语句的输出结果分别是 5 个和 2 个 UnicastProcessor 字符。这是因为 window 操作符所产生的流中包含的是 UnicastProcessor 类的对象，而
   * UnicastProcessor 类的 toString 方法输出的就是 UnicastProcessor 字符。
   */
  private static void window() {
    Flux.range(1, 100).window(20).subscribe(System.out::println);
    Flux.interval(Duration.ofMillis(100)).windowTimeout(2, Duration.ofMillis(1001)).toStream()
        .forEach(System.out::println);
  }

  /**
   * zipWith 操作符把当前流中的元素与另外一个流中的元素按照一对一的方式进行合并。在合并时可以不做任何处理，由此得到的是一个元素类型为 Tuple2 的流；也可以通过一个
   * BiFunction 函数对合并的元素进行处理，所得到的流的元素类型为该函数的返回值。
   *
   * 在代码清单 8 中，两个流中包含的元素分别是 a，b 和 c，d。第一个 zipWith 操作符没有使用合并函数，因此结果流中的元素类型为 Tuple2；第二个 zipWith
   * 操作通过合并函数把元素类型变为 String。
   */
  private static void zipWith() {
    //[a,c] [b,d]
    Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);
    //a-c b-d
    Flux.just("a", "b").zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
        .subscribe(System.out::println);
  }


  /**
   * take 系列操作符用来从当前流中提取元素。提取的方式可以有很多种。
   */
  private static void take() {
    Flux.range(1, 1000).take(10).subscribe(System.out::println);
    Flux.range(1, 1000).takeLast(10).subscribe(System.out::println);
    Flux.range(1, 1000).takeWhile(i -> i < 10).subscribe(System.out::println);
    Flux.range(1, 1000).takeUntil(i -> i == 10).subscribe(System.out::println);
  }

  /**
   * reduce 和 reduceWith reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作，得到一个包含计算结果的 Mono 序列。累积操作是通过一个
   * BiFunction 来表示的。在操作时可以指定一个初始值。如果没有初始值，则序列的第一个元素作为初始值。
   */
  private static void reduce() {
    //不带初始值 默认为第一个元素即下x = 1
    // 1 2 -> 3 3 -> 6 4 ...
    Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
    //带初始值 x = 100
    //100 1 -> 101 2
    Flux.range(1, 100).reduceWith(() -> 100, (x, y) -> x + y).subscribe(System.out::println);

  }

  /**
   * merge 和 mergeSequential 操作符用来把多个流合并成一个 Flux 序列。不同之处在于 merge 按照所有流中元素的实际产生顺序来合并，而
   * mergeSequential 则按照所有流被订阅的顺序，以流为单位进行合并。
   *
   * 代码清单 11 中分别使用了 merge 和 mergeSequential 操作符。进行合并的流都是每隔 100 毫秒产生一个元素，不过第二个流中的每个元素的产生都比第一个流要延迟 50
   * 毫秒。在使用 merge 的结果流中，来自两个流的元素是按照时间顺序交织在一起；而使用 mergeSequential 的结果流则是首先产生第一个流中的全部元素，再产生第二个流中的全部元素。
   */
  private static void merge() {
    //两个流变成一个流
    //(1) 表示第一个流产生的 (2) 表示第二个流产生的
    // 0(1) 0(2) 1(1) 1(2) 2(1) 2(2) 3(1) 3(2) 4(1) 4(2)
    Flux.merge(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5),
        Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
        .toStream()
        .forEach(System.out::println);
    // 0(1) 1(1) 2(1) 3(1) 4(1) 0(2) 1(2) 2(2) 3(2) 4(2)
    Flux.mergeSequential(Flux.interval(Duration.ofMillis(0), Duration.ofMillis(100)).take(5),
        Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(5))
        .toStream()
        .forEach(System.out::println);
  }


  /**
   * flatMap 和 flatMapSequential 操作符把流中的每个元素转换成一个流，再把所有流中的元素进行合并。flatMapSequential 和 flatMap 之间的区别与
   * mergeSequential 和 merge 之间的区别是一样的。
   *
   * 在代码清单 12 中，流中的元素被转换成每隔 100 毫秒产生的数量不同的流，再进行合并。由于第一个流中包含的元素数量较少，所以在结果流中一开始是两个流的元素交织在一起，然后就只有第二个流中的元素
   */
  private static void flatMap() {
    //第一个流延迟50 ms 第二个流延迟100 ms 变成两个流了
    Flux.just(5, 10)
        .flatMap(x -> Flux.interval(Duration.ofMillis(10 * x), Duration.ofMillis(100)).take(x))
        .toStream()
        .forEach(System.out::println);
  }

  /**
   * concatMap concatMap 操作符的作用也是把流中的每个元素转换成一个流，再把所有流进行合并。与 flatMap 不同的是，concatMap
   * 会根据原始流中的元素顺序依次把转换之后的流进行合并；与 flatMapSequential 不同的是，concatMap 对转换之后的流的订阅是动态进行的，而
   * flatMapSequential 在合并之前就已经订阅了所有的流。
   */
  private static void concatMap() {
    Flux.just(5, 10)
        .concatMap(x -> Flux.interval(Duration.ofMillis(10 * x), Duration.ofMillis(100)).take(x))
        .toStream()
        .forEach(System.out::println);
  }

  /**
   * combineLatest 操作符把所有流中的最新产生的元素合并成一个新的元素，作为返回结果流中的元素。只要其中任何一个流中产生了新的元素，合并操作就会被执行一次，结果流中就会产生新的元素。在
   * 代码清单 14 中，流中最新产生的元素会被收集到一个数组中，通过 Arrays.toString 方法来把数组转换成 String。
   */
  private static void combineLatest() {
    //[0, 0]
    //[0, 1]
    //[1, 1]
    //[1, 2]
    //[2, 2]
    //[2, 3]
    //[3, 3]
    //[3, 4]
    //[4, 4]
    //[4, 5]
    //[4, 6]
    //[4, 7]
    //[4, 8]
    //[4, 9]
    Flux.combineLatest(
        Arrays::toString,
        Flux.interval(Duration.ofMillis(100)).take(5),
        Flux.interval(Duration.ofMillis(50), Duration.ofMillis(100)).take(10)
    ).toStream().forEach(System.out::println);
  }

  /**
   * 消息的与
   */
  private static void concatWith() {
    Flux.just(1, 2)
        .concatWith(Flux.just(3, 5))
        //处理正常 与 异常消息
        .subscribe(System.out::println, System.err::println);
  }

  public static void main(String[] args) {
    concatWith();
  }
}
