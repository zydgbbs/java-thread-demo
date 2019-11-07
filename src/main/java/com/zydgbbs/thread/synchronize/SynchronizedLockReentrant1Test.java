package com.zydgbbs.thread.synchronize;

public class SynchronizedLockReentrant1Test {

    public synchronized void method1(){
        System.out.println("method1..");
        method2();
    }

    public synchronized void method2(){
        System.out.println("method2..");
        method3();
    }

    public synchronized void method3(){
        System.out.println("method3..");
    }

    public static void main(String[] args) {
        final SynchronizedLockReentrant1Test sd = new SynchronizedLockReentrant1Test();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sd.method1();
            }
        });
        t1.start();
    }

}
