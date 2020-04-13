package cn.learn.utils.guava.basicutilities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 排序和比较
 *
 * @author 邵益炯
 * @date 2018/9/28
 */
public class OrderingTest {

  @Data
  @AllArgsConstructor
  static class User {

    int age;
    String name;

  }

  private void test1() {
    //-------   排序规则 都是可以链式使用的!!!
    //自然排序
    Ordering<String> stringOrdering = Ordering.natural();
    //toString 后使用字典排序
    final Ordering<Object> usingToString = Ordering.usingToString();

    //创建自定义比较器 根据年龄排序 的一个相当于比较器的东西
    Ordering<User> userOrdering = new Ordering<User>() {
      @Override
      public int compare(User t1, User t2) {
        return Ints.compare(t1.getAge(), t2.getAge());
      }
    };
    //空 排最前 空排最后
    Ordering<User> nullsFirst = userOrdering.nullsFirst().nullsLast();
    //逆序
    Ordering<User> reverse = userOrdering.reverse();
    //字典序
    Ordering<Iterable<User>> lexicographical = userOrdering.lexicographical();
    //通过年龄比较
    Ordering.natural().nullsFirst().onResultOf((User u1)-> u1.getAge());

    ImmutableList<User> users = ImmutableList
        .of(new User(2, "a"), new User(5, "b"), new User(1, "c"), new User(9, "d"));
    stringOrdering.compare(users.get(0).getName(), users.get(1).getName());

    //------   进行排序

    //取排序后的前两个
    List<User> users1 = userOrdering.greatestOf(users, 2);
    //判断是顺序的
    boolean ordered = userOrdering.isOrdered(users);
    //返回一个拷贝的副本
    List<User> users2 = userOrdering.sortedCopy(users);
    //找最小 最大
    userOrdering.min(users);
    userOrdering.max(users);
  }
}
