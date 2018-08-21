package cn.learn.io.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author 邵益炯
 * @date 2018/8/20
 */
public class ReaderTest {

  public static void main(String[] args) throws Exception {
    //字符流读取文件  编码方式  BufferedReader 读取
    Reader reader = new InputStreamReader(
        new FileInputStream(new File("io/src/main/read.txt")),
        StandardCharsets.UTF_8);

    //将读取的存入字符数组中去
    char[] buffChar = new char[10];
    int length;
    //length = reader.read(buffChar) 这个是为了确定实际读了多少字符
    while ((length = reader.read(buffChar)) != -1) {
      System.out.print(Arrays.copyOf(buffChar, length));
    }
    reader.close();
  }
}
