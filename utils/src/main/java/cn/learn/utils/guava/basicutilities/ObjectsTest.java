package cn.learn.utils.guava.basicutilities;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import lombok.Data;

/**
 * Object 方法
 *
 * @author shaoyijiong
 * @date 2019/6/21
 */
@Data
public class ObjectsTest implements Comparable<ObjectsTest> {

  private Integer a;

  private String b;

  /**
   * 避免equal空指针异常
   */
  public void equal() {
    // returns true
    Objects.equal("a", "a");
    // returns false
    Objects.equal(null, "a");
    // returns false
    Objects.equal("a", null);
    // returns true
    Objects.equal(null, null);

  }


  /**
   * 通过比较链实现Comparable 方法
   */
  @Override
  public int compareTo(ObjectsTest o) {
    return ComparisonChain.start().compare(a, o.a).compare(b, o.b).result();
  }
}
