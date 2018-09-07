package cn.learn.collection.list;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MyArrayListTest {

  @Test
  public void arrayListTest() {
    MyArrayList<String> arrayList = new MyArrayList<>(2);
    arrayList.add("a");

  }
}