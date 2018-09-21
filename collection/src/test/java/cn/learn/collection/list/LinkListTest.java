package cn.learn.collection.list;

import java.util.LinkedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 链表数组的使用
 *
 * @author 邵益炯
 * @date 2018/9/20
 */
@RunWith(JUnit4.class)
public class LinkListTest {

  /**
   * LinkList 实现了Deque 接口 可以作为双端列表使用
   */
  @Test
  public void LinkListDeque() {
    LinkedList<String> linkedList = new LinkedList<>();
    //linkList 作为队列
  }
}
