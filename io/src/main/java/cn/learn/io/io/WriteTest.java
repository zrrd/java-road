package cn.learn.io.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 字符流输出
 *
 * @author 邵益炯
 * @date 2018/8/21
 */
public class WriteTest {

  public static void main(String[] args) throws Exception {
    //BufferedWriter
    Writer writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(new File("io/src/main/write.txt"))));
    //通过fileWriter写入文件 true or false 是否写入或者全部替换
    Writer writer1 = new FileWriter(new File("io/src/main/write.txt"), false);

    String txt = "今天天气真的不错";
    writer.write(txt);
    //清缓存
    writer.flush();
    writer.close();
  }
}
