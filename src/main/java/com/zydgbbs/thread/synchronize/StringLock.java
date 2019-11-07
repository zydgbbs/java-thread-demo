package com.zydgbbs.thread.synchronize;

public class StringLock {
    public void method() {
        //new String("字符串常量")
        //每个线程创建前使用new String(lock)会产生不同的锁，造成线程同步失败。所以在使用的时候要特别注意这点，new String(lock)是会产生不同的对象，他们所指向的对象锁是不同的。
        synchronized ("字符串常量") {
            try {
                while(true){
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + "开始");
                    Thread.sleep(1000);
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + "结束");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final StringLock stringLock = new StringLock();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stringLock.method();
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
