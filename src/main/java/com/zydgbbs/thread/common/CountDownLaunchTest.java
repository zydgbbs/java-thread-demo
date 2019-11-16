package com.zydgbbs.thread.common;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLaunchTest {

    final static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) {
        for (int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"开始执行");
                    try {
                        Thread.sleep((new Random().nextInt(1000))+1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                    System.out.println(Thread.currentThread().getName()+"开始完毕");
                }
            }).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main开始等待");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main执行完毕");

    }
}
