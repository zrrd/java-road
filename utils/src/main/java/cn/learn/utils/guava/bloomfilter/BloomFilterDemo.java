package cn.learn.utils.guava.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 布隆过滤器
 *
 * @author shaoyijiong
 * @date 2019/12/2
 */
public class BloomFilterDemo {

  public static void main(String[] args) {
    // 总数量
    int total = 1000000;

    BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 1000, 0.000001);

    // 初始化 1000000 条数据到过滤器中
    for (int i = 0; i < total; i++) {
      bf.put("" + i);
    }
    // 判断是否在过滤器中
    int count = 0;
    for (int i = 0; i < total + 10000; i++) {
      if (bf.mightContain("" + i)) {
        count++;
      }
    }
    // 布隆过滤器可以检查值是 “可能在集合中” 还是 “绝对不在集合中”
    System.out.println("已经匹配数量" + count);
  }
}
