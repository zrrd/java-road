import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shaoyijiong
 * @date 2019/7/16
 */
public class ReferenceGo {


  /**
   * <pre>
   * 强引用
   * 1.强引用可以直接访问目标对象。
   * 2.强引用所指向的对象在任何时候都不会被系统回收。JVM宁愿抛出OOM异常，也不会回收强引用所指向的对象。
   * 3.强引用可能导致内存泄漏。
   * </pre>
   */
  private static void referenceTest() {
    List<StringBuilder> list = new LinkedList<>();
    while (true) {
      list.add(new StringBuilder("Helloword"));
    }
  }

  /**
   * <pre>
   * 软引用 SoftReference的特点是它的一个实例保存对一个Java对象的软引用， 该软引用的存在不妨碍垃圾收集线程对该Java对象的回收。也就是说，
   * 一旦SoftReference保存了对一个Java对象的软引用后，在垃圾线程对这个Java对象回收前，
   * SoftReference类所提供的get()方法返回Java对象的强引用。一旦垃圾线程回收该Java对象之后，get()方法将返回null。
   * </pre>
   */
  private static void softReferenceTest() {
    // 初始化软引用
    String obj = new String("Hello World AAAAAAAAAA");
    SoftReference sf = new SoftReference<>(obj);
    obj = null;

    System.gc();
    byte[] bytes = new byte[1024 * 511];
    System.gc();

    System.out.println("是否被回收" + sf.get());

  }

  /**
   * <pre>
   * 弱引用是一种比软引用较弱的引用类型。在系统GC时，只要发现弱引用，
   * 不管系统堆空间是否足够，都会将对象进行回收。
   * 在java中，可以用java.lang.ref.WeakReference实例来保存对一个Java对象的弱引用。
   * </pre>
   */
  private static void weakReferenceTest() {
    String obj = new String("Hello World");
    WeakReference sf = new WeakReference<>(obj);
    obj = null;
    System.out.println("是否被回收" + sf.get());
    System.gc();
    System.out.println("是否被回收" + sf.get());
  }


  /**
   * 虚引用是所有类型中最弱的一个。一个持有虚引用的对象，和没有引用几乎是一样的，随时可能被垃圾回收器回收。当试图通过虚引用的get()方法取得强引用时，总是会失败。并且，虚引用必须和引用队列一起使用，它的作用在于跟踪垃圾回收过程。
   * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在垃圾回收后，销毁这个对象，将这个虚引用加入引用队列。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。
   */
  public void referenceQueueTest() {
    String obj = new String("Hello World");
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    PhantomReference sf = new PhantomReference<>(obj, referenceQueue);
    obj = null;
    System.out.println("是否被回收" + sf.get());
    System.gc();
    System.out.println("是否被回收" + sf.get());
  }

  /**
   * jvm 参数 -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError
   */
  public static void main(String[] args) {
    weakReferenceTest();
  }
}
