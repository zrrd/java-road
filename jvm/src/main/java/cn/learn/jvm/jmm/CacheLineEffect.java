package cn.learn.jvm.jmm;

/**
 * @author shaoyijiong
 * @date 2022/8/31
 */
public class CacheLineEffect {

    private static class T {
        public volatile long x = 0L;
    }

    /**
     * long 8个字节位于同一个缓存行
     */
    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    /**
     * 位于同一个缓存行测试时间
     */
    private static void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000L; i++) {
                arr[0].x = i;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000L; i++) {
                arr[1].x = i;
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("test1:" + (System.currentTimeMillis() - start));
    }

    private static class C {
        public volatile long x = 0L;
        // 填充7位让一个C就是占满一个缓存行
        // 缓存行对齐
        public volatile long x1, x2, x3, x4, x5, x6, x7;
    }

    public static C[] arr1 = new C[2];

    static {
        arr1[0] = new C();
        arr1[1] = new C();
    }


    private static void test2() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000L; i++) {
                arr1[0].x = i;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000_000L; i++) {
                arr1[1].x = i;
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("test2:" + (System.currentTimeMillis() - start));
    }


    /**
     * 缓存行共享会稍微慢点
     */
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }

}

