package cn.learn.collection.heap;

/**
 * 二叉堆.
 *
 * @author 邵益炯
 * @date 2018/9/25
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

  private static final int DEFAULT_CAPACITY = 10;
  private int cuttentSize;
  private AnyType[] array;

  public boolean isEmpty() {
    return cuttentSize == 0;
  }

  public AnyType finMin() {
    return array[1];
  }

  public void insert(AnyType x) {
    if (cuttentSize == array.length - 1) {
      //扩容
    }

    int hole = ++cuttentSize;
    //每次和他的父元素比较  如果小于父元素交换 找空位  不是交换哦
    for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
      array[hole] = array[hole / 2];
    }
    array[hole] = x;
  }

  public AnyType deleteMin() {
    if (isEmpty()) {
      throw new RuntimeException();
    }

    AnyType minItem = finMin();
    //找一个位置安放最后一个元素
    array[1] = array[cuttentSize--];
    percolateDown(1);
    return minItem;
  }

  private void percolateDown(int hole) {
    int child;
    AnyType tmp = array[hole];
    for (; hole * 2 <= cuttentSize; hole = child) {
      child = hole * 2;
      if (child != cuttentSize && array[child + 1].compareTo(array[child]) < 0) {

        child++;
      }
      if (array[child].compareTo(tmp) < 0) {
        array[hole] = array[child];
      } else {
        break;
      }
      array[hole] = tmp;
    }


  }
}
