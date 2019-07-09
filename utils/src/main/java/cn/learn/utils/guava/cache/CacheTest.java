package cn.learn.utils.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoyijiong
 * @date 2019/7/8
 */
public class CacheTest {

  static class CustomRemovalListener implements RemovalListener<String, Integer> {

    @Override
    public void onRemoval(RemovalNotification notification) {
      System.out.println(notification);
    }
  }


  public void cacheTest() {

    LoadingCache graphs = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .removalListener(new CustomRemovalListener())
        .build(
            new CacheLoader<String, Integer>() {
              @Override
              public Integer load(String key) {
                return null;
              }
            });
  }
}
