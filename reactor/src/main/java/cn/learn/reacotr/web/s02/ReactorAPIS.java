package cn.learn.reacotr.web.s02;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.var;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author shaoyijiong
 * @date 2020/6/17
 */
@SuppressWarnings("all")
public class ReactorAPIS {

  public static void createFluxFromExistingData() {
    var justFlux = Flux.just(1, 2, 3, 4);
    subscribeFlux("justFlux", justFlux);
    var arrayFlux = Flux.fromArray(new Integer[] {1, 2, 3, 4});
    subscribeFlux("arrayFlux", arrayFlux);
    var iterableFlux = Flux.fromIterable(Arrays.asList(1, 2, 3, 4));
    subscribeFlux("iterableFlux", iterableFlux);
    var streamFlux = Flux.fromStream(Stream.of(1, 2, 3, 4));
    subscribeFlux("streamFlux", streamFlux);
    var rangeFlux = Flux.range(1, 4);
    subscribeFlux("rangeFlux", rangeFlux);
  }

  public static void createFluxProgrammatically() {
    var generateFlux = Flux.generate(() -> 1, (state, sink) -> {
      sink.next("message #" + state);
      if (state == 10) {
        sink.complete();
      }
      return state + 1;
    });
    subscribeFlux("generateFlux", generateFlux);
  }

  public static void createMonoFromExistingData() {
    var justMono = Mono.just(1);
  }


  private static void createMonoAsync() {
    // 自己定义管道运行的线程
    var callableMono = Mono.fromCallable(() -> Thread.currentThread().getName() + "@" + LocalDateTime.now())
        // 在哪一个线程池里面跑
        .publishOn(Schedulers.elastic());
    var runnableMono =
        Mono.fromRunnable(() -> System.out.println(Thread.currentThread().getName() + "@" + LocalDateTime.now()))
            .publishOn(Schedulers.elastic());
    var supplierMono = Mono.fromSupplier(() -> Thread.currentThread().getName() + "@" + LocalDateTime.now())
        .publishOn(Schedulers.elastic());
    blockMono("callableMono", callableMono);
    blockMono("runnableMono", runnableMono);
    blockMono("supplierMono", supplierMono);
  }

  public static void useThenForFlow() {
    var thenMono = Mono.just("world").map(n -> "hello " + n).doOnNext(System.out::println)
        // 通过 then api 表示一个管道处理完了 , 返回一个结果 , 或者处理另外一个管道
        // 不要通过表面逻辑来定义管道的执行顺序 , 要通过 then , 因为两个管道可能运行在不同线程里面
        .thenReturn("do something else");
    blockMono("thenMono", thenMono);
  }

  public static void monoFluxInterchange() {
    var monoToFlux = Mono.just(1).flux();
    subscribeFlux("monoToFlux", monoToFlux);
    // 把管道中所有对象收集 => collection
    var fluxToMono = Flux.just(1, 2, 3).collectList();
    blockMono("fluxToMono", fluxToMono);
  }


  private static void mapVsFlatMap() {
    var mapFlux = Flux.just(1, 2, 3).map(i -> "id #" + i);
    subscribeFlux("mapFlux", mapFlux);
    var flatMapFlux = Flux.just(1, 2, 3).flatMap(i -> Mono.just("id #" + i));
    subscribeFlux("flatMapFlux", flatMapFlux);
  }

  private static void subscribeFlux(String varName, Flux<?> flux) {
    // 当subscribe流时
    flux.doOnSubscribe(s -> System.out.print(varName + ": "))
        // 流里面新的对象时
        .doOnNext(e -> System.out.print(e + ", "))
        // 流里面所有对象都处理完
        .doOnComplete(System.out::println)
        .subscribe();
  }


  /**
   * 场景 : 从不同地方获取用户信息 , 并且组装起来
   */
  private static void zipMonoOrFlux() {
    var userId = "max";
    var monoProfile = Mono.just(userId + "的详细信息");
    var monoLastOrder = Mono.just(userId + "的最新订单");
    var monoLastReview = Mono.just(userId + "的最新评论");
    // 等待3个管道都生产完 收集起来变成一个对象
    var zipMono = Mono.zip(monoProfile, monoLastOrder, monoLastReview)
        .doOnNext(t -> System.out.printf("%s的主页 %s %s%n", t.getT1(), t.getT2(), t.getT3()));
    blockMono("zipMono", zipMono);
  }

  /**
   * 处理异常 两种模式等价 , 比较推荐下一种
   */
  private static void errorHandling() {
    var throwExceptionFlux = Flux.range(1, 10).map(i -> {
      if (i > 5) {
        throw new RuntimeException("something wrong");
      }
      return "item #" + i;
    });
    subscribeFlux("throwExceptionFlux", throwExceptionFlux);
    var errorFlux = Flux.range(1, 10).flatMap(i -> {
      if (i > 5) {
        return Mono.error(new RuntimeException("something wrong"));
      }
      return Mono.just("item #" + i);
    });
    subscribeFlux("errorFlux", errorFlux);
  }

  private static void blockMono(String varName, Mono<?> mono) {
    mono.doOnSubscribe(s -> System.out.print(varName + ": "))
        .doOnNext(e -> System.out.println(e + ", "))
        // 会阻塞 直到管道中所有都消费 , 所以有一个返回值
        // 不推荐 , 违背 reactor 的初衷
        .block();
  }

  public static void main(String[] args) {
    errorHandling();
  }
}
