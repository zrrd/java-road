package cn.learn.utils.guava.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.collect.ImmutableList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoyijiong
 * @date 2019/7/8
 */
public class CacheTest {

  private Cache<String, Integer> cache;

  /**
   * 删除时打印元素
   */
  static class CustomRemovalListener implements RemovalListener<String, Integer> {

    @Override
    public void onRemoval(RemovalNotification notification) {
      System.out.println(notification);
    }
  }


  private void init() {
    cache = CacheBuilder.newBuilder()
        // 存储最大数量
        .maximumSize(1000)
        // 过期事件
        .expireAfterWrite(10, TimeUnit.MINUTES)
        // 删除时触发的事件
        .removalListener(new CustomRemovalListener())
        .build();
    // 还有其他配置 如 并发等级 key value 是否为弱引用 初始化大小
    // 初始化元素 定时回收时间 开启统计功能 实现自己的缓存CacheLoader  权重(用于定义回收策略)
  }

  private void get() throws ExecutionException {
    // 获取缓存 如果没有则计算 这里时插入1
    cache.get("", () -> 1);
    // 有缓存返回
    cache.getIfPresent("");
  }

  private void put() {
    // 插入
    cache.put("", 1);
  }

  private void clear() {
    // 个别清除
    cache.invalidate("");
    // 清除所有
    cache.invalidateAll(ImmutableList.of(""));
    // 清除所有
    cache.invalidateAll();
    cache.cleanUp();
  }
}
