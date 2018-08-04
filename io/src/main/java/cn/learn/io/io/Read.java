package cn.learn.io.io;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author shaoyijiong
 */
public class Read {

  public static void main(String[] args) {
    //缓存字节
    byte[] b = new byte[3];
    InputStream in;
    try {
      //把所有的数据都放到缓存中去
      in = new BufferedInputStream(
          new FileInputStream("D:\\code\\javaroad\\io\\src\\main\\read.txt"));
      //in.read()读一个字符  这样都中文会有乱码  因为中文需要两个字节而这样只能一个个字节都的
      //    while ((i = in.read())!=-1){
      //        System.out.print((char)i);
      //    }

      //将数据存到缓存字节b中 每次都两个字节(byte的数组大小)
      //不放缓存直接读取in.read(b)
      while (in.read(b) != -1) {
        //byte 和 String 的互相转换
        String str = new String(b);
        System.out.print(str);
      }
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
