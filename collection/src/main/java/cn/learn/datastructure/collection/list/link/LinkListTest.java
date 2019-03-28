package cn.learn.datastructure.collection.list.link;

import java.util.LinkedList;

/**
 * 链表数组的使用
 *
 * @author 邵益炯
 * @date 2018/9/20
 */
public class LinkListTest {

  /**
   * LinkList 实现了Deque 接口 可以作为双端列表使用
   */
  public void LinkListDeque() {
    LinkedList<String> linkedList = new LinkedList<>();
    //linkList 作为队列
    //队尾入
    boolean b = linkedList.offerLast("");
    //队头入
    boolean b1 = linkedList.offerFirst("");
    //拿队头不移除
    String s = linkedList.peekFirst();
    //拿队尾不移除
    String s1 = linkedList.peekLast();
    //移除队头并返回
    String s2 = linkedList.pollFirst();
    //移除队尾并返回
    String s3 = linkedList.pollLast();
  }
}
