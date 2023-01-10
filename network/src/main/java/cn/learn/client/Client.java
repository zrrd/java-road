package cn.learn.client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @author shaoyijiong
 * @date 2023/1/9
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8888);
            // 客户在调用函数 connect 前不必非得调用 bind 函数，因为如果需要的话，内核会确定源 IP 地址，并按照一定的算法选择一个临时端口作为源端口。
            // socket.bind();
            socket.connect(inetSocketAddress);

            // 服务端发送数据
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            outputStreamWriter.write("hello server");
            outputStreamWriter.flush();

            // 获取服务端输入数据
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            // 一次性接收完成
            BufferedReader buf = new BufferedReader(inputStreamReader);
            String str = buf.readLine();
            buf.close();
            System.out.println(str);

            outputStreamWriter.close();
            inputStreamReader.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}