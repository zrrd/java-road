package cn.learn.scoket;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author shaoyijiong
 * @date 2018/7/13
 */
public class FileTest {
    @Test
    public void fileTest() throws IOException {
        File file = FileUtils.getFile("server.txt");
        file.createNewFile();
    }
}
