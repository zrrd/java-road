package cn.learn.utils.guava.io;

import com.google.common.graph.Traverser;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * guava io https://github.com/google/guava/wiki/IOExplained
 *
 * @author shaoyijiong
 * @date 2019/7/10
 */
@SuppressWarnings("UnstableApiUsage")
public class IoTest {


  /**
   * 工具类不提供关闭  需要手动关闭
   */
  private static void byteStreamsTest() throws Exception {
    FileInputStream fileInputStream = new FileInputStream("D:\\a.txt");
    FileOutputStream fileOutputStream = new FileOutputStream("D:\\b.txt");
    //将流转为字节
    byte[] bytes = ByteStreams.toByteArray(fileInputStream);

    //将输入流 的内容复制到输出流
    ByteStreams.copy(fileInputStream, fileOutputStream);

    //读取
    byte[] readBytes = new byte[10000];
    ByteStreams.readFully(fileInputStream, readBytes);

    //跳过
    ByteStreams.skipFully(fileInputStream, 100);

    //变成一个输出流
    OutputStream outputStream = ByteStreams.nullOutputStream();
    fileInputStream.close();

  }


  /**
   * 字符流工具
   */
  private static void charStreamsTest() throws Exception {
    InputStreamReader reader = new InputStreamReader(new FileInputStream("D:\\a.txt"));
    OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("D:\\b.txt"));

    //输入流转String
    CharStreams.toString(reader);
    CharStreams.readLines(reader);
    CharStreams.skipFully(reader, 1);
    CharStreams.nullWriter();
  }

  /**
   * 字节源
   */
  private static void byteSourceTest() throws Exception {

    //获得源

    //用来创建与文件有关的流
    ByteSource byteSource = Files.asByteSource(new File("D:\\a.txt"));
    ByteSink byteSink = Files.asByteSink(new File("D:\\a.txt"), FileWriteMode.APPEND);
    ByteSource byteSource1 = Resources.asByteSource(new URL(""));
    byte[] a = new byte[1];
    ByteSource.wrap(a);

    //将两个流合并为一个流
    ByteSource.concat(byteSource, byteSource1);

    //源的截取
    byteSource.slice(1, 10);

    //操作源

    byte[] read = byteSource.read();
    byteSource.copyTo(byteSink);
    long size = byteSource.size();
    boolean empty = byteSource.isEmpty();

    byteSink.write("a".getBytes());
    byteSink.writeFrom(new FileInputStream("D:\\a.txt"));

  }

  /**
   * 字符源
   */
  private static void charSourceTest() {

    CharSource charSource = Files.asCharSource(new File(""), StandardCharsets.UTF_8);
    CharSink charSink = Files.asCharSink(new File(""), StandardCharsets.UTF_8, FileWriteMode.APPEND);
    //.... 其他操作与字节源大同小异 https://wizardforcel.gitbooks.io/guava-tutorial/content/25.html
  }


  private static void filesTest() throws IOException {
    //必要时为文件创建父目录
    Files.createParentDirs(new File(""));

    //返回给定路径所表示文件的扩展名
    Files.getFileExtension("");

    //返回去除了扩展名的文件名
    Files.getNameWithoutExtension("");
    final Traverser<File> fileTraverser = Files.fileTraverser();
  }

  public static void main(String[] args) throws Exception {
    filesTest();
  }
}
