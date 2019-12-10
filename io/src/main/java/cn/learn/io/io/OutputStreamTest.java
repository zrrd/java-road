package cn.learn.io.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 邵益炯
 * @date 2018/8/21
 */
@Slf4j
public class OutputStreamTest {

  public static void main(String[] args) {
    //FileOutputStream 能 构造 true 或者 false 是否写入或者全部替换 append
    try (OutputStream outputStream = new BufferedOutputStream(
        new FileOutputStream(new File("io/src/main/write.txt"), true))) {
      String txt = "今天的风儿好喧嚣\n\r";
      byte[] b = txt.getBytes(StandardCharsets.UTF_8);
      outputStream.write(b);
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }
}
