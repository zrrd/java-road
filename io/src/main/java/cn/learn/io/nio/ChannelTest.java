package cn.learn.io.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Channel测试
 *
 * @author 邵益炯
 * @date 2018/8/21
 */
public class ChannelTest {

  public static void main(String[] args) throws Exception {
    RandomAccessFile file = new RandomAccessFile("io/src/main/read.txt", "rw");
    FileChannel inChannel = file.getChannel();
    //OpenOption 定义了文件Channel的操作 有 读 写 填 ...
    FileChannel inChannel1 = FileChannel
        .open(new File("io/src/main/read.txt").toPath(), StandardOpenOption.READ);
    ByteBuffer buf = ByteBuffer.allocate(48);
    int bytesRead = inChannel1.read(buf);
    byte[] b = new byte[1024];
    while (bytesRead != -1) {
      System.out.println("Read " + bytesRead);
      buf.flip();
      while(buf.hasRemaining()){
        b =  ArrayUtils.add(b, buf.get());
      }
      buf.clear();
      bytesRead = inChannel1.read(buf);
    }
    System.out.println(new String(b));
    file.close();
  }
}
