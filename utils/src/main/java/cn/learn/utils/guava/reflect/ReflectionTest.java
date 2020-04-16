package cn.learn.utils.guava.reflect;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * https://github.com/google/guava/wiki/ReflectionExplained
 *
 * @author shaoyijiong
 * @date 2020/4/16
 */
@SuppressWarnings("all")
public class ReflectionTest {

  private static void typeTokenTest() {
    ArrayList<String> stringList = Lists.newArrayList();
    ArrayList<Integer> intList = Lists.newArrayList();
    System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
// returns true, even though ArrayList<String> is not assignable from ArrayList<Integer>

    TypeToken<String> stringTok = TypeToken.of(String.class);
    TypeToken<Integer> intTok = TypeToken.of(Integer.class);
    TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {
    };
    TypeToken<Map<?, ?>> wildMapTok = new TypeToken<Map<?, ?>>() {
    };


  }

  public static void main(String[] args) {
    typeTokenTest();
  }
}
