package cn.learn.reacotr.core;

import java.time.Duration;
import reactor.core.publisher.Flux;

/**
 * 操作符
 *
 * @author shaoyijiong
 * @date 2019/5/12
 */
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

  public static void main(String[] args) {
    zipWith();
  }
}
