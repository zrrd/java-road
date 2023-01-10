package cn.learn.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author shaoyijiong
 * @date 2023/1/9
 */
public class Server {

    public static void main(String[] args) throws IOException {
        //  创建服务端套接字
        ServerSocket serverSocket = new ServerSocket();
        // 对ip与端口进行绑定
        // 要注意 , 这个的ip是指本机的ip ; 当一台机器有多张网卡时可以指定绑定到某张网卡的某个ip上去 , 也可以使用通配符绑定全部ip
        // InetAddress.anyLocalAddress()
        // ipv4 Inet4AddressImpl.anyLocalAddress()  ipv6 Inet6AddressImpl.anyLocalAddress()
        // 由于上面两个类是非public的所以只要设置为空 , 默认绑定多个ip
        InetSocketAddress inetSocketAddress = new InetSocketAddress((InetAddress) null, 8888);
        // java api中将bind与listen合并为了一步
        // 查看具体实现类就可以发现 , bind只是将套接字与ip 端口进行绑定 , listen才会告诉系统内核能够接收用户请求
        serverSocket.bind(inetSocketAddress);
        // serverSocket accept后 如果有客户端连接返回一个新的 socket
        // serverSocket 可以称为监听套接字
        // clientContentSocket 可以称为已连接套接字
        // 为啥需要重新返回一个新的 socket? 是因为一个服务端需要服务多个客户端
        Socket clientContentSocket = serverSocket.accept();


        // 获取客户端输入数据
        InputStreamReader inputStreamReader = new InputStreamReader(clientContentSocket.getInputStream());
        char[] array = new char[100];
        int read = inputStreamReader.read(array);
        System.out.println(Arrays.copyOf(array, read));

        // 向客户端发送数据
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientContentSocket.getOutputStream());
        outputStreamWriter.write("hello client");
        outputStreamWriter.flush();

        inputStreamReader.close();
        outputStreamWriter.close();
        clientContentSocket.close();
        serverSocket.close();
    }
}