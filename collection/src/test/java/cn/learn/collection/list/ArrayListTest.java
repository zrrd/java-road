package cn.learn.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.util.StopWatch;

/**
 * ArrayList的性能测试
 *
 * @author 邵益炯
 * @date 2018/9/20
 */
@RunWith(JUnit4.class)
public class ArrayListTest {

  @Test
  public void testTime() {
    StopWatch sw = new StopWatch();
    int size = 10000000;
    Random random = new Random();
    List<Integer> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      int a = random.nextInt(size);
      list.add(a);
    }

    Integer value;

    sw.start("通过iterator遍历");
    Iterator<Integer> integerIterator = list.iterator();
    while (integerIterator.hasNext()) {
      value = integerIterator.next();
    }
    sw.stop();

    sw.start("通过foreach遍历");
    for (Integer integer : list) {
      value = integer;
    }
    sw.stop();

    sw.start("通过随机访问遍历");
    for (int i = 0; i < size; i++) {
      value = list.get(i);
    }
    sw.stop();

    sw.start("通过list自带的foreach遍历");
    list.forEach(a->a.toString());
    sw.stop();

    /**
     * ms     %     Task name
     * -----------------------------------------
     * 00023  004%  通过iterator遍历
     * 00023  004%  通过foreach遍历
     * 00022  004%  通过随机访问遍历
     * 00549  089%  通过list自带的foreach遍历
     */
    System.out.println(sw.prettyPrint());
  }

  @Test
  public void toArrayTest() {
    List<Integer> list = new ArrayList<>();
    //这样会抛异常
    Integer[] integers = (Integer[]) list.toArray();

    integers = list.toArray(new  Integer[0]);
  }
}
