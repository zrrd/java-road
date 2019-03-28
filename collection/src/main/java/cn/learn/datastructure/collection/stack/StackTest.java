package cn.learn.datastructure.collection.stack;

import java.util.Stack;

/**
 * @author shaoyijiong
 * @date 2019/3/28
 */
public class StackTest {

  public static void main(String[] args) {
    //继承于Vector
    Stack<String> stack = new Stack<>();
    //向栈插入一个
    stack.push("a");
    stack.push("a");
    //栈头弹出一个
    stack.pop();
    //得到栈头元素不删除
    String peek = stack.peek();
    //查找栈中有多少元素
    int a = stack.search("a");
  }
}
