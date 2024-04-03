package cn.learn.utils.proto;

import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author shaoyijiong
 * @date 2024/4/3
 */
public class Test {
    public static void main(String[] args) throws IOException, URISyntaxException {
        LoginRequests.LoginRequest loginRequest = LoginRequests.LoginRequest.newBuilder().setUsername("test").setPassword("123").build();
        System.out.println(loginRequest);

        // 序列化成字节数组
        byte[] byteArray = loginRequest.toByteArray();
        System.out.println(byteArray);
        // 反序列化
        LoginRequests.LoginRequest loginRequest1 = LoginRequests.LoginRequest.parseFrom(byteArray);
        System.out.println(loginRequest1);

        // 序列化到文件
        // /Users/meiliodas/IdeaProjects/self/java-road/utils/target/classes/LoginRequest.txt
        URL resource = Test.class.getClassLoader().getResource("LoginRequest.txt");
        Path path = Paths.get(resource.toURI());

        loginRequest.writeTo(Files.newOutputStream(path));
        // 从文件反序列化
        byte[] bytes = FileUtils.readFileToByteArray(path.toFile());
        // 比较两个字节数组
        System.out.println("equals:" + Arrays.equals(byteArray, bytes));
        LoginRequests.LoginRequest loginRequest2 = LoginRequests.LoginRequest.parseFrom(FileUtils.readFileToByteArray(path.toFile()));
        System.out.println("loginRequest2:");
        System.out.println(loginRequest2);
        System.out.println("success");

    }
}