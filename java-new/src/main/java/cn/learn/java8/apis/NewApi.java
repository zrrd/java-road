package cn.learn.java8.apis;

import java.util.stream.Collectors;

/**
 * java8新的api
 *
 * @author 邵益炯
 * @date 2018/11/5
 */
public class NewApi {

  public static void main(String[] args) {
    String join = String.join(":", "foobar", "foo", "bar");
    // => foobar:foo:bar

    String s = "foobar:foo:bar"
        .chars()
        .distinct()
        .mapToObj(c -> String.valueOf((char) c))
        .sorted()
        .collect(Collectors.joining());
    // => :abfor
  }
}
