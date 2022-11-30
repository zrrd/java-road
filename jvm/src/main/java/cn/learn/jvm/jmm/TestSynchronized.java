package cn.learn.jvm.jmm;

/**
 * @author shaoyijiong
 * @date 2022/11/22
 */
public class TestSynchronized {

    synchronized void m() {

    }

    void n() {
        synchronized (this) {

        }
    }
}