package cn.learn.utils.guava.current;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;
import lombok.Data;
import org.apache.commons.lang3.RandomUtils;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.util.ClassUtils;

/**
 * 使用ListenableFuture来代替JDK的 Future
 *
 * @author shaoyijiong
 * @date 2019/7/9
 */
public class ListenableFutureTest {


  @Data
  private static class Explosion {

    private String name;

  }


  private Explosion pushBigRedButton() {
    System.out.println("创建对象");
    if (RandomUtils.nextInt(0, 10) > 5) {
      throw new RuntimeException();
    }
    Explosion explosion = new Explosion();
    explosion.setName("小A");
    return explosion;
  }

  /**
   * 测试1
   */
  public void test1() {

    //创建线程池
    ListeningExecutorService service = MoreExecutors
        .listeningDecorator(Executors.newFixedThreadPool(10));
    ListenableFuture<Explosion> explosion = service.submit(this::pushBigRedButton);

    Futures.addCallback(explosion, new FutureCallback<Explosion>() {

      @Override
      public void onSuccess(@Nullable Explosion result) {
        System.out.println("操作成功" + result.getName());
      }

      @Override
      public void onFailure(Throwable thrown) {
        System.out.println("操作失败" + thrown.getMessage());
      }
    }, service);
  }


  public static void main(String[] args) {
    new ListenableFutureTest().test1();
  }
}
