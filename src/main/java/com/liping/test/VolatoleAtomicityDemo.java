package com.liping.test;

/**
 * @author LiPing
 * @create 2022-11-24-21:03
 */
public class VolatoleAtomicityDemo {
    public  static int inc;
//    public volatile static long incLong;

//    public void increase() {
//        inc++;
//    }
//    public void increaseLong() {
//        incLong++;
//    }
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        VolatoleAtomicityDemo volatoleAtomicityDemo = new VolatoleAtomicityDemo();
//
//        synchronized (inc){
//            for (int i = 0; i < 5; i++) {
//                threadPool.execute(() -> {
//                    for (int j = 0; j < 500; j++) {
//                        volatoleAtomicityDemo.increase();
//                        volatoleAtomicityDemo.increaseLong();
//                    }
//                });
//            }
//        }
//
//        // 等待1.5秒，保证上面程序执行完成
//        Thread.sleep(1500);
//        System.out.println(inc);
//        System.out.println(incLong);
//        threadPool.shutdown();
//        System.out.println(inc);

//        System.out.println(Math.round(11.5));
//        System.out.println(Math.round(-11.5));

    System.out.println(100%3);
System.out.println(100%3.0);

    }

}
