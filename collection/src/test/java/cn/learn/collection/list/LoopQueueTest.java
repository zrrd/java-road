package cn.learn.collection.list;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoopQueueTest {

  @Test
  public void testQueue() {
    LoopQueue<String> loopQueue = new LoopQueue<>();
    loopQueue.add("a");
    loopQueue.add("b");
    loopQueue.add("c");

    System.out.println(loopQueue.peek());
    System.out.println(loopQueue.empty());
    System.out.println(loopQueue.toString());

    System.out.println(loopQueue.poll());
    System.out.println(loopQueue.length());
  }
}