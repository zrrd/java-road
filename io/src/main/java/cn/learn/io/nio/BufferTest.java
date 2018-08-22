package cn.learn.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 邵益炯
 * @date 2018/8/21
 */
public class BufferTest {

  private static void learn() throws Exception {
    RandomAccessFile aFile = new RandomAccessFile("io/src/main/read.txt", "rw");
    FileChannel inChannel = aFile.getChannel();

    //每个Buffer实现类都有一个allocate()方法用于分配内存
    ByteBuffer buf = ByteBuffer.allocate(48);

    //写数据到Buffer有两种方法：
    //
    //从Channel中写数据到Buffer
    //手动写数据到Buffer，调用put方法
    //bytesRead -1 表示读完了
    int bytesRead = inChannel.read(buf);
    buf.put("hello".getBytes());

    //flip()方法可以吧Buffer从写模式切换到读模式。调用flip方法会把position归零，并设置limit为之前的position的值。
    //也就是说，现在position代表的是读取位置，limit标示的是已写入的数据位置。
    buf.flip();

    //从Buffer读数据也有两种方式。
    //
    //从buffer读数据到channel
    //从buffer直接读取数据，调用get方法
    int bytesWritten = inChannel.write(buf);
    byte aByte = buf.get();

    //clear() and compact()
    //一旦我们从buffer中读取完数据，需要复用buffer为下次写数据做准备。只需要调用clear或compact方法。
    //clear方法会重置position为0，limit为capacity，也就是整个Buffer清空。实际上Buffer中数据并没有清空，我们只是把标记为修改了。
    //如果Buffer还有一些数据没有读取完，调用clear就会导致这部分数据被“遗忘”，因为我们没有标记这部分数据未读。
    //针对这种情况，如果需要保留未读数据，那么可以使用compact。 因此compact和clear的区别就在于对未读数据的处理，是保留这部分数据还是一起清空。
    buf.clear();
    buf.compact();
  }

  /**
   * buffer 三个属性
   * 容量 (Capacity)
   * 位置 (Position)
   * 当写入数据到Buffer的时候需要中一个确定的位置开始，默认初始化时这个位置position为0，一旦写入了数据比如一个字节，整形数据，
   * 那么position的值就会指向数据之后的一个单元，position最大可以到capacity-1.
   * 当从Buffer读取数据时，也需要从一个确定的位置开始。buffer从写入模式变为读取模式时，position会归零，每次读取后，position向后移动。
   * 上限 (Limit)
   * 在写模式，limit的含义是我们所能写入的最大数据量。它等同于buffer的容量。
   * 一旦切换到读模式，limit则代表我们所能读取的最大数据量，他的值等同于写模式下position的位置。
   * 数据读取的上限时buffer中已有的数据，也就是limit的位置（原position所指的位置）。
   *
   * 利用Buffer读写数据，通常遵循四个步骤：
   * 把数据写入buffer; 调用flip; 从Buffer中读取数据; 调用buffer.clear()或者buffer.compact()
   */
  public static void main(String[] args) throws Exception {
    RandomAccessFile aFile = new RandomAccessFile("io/src/main/read.txt", "rw");
    FileChannel inChannel = aFile.getChannel();
    //创建一个48字节的缓存
    ByteBuffer buf = ByteBuffer.allocate(48);
    //读到buffer中去
    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {
      //使buffer准备好读数据 Position 到 0
      buf.flip();
      while (buf.hasRemaining()) {
        //每次读一个字节  可以传入byte数组每次读byte
        System.out.print((char) buf.get());
      }
      //使buffer准备好写 Position 到 0
      buf.clear();
      bytesRead = inChannel.read(buf);
    }
    aFile.close();
  }
}
