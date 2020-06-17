package cn.learn.reacotr.core.reactive;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import org.apache.commons.lang3.RandomUtils;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * 核心api
 *
 * @author shaoyijiong
 * @date 2020/6/17
 */
@SuppressWarnings("all")
public class CoreFeatures {

  // https://projectreactor.io/docs/core/release/reference/index.html#core-features

  /**
   * 创建 Flux 或 Mono 简单方法以及消费
   */
  public static void createFluxOrMono() {
    // Flux
    Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
    List<String> iterable = Arrays.asList("foo", "bar", "foobar");
    Flux<String> seq2 = Flux.fromIterable(iterable);

    // Mono
    Mono<String> noData = Mono.empty();
    Mono<String> data = Mono.just("foo");
    // 
    Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);
  }

  /**
   * 消费消息
   */
  public static void subscribe() {
    Flux<Integer> ints1 = Flux.range(1, 3);
    ints1.subscribe();

    Flux<Integer> ints2 = Flux.range(1, 3);
    // 接收一个consumer , 对流中每个元素执行的操作
    ints2.subscribe(i -> System.out.println(i));

    Flux<Integer> ints3 = Flux.range(1, 4)
        .map(i -> {
          if (i <= 3) {
            return i;
          }
          throw new RuntimeException("Got to 4");
        });
    // 添加异常的处理
    ints3.subscribe(i -> System.out.println(i),
        error -> System.err.println("Error: " + error));

    Flux<Integer> ints4 = Flux.range(1, 4);
    // 当所有元素消费完后执行
    ints4.subscribe(i -> System.out.println(i),
        error -> System.err.println("Error " + error),
        () -> System.out.println("Done"));

    Flux<Integer> ints5 = Flux.range(1, 4);
    // 最多消费10个元素
    ints5.subscribe(i -> System.out.println(i),
        error -> System.err.println("Error " + error),
        () -> System.out.println("Done"),
        sub -> sub.request(10));
  }

  public static void cancel() {
    Flux<Integer> ints1 = Flux.range(1, 3);
    final Disposable subscribe = ints1.subscribe(i -> {
      try {
        System.out.println(i);
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    subscribe.dispose();
  }

  /**
   * 消费对象
   */
  public static class SampleSubscriber<T> extends BaseSubscriber<T> {

    /**
     * 开始订阅时
     */
    @Override
    public void hookOnSubscribe(Subscription subscription) {
      System.out.println("Subscribed");
      request(1);
    }

    /**
     * 消费
     */
    @Override
    public void hookOnNext(T value) {
      System.out.println(value);
      // cancel(); 取消当前订阅
      // 从订阅中拿一个元素
      request(1);
      // requestUnbounded();
    }
  }

  public static void sampleSubscriber() {
    SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
    Flux<Integer> ints = Flux.range(1, 11);
    ints.subscribe(i -> System.out.println(i),
        error -> System.err.println("Error " + error),
        () -> {
          System.out.println("Done");
        },
        s -> s.request(10));
    ints.subscribe(ss);
  }

  /**
   * 背压
   */
  public static void backpressure() {
    Flux.range(1, 10)
        .doOnRequest(r -> System.out.println("request of " + r))
        .subscribe(new BaseSubscriber<Integer>() {

          @Override
          public void hookOnSubscribe(Subscription subscription) {
            request(1);
          }

          @Override
          public void hookOnNext(Integer integer) {
            System.out.println("Cancelling after having received " + integer);
            // requestUnbounded(); 等价于 request(Long.MAX_VALUE) 上游尽可能快的生成
            // 每次至少调用一次 request 才能完成接下来的操作
            cancel();
          }
        });
  }

  /**
   * 程序的方式创建流
   */
  public static void programmatically() {
    // 同步地创建
    Flux<String> flux = Flux.generate(
        // 初始值 0
        () -> 0, (state, sink) -> {
          // 产生消息
          sink.next("3 x " + state + " = " + 3 * state);
          // 何时停止 注释下面代码会导致无限流
          if (state == 10) {
            sink.complete();
          }
          // 在下一次禅师的新状态
          return state + 1;
        });

    Flux<String> flux1 = Flux.generate(
        AtomicLong::new, (state, sink) -> {
          long i = state.getAndIncrement();
          sink.next("3 x " + i + " = " + 3 * i);
          if (i == 10) {
            sink.complete();
          }
          return state;
        }, (state) -> System.out.println("state: " + state));
  }

  interface MyEventListener<T> {

    /**
     * 数据块完成
     */
    void onDataChunk(List<T> chunk);

    /**
     * 处理完成
     */
    void processComplete();
  }

  static class MyEventProcessor<T> {

    MyEventListener<T> listener;

    /**
     * 注册监听器
     */
    void register(MyEventListener<T> listener) {
      this.listener = listener;
    }

    void create(Supplier<T> supplier) {
      // 产生10批数据
      for (int i = 0; i < 10; i++) {
        final ArrayList<T> list = Lists.newArrayListWithCapacity(i);
        // 生成长度为i的数据
        for (int j = 0; j < i + 1; j++) {
          list.add(supplier.get());
        }
        System.out.println("生产了" + list.size() + "条数据");
        listener.onDataChunk(list);
      }
      listener.processComplete();
    }

  }

  /**
   * 异步多线程创建流的方式
   */
  public static void create() {
    // 生产者
    MyEventProcessor<String> processor = new MyEventProcessor<>();
    // 将生产者生成的数据转为流 可以通过 OverflowStrategy 来改变背压状态
    Flux<String> bridge = Flux.create(sink -> {
      processor.register(
          new MyEventListener<String>() {
            @Override
            public void onDataChunk(List<String> chunk) {
              for (String s : chunk) {
                sink.next(s);
              }
            }

            @Override
            public void processComplete() {
              sink.complete();
            }
          });
    });
    // 订阅数据
    bridge.subscribe(System.out::println);
    // 生产数据
    processor.create(() -> String.valueOf(RandomUtils.nextInt()));
  }

  /**
   * 异步单线程
   */
  public static void push() {
    // 生产者
    MyEventProcessor<String> processor = new MyEventProcessor<>();
    // 将生产者生成的数据转为流 可以通过 OverflowStrategy 来改变背压状态
    Flux<String> bridge = Flux.push(sink -> {
      processor.register(
          new MyEventListener<String>() {
            @Override
            public void onDataChunk(List<String> chunk) {
              for (String s : chunk) {
                sink.next(s);
              }
            }

            @Override
            public void processComplete() {
              sink.complete();
            }
          });
    });
    // 订阅数据
    bridge.subscribe(System.out::println);
    // 生产数据
    processor.create(() -> String.valueOf(RandomUtils.nextInt()));
  }

  public static void handle() {
    // 在流中过滤空值
    Flux<String> alphabet = Flux.just("", "30", "13", "9", "20")
        .handle((i, sink) -> {
          if (i != "") {
            sink.next(i);
          }
        });
    alphabet.subscribe(System.out::println);
  }

  public static void schedule() {
    Scheduler s = Schedulers.newParallel("parallel-scheduler", 4);
    final Flux<String> flux = Flux
        .range(1, 2)
        .map(i -> 10 + i)
        .publishOn(s)
        .map(i -> "value " + i);
    new Thread(() -> flux.subscribe(System.out::println));
  }

  public static void main(String[] args) throws InterruptedException {
    schedule();
  }
}
