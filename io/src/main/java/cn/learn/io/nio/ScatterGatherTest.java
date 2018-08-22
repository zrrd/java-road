package cn.learn.io.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Scattering read指的是从通道读取的操作能把数据写入多个buffer，也就是sctters代表了数据从一个channel到多个buffer的过程。  gathering
 * write则正好相反，表示的是从多个buffer把数据写入到一个channel中。
 *
 * @author 邵益炯
 * @date 2018/8/22
 */
public class ScatterGatherTest {

  public static void main(String[] args) throws Exception {
    RandomAccessFile file = new RandomAccessFile("io/src/main/read.txt", "rw");
    FileChannel inChannel = file.getChannel();

    ByteBuffer header = ByteBuffer.allocate(128);
    ByteBuffer body = ByteBuffer.allocate(1024);

    ByteBuffer[] bufferArray = {header, body};
    //先写到header中去header写满再写到body中去
    inChannel.read(bufferArray);
    //按顺序写到 channel中去 按照buffer的字节
    inChannel.write(bufferArray);
  }
}
