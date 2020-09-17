package cn.learn.java8.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 使用jdk8的方法读取文件
 *
 * @author 邵益炯
 * @date 2018/10/29
 */
public class ReadFile {

  public static void main(String[] args) throws IOException {

    String path = "D:\\code\\javaroad\\java8\\src\\main\\java\\cn\\learn\\java8\\file\\a.txt";

    // Files.readAllBytes默认以UTF-8编码读入文件，故文件的编码如果不是UTF-8，那么中文内容会出现乱字符
    Files.readAllBytes(Paths.get(path));

    List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
    StringBuilder sb = new StringBuilder();
    for (String s : lines) {
      sb.append(s);
      sb.append("\n");
    }

    System.out.println(sb);

    /*
    文件拷贝 文件 -> 文件 ， 文件 -> 输出流
    Files.copy();

    创建文件夹，文件
    Files.createDirectory();
    Files.createFile();
    Files.createSymbolicLink();
    Files.createLink();
    Files.createTempDirectory();
    Files.createTempFile();

    文件是否存在
    Files.exists();
    文件移动目录
    Files.move();
    文件删除
    Files.delete();
    遍历目录
    Files.walkFileTree();

    文件查找
    Files.find();

    文件读取与写入
    Files.write();
    Files.newBufferedReader();
    Files.newInputStream();
    Files.newBufferedWriter();
    Files.newOutputStream();

    还有很多其他接口
    * */

  }
}
