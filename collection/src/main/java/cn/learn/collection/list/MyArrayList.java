package cn.learn.collection.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 自建的数组
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

  /**
   * 数组长度
   */
  private int size;

  /**
   * 数组存储
   */
  private AnyType[] theItems;

  /**
   * 初始长度
   */
  private static final int DEFULT_CAPACITY = 10;

  public MyArrayList(int capacity) {
    size = 0;
    ensureCapacity(capacity);
  }

  /**
   * 构造
   */
  public MyArrayList() {
    doClear();
  }

  /**
   * 清空
   */
  public void clear() {
    doClear();
  }

  /**
   * 初始化操作
   */
  private void doClear() {
    size = 0;
    ensureCapacity(DEFULT_CAPACITY);

  }

  /**
   * 长度
   */
  public int size() {
    return size;
  }

  /**
   * 是否为空
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * 是否存在某元素
   */
  public boolean contains(Object o) {
    return indexOf(o) > 0;
  }

  /**
   * 序号
   */
  public int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (theItems[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (o.equals(theItems[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * 获取
   */
  public AnyType get(int idx) {
    if (idx < 0 && idx >= size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return theItems[idx];
  }

  /**
   * 设值
   */
  public AnyType set(int idx, AnyType newVal) {
    if (idx < 0 && idx >= size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    AnyType old = theItems[idx];
    theItems[idx] = newVal;
    return old;
  }

  /**
   * 扩容函数
   */
  public void ensureCapacity(int newCapacity) {
    if (newCapacity < size) {
      return;
    }
    AnyType[] old = theItems;
    theItems = (AnyType[]) new Object[newCapacity];
    for (int i = 0; i < size(); i++) {
      theItems[i] = old[i];
    }
  }

  /**
   * 添加
   */
  public boolean add(AnyType x) {
    add(size, x);
    return true;
  }

  public void add(int idx, AnyType x) {
    if (theItems.length == size()) {
      //将数组长度变为两倍
      ensureCapacity(size * 2 + 1);
    }
    //将idx后的都后移一位
    for (int i = size; i > idx; i--) {
      theItems[i] = theItems[i - 1];
    }
    theItems[idx] = x;
    size++;
  }

  /**
   * 删除
   */
  public AnyType remove(int idx) {
    AnyType removeItem = theItems[idx];
    for (int i = idx; i < size() - 1; i++) {
      theItems[i] = theItems[i + 1];
    }
    size--;
    return removeItem;
  }

  /**
   * 迭代器
   */
  private class ArrayListIterator implements Iterator<AnyType> {

    /**
     * 当前位置记录器
     */
    private int current = 0;

    /**
     * 是否有下一个
     */
    @Override
    public boolean hasNext() {
      return current < size();
    }

    /**
     * 下一个
     */
    @Override
    public AnyType next() {
      return theItems[current++];
    }

    /**
     * 移出
     */
    @Override
    public void remove() {
      MyArrayList.this.remove(--current);
    }
  }

  /**
   * 返回迭代器
   */
  @Override
  public Iterator<AnyType> iterator() {
    return new ArrayListIterator();
  }

  public Object[] toArray() {
    //返回的是一个拷贝对象  不直接返回数组
    return Arrays.copyOf(theItems, size);
  }
}
