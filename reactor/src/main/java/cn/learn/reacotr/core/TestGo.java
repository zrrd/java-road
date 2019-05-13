package cn.learn.reacotr.core;

import java.time.Duration;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * 测试
 *
 * @author shaoyijiong
 * @date 2019/5/13
 */
public class TestGo {

  /**
   * 进行测试时的一个典型的场景是对于一个序列，验证其中所包含的元素是否符合预期。StepVerifier 的作用是可以对序列中包含的元素进行逐一验证。在代码清单 21 中，需要验证的流中包含 a
   * 和 b 两个元素。通过 StepVerifier.create()方法对一个流进行包装之后再进行验证。expectNext()方法用来声明测试时所期待的流中的下一个元素的值，而
   * verifyComplete()方法则验证流是否正常结束。类似的方法还有 verifyError()来验证流由于错误而终止。
   */
  private static void expectNext() {
    StepVerifier.create(Flux.just("a", "b"))
        .expectNext("a")
        .expectNext("b")
        .verifyComplete();
  }

  /**
   * 有些序列的生成是有时间要求的，比如每隔 1 分钟才产生一个新的元素。在进行测试中，不可能花费实际的时间来等待每个元素的生成。此时需要用到 StepVerifier 提供的虚拟时间功能。通过
   * StepVerifier.withVirtualTime()方法可以创建出使用虚拟时钟的 StepVerifier。通过 thenAwait(Duration)方法可以让虚拟时钟前进。
   *
   * 在代码清单 22 中，需要验证的流中包含两个产生间隔为一天的元素，并且第一个元素的产生延迟是 4 个小时。在通过 StepVerifier.withVirtualTime()方法包装流之后，expectNoEvent()方法用来验证在
   * 4 个小时之内没有任何消息产生，然后验证第一个元素 0 产生；接着 thenAwait()方法来让虚拟时钟前进一天，然后验证第二个元素 1 产生；最后验证流正常结束。
   */
  private static void await() {
    StepVerifier
        .withVirtualTime(() -> Flux.interval(Duration.ofHours(4), Duration.ofDays(1)).take(2))
        .expectSubscription()
        .expectNoEvent(Duration.ofHours(4))
        .expectNext(0L)
        .thenAwait(Duration.ofDays(1))
        .expectNext(1L)
        .verifyComplete();
  }

  /**
   * TestPublisher 的作用在于可以控制流中元素的产生，甚至是违反反应流规范的情况。在代码清单 23 中，通过 create()方法创建一个新的 TestPublisher
   * 对象，然后使用 next()方法来产生元素，使用 complete()方法来结束流。TestPublisher 主要用来测试开发人员自己创建的操作符。
   */
  private static void testPublisher() {
    final TestPublisher<String> testPublisher = TestPublisher.create();
    testPublisher.next("a");
    testPublisher.next("b");
    testPublisher.complete();

    StepVerifier.create(testPublisher)
        .expectNext("a")
        .expectNext("b")
        .expectComplete();
  }

  private static void checkpoint() {
    Flux.just(1, 0).map(x -> 1 / x).checkpoint("test").subscribe(System.out::println);
  }

  private static void log() {
    Flux.range(1, 2).log("Range").subscribe(System.out::println);
  }

  public static void main(String[] args) {
    log();
  }
}
