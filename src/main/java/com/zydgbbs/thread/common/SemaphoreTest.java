package com.zydgbbs.thread.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        //线程池
        ExecutorService exec= Executors.newCachedThreadPool();
        //只能几个线程同时访问
        final Semaphore semph = new Semaphore(3);
        //模拟几个客户端访问
        for(int index=1;index<10;index++) {
            final int NO=index;
            Runnable ru = new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        //获取许可
                        semph.acquire();
                        System.out.println("accessing:"+NO);
                        Thread.sleep((long)(Math.random()*1000));
                        //访问完后，释放
                        System.out.println("release:"+NO);
                        semph.release();
                    }catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            };
            exec.execute(ru);
        }
        //退出线程池
        exec.shutdown();
    }
}
