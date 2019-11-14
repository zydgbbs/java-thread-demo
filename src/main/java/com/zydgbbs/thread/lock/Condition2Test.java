package com.zydgbbs.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition2Test {

    private int signal;
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void a() throws Exception{
        lock.lock();
        while (signal!=0){
            conditionA.await();
        }
        System.out.println("执行了a方法");
        signal = (signal+1)%3;
        TimeUnit.SECONDS.sleep(1);
        conditionB.signal();
        lock.unlock();
    }
    public void b() throws Exception{
        lock.lock();
        while (signal!=1){
            conditionB.await();
        }
        System.out.println("执行了b方法");
        signal = (signal+1)%3;
        TimeUnit.SECONDS.sleep(1);
        conditionC.signal();
        lock.unlock();
    }
    public void c() throws Exception{
        lock.lock();
        while (signal!=2){
            conditionC.await();
        }
        System.out.println("执行了c方法");
        TimeUnit.SECONDS.sleep(1);
        signal = (signal+1)%3;
        conditionA.signal();
        lock.unlock();
    }

    public static void main(String[] args) {
        Condition2Test test = new Condition2Test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true)
                        test.a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true)
                        test.b();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true)
                        test.c();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
