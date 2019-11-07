package com.zydgbbs.thread.synchronize;

/**
 * Synchronized是Java中解决并发问题的一种最常用的方法，也是最简单的一种方法。
 * Synchronized的作用主要有三个：
 * （1）确保线程互斥的访问同步代码
 * （2）保证共享变量的修改能够及时可见
 * （3）有效解决重排序问题。
 * 静态同步方法，锁是当前类的class对象
 */
public class SynchronizedStaticMethodTest {
    public static synchronized void method1(){
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public static synchronized void method2(){
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }
    public static void main(String[] args) {
        //当前实例对象test
        final SynchronizedStaticMethodTest test = new SynchronizedStaticMethodTest();
        final SynchronizedStaticMethodTest test2 = new SynchronizedStaticMethodTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test2.method2();
            }
        }).start();
    }
}
