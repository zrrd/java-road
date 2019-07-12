package cn.learn.generic.generc2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaoyijiong
 * @date 2019/7/11
 */
public class GenericExtends {


  static class Fruit {

  }

  static class Apple extends Fruit {

  }

  static class Orange extends Fruit {

  }


  /**
   * <pre>
   * “Producer Extends” – 如果你需要一个只读List，用它来produce T，那么使用? extends T。
   * “Consumer Super” – 如果你需要一个只写List，用它来consume T，那么使用? super T。
   * </pre>
   */
  private static void t1() {
    List<? extends Fruit> list1 = new ArrayList<Fruit>();
    List<? extends Fruit> list2 = new ArrayList<Apple>();
    List<? extends Fruit> list3 = new ArrayList<Orange>();

    //能拿数据
    list1.add(null);
    Fruit fruit = list1.get(0);

    //不能塞数据
    //list1.add(new Apple());
    //list2.add(new Apple());

  }


  private static void t2() {
    List<? super Fruit> list1 = new ArrayList<>();
    //能add
    list1.add(new Apple());
    //get出来只能是object   需要强转
    Object object = list1.get(0);
    Fruit fruit = (Fruit) object;
  }


  public static <E> void append(List<E> list,Class<E> cls)
      throws IllegalAccessException, InstantiationException {
    //不能通过泛型创建对象
    //E elem = new E();

    //利用反射解决这个问题
    E elem = cls.newInstance();
    list.add(elem);
  }

  public static void main(String[] args) {
    t2();
  }


}
