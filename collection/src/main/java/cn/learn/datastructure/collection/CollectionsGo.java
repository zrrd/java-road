package cn.learn.datastructure.collection;

import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * @author shaoyijiong
 * @date 2019/8/28
 */
class CollectionsGo {

  /**
   * <pre>
   * Collections 的emptyMap 返回的是一个特殊的map 无法存放元素 如果要存放的话 会抛出UnsupportedOperationException 异常
   * 包括 emptyList emptySet 再增删元素的时候 代码中都会直接抛出 UnsupportedOperationException 异常
   * </pre>
   */
  @Test
  void test1() {
    Map<Object, Object> emptyMap = Collections.emptyMap();
    emptyMap.put("a", "a");
  }
}
