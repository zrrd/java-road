package cn.learn.scoket;

import cn.learn.scoket.socketgo.Client;
import cn.learn.scoket.socketgo.Server;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * 测试下socket
 *
 * @author shaoyijiong
 * @date 2018/7/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SocketTest {

    @Autowired
    Client client;

    @Autowired
    Server server;

    @Test
    public void test() throws IOException {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("pool-%d").build();
        ExecutorService pool =new ThreadPoolExecutor(5,200,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(1024),
                nameThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        pool.execute(server);
        pool.execute(client);
        pool.execute(client);
        pool.execute(client);
    }
}
