import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * @author shaoyijiong
 * @date 2020/4/1
 */
@SuppressWarnings("all")
public class JustMockitoTest {

  /**
   * 创建mock对象
   */
  @Test
  public void createMockObject() {
    // 使用 mock 静态方法创建 Mock 对象.
    List mockedList = mock(List.class);
    Assert.assertTrue(mockedList instanceof List);

    // mock 方法不仅可以 Mock 接口类, 还可以 Mock 具体的类型.
    ArrayList mockedArrayList = mock(ArrayList.class);
    Assert.assertTrue(mockedArrayList instanceof List);
    Assert.assertTrue(mockedArrayList instanceof ArrayList);
  }


  /**
   * 配置mock对象
   */
  @Test
  public void configMockObject() {
    List mockedList = mock(List.class);

    // 我们定制了当调用 mockedList.add("one") 时, 返回 true
    when(mockedList.add("one")).thenReturn(true);
    // 当调用 mockedList.size() 时, 返回 1
    when(mockedList.size()).thenReturn(1);

    Assert.assertTrue(mockedList.add("one"));
    // 因为我们没有定制 add("two"), 因此返回默认值, 即 false.
    Assert.assertFalse(mockedList.add("two"));
    Assert.assertEquals(mockedList.size(), 1);

    Iterator i = mock(Iterator.class);
    // 调用多次next时 会依次返回 Hello, Mockito
    when(i.next()).thenReturn("Hello,").thenReturn("Mockito!");
    String result = i.next() + " " + i.next();
    //assert
    Assert.assertEquals("Hello, Mockito!", result);
  }


  /**
   * 期望抛出 NoSuchElementException , 不会再控制台打印
   */
  @Test(expected = NoSuchElementException.class)
  public void testForIOException() throws Exception {
    Iterator i = mock(Iterator.class);
    when(i.next()).thenReturn("Hello,").thenReturn("Mockito!"); // 1
    String result = i.next() + " " + i.next(); // 2
    Assert.assertEquals("Hello, Mockito!", result);

    doThrow(new NoSuchElementException()).when(i).next(); // 3
    i.next(); // 4
  }


  /**
   * Mockito 会追踪 Mock 对象的所用方法调用和调用方法时所传递的参数. 我们可以通过 verify() 静态方法来来校验指定的方法调用是否满足断言
   */
  @Test
  public void testVerify() {
    List mockedList = mock(List.class);
    mockedList.add("one");
    mockedList.add("two");
    mockedList.add("three times");
    mockedList.add("three times");
    mockedList.add("three times");
    when(mockedList.size()).thenReturn(5);
    Assert.assertEquals(mockedList.size(), 5);

    // 第一句校验 mockedList.add("one") 至少被调用了 1 次(atLeastOnce)
    verify(mockedList, atLeastOnce()).add("one");
    // 第二句校验 mockedList.add("two") 被调用了 1 次(times(1))
    verify(mockedList, times(1)).add("two");
    // 第三句校验 mockedList.add("three times") 被调用了 3 次(times(3))
    verify(mockedList, times(3)).add("three times");
    // 第四句校验 mockedList.isEmpty() 从未被调用(never)
    verify(mockedList, never()).isEmpty();
  }

  /**
   * 使用 spy() 部分模拟对象
   * <p>Mockito 提供的 spy 方法可以包装一个真实的 Java 对象, 并返回一个包装后的新对象.
   * <P>若没有特别配置的话, 对这个新对象的所有方法调用, 都会委派给实际的 Java 对象
   */
  @Test
  public void testSpy() {
    List list = new LinkedList();
    List spy = spy(list);

    // 对 spy.size() 进行定制.
    when(spy.size()).thenReturn(100);

    spy.add("one");
    spy.add("two");

    // 因为我们没有对 get(0), get(1) 方法进行定制,
    // 因此这些调用其实是调用的真实对象的方法.
    Assert.assertEquals(spy.get(0), "one");
    Assert.assertEquals(spy.get(1), "two");

    Assert.assertEquals(spy.size(), 100);
  }

  /**
   * 参数捕获
   * <p>Mockito 允准我们捕获一个 Mock 对象的方法调用所传递的参数
   */
  @Test
  public void testCaptureArgument() {
    List<String> list = Arrays.asList("1", "2");
    List mockedList = mock(List.class);
    // 参数对象
    ArgumentCaptor<List> argument = ArgumentCaptor.forClass(List.class);
    mockedList.addAll(list);
    verify(mockedList).addAll(argument.capture());
    
    Assert.assertEquals(2, argument.getValue().size());
    Assert.assertEquals(list, argument.getValue());
  }
}
