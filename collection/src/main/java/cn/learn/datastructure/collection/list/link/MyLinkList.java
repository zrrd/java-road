package cn.learn.datastructure.collection.list.link;

import java.util.Iterator;

/**
 * 自建的链表
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
public class MyLinkList<AnyType> implements Iterable<AnyType> {

  private static class Node<AnyType> {

    AnyType data;
    Node<AnyType> prev;
    Node<AnyType> next;

    Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
      data = d;
      prev = p;
      next = n;
    }
  }

  /**
   * 构建函数  构建头结点和尾结点
   */
  public MyLinkList() {
    doClear();
  }

  public void clear() {
    doClear();
  }

  private void doClear() {
    beginMarker = new Node<>(null, null, null);
    endMarker = new Node<>(null, beginMarker, null);
    beginMarker.next = endMarker;
    theSize = 0;
    modCount++;
  }

  public int size() {
    return theSize;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private void addBefore(Node<AnyType> p, AnyType x) {
    //插入到元素的前一个
    Node<AnyType> newNode = new Node<>(x, p.prev, p);
    newNode.prev.next = newNode;
    p.prev = newNode;
    theSize++;
    modCount++;
  }

  /**
   * 头结点
   */
  private Node<AnyType> beginMarker;
  /**
   * 尾结点
   */
  private Node<AnyType> endMarker;

  private int theSize;

  private int modCount;

  @Override
  public Iterator<AnyType> iterator() {
    return null;
  }


}
