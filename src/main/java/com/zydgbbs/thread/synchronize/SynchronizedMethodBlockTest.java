package com.zydgbbs.thread.synchronize;

/**
 * Synchronized是Java中解决并发问题的一种最常用的方法，也是最简单的一种方法。
 * Synchronized的作用主要有三个：
 * （1）确保线程互斥的访问同步代码
 * （2）保证共享变量的修改能够及时可见
 * （3）有效解决重排序问题。
 * 同步方法块，锁是括号里面的对象，效果跟同步普通方法一样
 */
public class SynchronizedMethodBlockTest {

    public void method1(){
        System.out.println("Method 1 start");
        try {
            synchronized (this) {//this可以换成(SynchronizedMethodBlockTest.class)类锁，具有类锁的功能
                System.out.println("Method 1 execute");
                Thread.sleep(3000);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public void method2(){
        System.out.println("Method 2 start");
        try {
            synchronized (this) {//this可以换成(SynchronizedMethodBlockTest.class)类锁，具有类锁的功能
                System.out.println("Method 2 execute");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static void main(String[] args) {

        final SynchronizedMethodBlockTest test = new SynchronizedMethodBlockTest();
        //test.
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();

    }
}
