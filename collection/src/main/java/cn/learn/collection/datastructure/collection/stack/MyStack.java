package cn.learn.collection.datastructure.collection.stack;

/**
 * 通过数组实现栈
 *
 * @author 邵益炯
 * @date 2018/8/14
 */
public class MyStack<AnyType> {

  private Object[] data;
  /**
   * 栈容量
   */
  private int maxSize = 0;
  /**
   * 栈顶指针
   */
  private int top = -1;

  /**
   * 构造函数：根据给定的size初始化栈
   */
  MyStack() {
    //默认栈大小为10
    this(10);
  }

  MyStack(int initialSize) {
    if (initialSize >= 0) {
      this.maxSize = initialSize;
      data = new Object[initialSize];
      top = -1;
    } else {
      throw new RuntimeException("初始化大小不能小于0：" + initialSize);
    }
  }

  /**
   * 出栈
   *
   * @return 栈顶元素
   */
  public AnyType pop() {
    if (top == -1) {
      throw new RuntimeException("栈为空！");
    } else {
      return (AnyType) data[top--];
    }
  }

  /**
   * 进栈,第一个元素top=0
   */
  public boolean push(AnyType e) {
    if (top == maxSize - 1) {
      throw new RuntimeException("栈已满，无法将元素入栈！");
    } else {
      data[++top] = e;
      return true;
    }
  }
}
