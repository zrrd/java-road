package cn.learn.scoket.socketgo;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * socket 客户端
 *
 * @author shaoyijiong
 * @date 2018/7/13
 */
@Component
public class Client implements Runnable {
    @Value("${myserver.host}")
    private String host;

    @Value("${myserver.port}")
    private int port;

    @Override
    public void run() {
        try {
            File file = FileUtils.getFile("client.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            Socket socket = new Socket(host, port);
            byte[] bytes = new byte[1024];
            InputStream inputStream = FileUtils.openInputStream(file);
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                socket.getOutputStream().write(bytes, 0, len);
            }
            socket.getOutputStream().close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
