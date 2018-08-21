package cn.learn.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 邵益炯
 * @date 2018/8/21
 */
public class BufferTest {

  /**
   * 利用Buffer读写数据，通常遵循四个步骤：
   *
   * 把数据写入buffer; 调用flip; 从Buffer中读取数据; 调用buffer.clear()或者buffer.compact()
   */
  public static void main(String[] args) throws Exception {
    RandomAccessFile aFile = new RandomAccessFile("io/src/main/read.txt", "rw");
    FileChannel inChannel = aFile.getChannel();
    //create buffer with capacity of 48 bytes 创建一个48字节的缓存
    ByteBuffer buf = ByteBuffer.allocate(48);
    //read into buffer. 读到buffer中去
    int bytesRead = inChannel.read(buf);
    while (bytesRead != -1) {
      //make buffer ready for read 使buffer准备好读
      buf.flip();
      while (buf.hasRemaining()) {
        // read 1 byte at a time 每次读一个字节  可以传入byte数组每次读byte
        System.out.print((char) buf.get());
      }
      //make buffer ready for writing
      buf.clear();
      bytesRead = inChannel.read(buf);
    }
    aFile.close();
  }
}
