package cn.learn.jvm.classloader;

import com.alibaba.fastjson.util.IOUtils;
import com.google.common.base.Splitter;

import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Spliterator;

/**
 * @author shaoyijiong
 * @date 2022/8/23
 */
public class T {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/meiliodas/Downloads/b3825a2f-6d58-485a-bc66-670e68a8d561.csv");
        InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(file.toPath()));
        String s = IOUtils.readAll(inputStreamReader);
        List<String> strings = Splitter.on(",").splitToList(s);
        Set<String> set = new HashSet<>();
        for (String string : strings) {
            String substring = string.substring(0, 2);
            set.add(substring);

        }
        for (String s1 : set) {
            System.out.print(s1);
            System.out.print(",");
        }
    }
}