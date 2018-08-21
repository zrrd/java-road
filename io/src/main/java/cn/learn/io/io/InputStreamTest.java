package cn.learn.io.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author 邵益炯
 * @date 2018/8/21
 */
public class InputStreamTest {

  public static void main(String[] args) throws Exception {
    //通过try with resource 自动关闭流
    try (BufferedInputStream inputStream = new BufferedInputStream(
        new FileInputStream(new File("io/src/main/read.txt")))) {
      //将读取的存入字节数组中
      byte[] b = new byte[10];
      int length;
      byte[] all = new byte[1024];
      while ((length = inputStream.read(b)) != -1) {
        all = ArrayUtils.addAll(all, Arrays.copyOf(b, length));
        //由于一个字符存在几个字节  可能导致字符乱码
        System.out.print(new String(Arrays.copyOf(b, length)));
      }
      System.out.println();
      System.out.println("所有:" + new String(all));
    }

  }
}
