package com.liping.test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程
 *
 * @author LiPing
 * @create 2019-04-11-10:00
 */
public class MutliThread {
    //public static int count = 0;
    
    //原子操作类，指的是java.util.concurrent.atomic包下，一系列以Atomic开头的包装类。
    // 例如AtomicBoolean，AtomicInteger，AtomicLong。它们分别用于Boolean，Integer，Long类型的原子性操作。
    public static AtomicInteger count = new AtomicInteger(0);
    public static void main(String[] args){
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(20);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 100; j++) {
                       // //加同步锁，得到200，否则103，因为线程不安全
                       //synchronized (MutliThread.class) {
                       //    count ++;
                       //}
                       
                        //原子操作类
                        count.incrementAndGet();
                    }
                }
            }).start();
        }
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count: "+count);
    }
}
