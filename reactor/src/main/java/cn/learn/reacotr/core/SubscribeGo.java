package cn.learn.reacotr.core;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * 处理消息订阅
 *
 * @author shaoyijiong
 * @date 2019/5/13
 */
public class SubscribeGo {

  /**
   * 当需要处理 Flux 或 Mono 中的消息时，如之前的代码清单所示，可以通过 subscribe 方法来添加相应的订阅逻辑。在调用 subscribe
   * 方法时可以指定需要处理的消息类型。可以只处理其中包含的正常消息，也可以同时处理错误消息和完成消息。代码清单 15 中通过 subscribe()方法同时处理了正常消息和错误消息。
   */
  private static void subscribe() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        //处理正常 与 异常消息
        .subscribe(System.out::println, System.err::println);
  }

  /**
   * 正常的消息处理相对简单。当出现错误时，有多种不同的处理策略。第一种策略是通过 onErrorReturn()方法返回一个默认值。在代码清单 16 中，当出现错误时，流会产生默认值 0.
   */
  private static void onErrorReturn() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .onErrorReturn(0)
        .subscribe(System.out::println);

  }

  /**
   * 第三种策略是通过 onErrorResumeWith()方法来根据不同的异常类型来选择要使用的产生元素的流。在代码清单 18 中，根据异常类型来返回不同的流作为出现错误时的数据来源。因为异常的类型为
   * IllegalArgumentException，所产生的元素为-1。
   *
   * 清单 18. 出现错误时根据异常类型来选择流
   */
  private static void onErrorResume() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalArgumentException()))
        .onErrorResume(e -> {
          if (e instanceof IllegalStateException) {
            return Mono.just(0);
          } else if (e instanceof IllegalArgumentException) {
            return Mono.just(-1);
          }
          return Mono.empty();
        }).subscribe(System.out::println);
  }

  /**
   * 当出现错误时，还可以通过 retry 操作符来进行重试。重试的动作是通过重新订阅序列来实现的。在使用 retry 操作符时可以指定重试的次数。代码清单 19 中指定了重试次数为
   * 1，所输出的结果是 1，2，1，2 和错误信息。
   */
  private static void retry() {
    Flux.just(1, 2)
        .concatWith(Mono.error(new IllegalStateException()))
        .retry(1)
        .subscribe(System.out::println);

  }

  /**
   * 调度器
   * <pre>
   *   前面介绍了反应式流和在其上可以进行的各种操作，通过调度器（Scheduler）可以指定这些操作执行的方式和所在的线程。有下面几种不同的调度器实现。
   *
   * 1.当前线程，通过 Schedulers.immediate()方法来创建。
   * 2.单一的可复用的线程，通过 Schedulers.single()方法来创建。
   * 3.使用弹性的线程池，通过 Schedulers.elastic()方法来创建。线程池中的线程是可以复用的。当所需要时，新的线程会被创建。如果一个线程闲置太长时间，则会被销毁。该调度器适用于 I/O 操作相关的流的处理。
   * 4.使用对并行操作优化的线程池，通过 Schedulers.parallel()方法来创建。其中的线程数量取决于 CPU 的核的数量。该调度器适用于计算密集型的流的处理。
   * 5.使用支持任务调度的调度器，通过 Schedulers.timer()方法来创建。
   * 6.从已有的 ExecutorService 对象中创建调度器，通过 Schedulers.fromExecutorService()方法来创建。
   * 某些操作符默认就已经使用了特定类型的调度器。比如 intervalMillis()方法创建的流就使用了由 Schedulers.timer()创建的调度器。通过 publishOn()和 subscribeOn()方法可以切换执行操作的调度器。其中 publishOn()方法切换的是操作符的执行方式，而 subscribeOn()方法切换的是产生流中元素时的执行方式。
   *
   * 在代码清单 20 中，使用 create()方法创建一个新的 Flux 对象，其中包含唯一的元素是当前线程的名称。接着是两对 publishOn()和 map()方法，其作用是先切换执行时的调度器，
   * 再把当前的线程名称作为前缀添加。最后通过 subscribeOn()方法来改变流产生时的执行方式。运行之后的结果是[elastic-2] [single-1] parallel-1。最内层的线程名字 parallel-1 来自产生流中元素时使用的 Schedulers.parallel()调度器，
   * 中间的线程名称 single-1 来自第一个 map 操作之前的 Schedulers.single()调度器，最外层的线程名字 elastic-2 来自第二个 map 操作之前的 Schedulers.elastic()调度器。
   * </pre>
   */
  private static void scheduler() {
    Flux.create(sink -> {
      sink.next(Thread.currentThread().getName());
      sink.complete();
    })
        .publishOn(Schedulers.single())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .publishOn(Schedulers.elastic())
        .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
        .subscribeOn(Schedulers.parallel())
        .toStream()
        .forEach(System.out::println);
  }


  public static void main(String[] args) {
    scheduler();
  }
}
