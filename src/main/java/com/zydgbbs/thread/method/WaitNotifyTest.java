package com.zydgbbs.thread.method;

import java.util.concurrent.TimeUnit;

/**
 * 在Object.java中，定义了wait(), notify()和notifyAll()等接口。
 * wait()的作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。
 * 而notify()和notifyAll()的作用，则是唤醒当前对象上的等待线程；
 * notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
 *
 * Object类中关于等待/唤醒的API详细信息如下：
 * notify()-- 唤醒在此对象监视器上等待的单个线程。
 * notifyAll()-- 唤醒在此对象监视器上等待的所有线程。
 * wait()-- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，
 * 当前线程被唤醒(进入“就绪状态”)。
 * wait(long timeout)-- 让当前线程处于“等待(阻塞)状态”，“直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，
 * 或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
 * wait(long timeout, int nanos)  -- 让当前线程处于“等待(阻塞)状态”，
 * “直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法，或者其他某个线程中断当前线程，或者已超过某个实际时间量”，
 * 当前线程被唤醒(进入“就绪状态”)。
 */
public class WaitNotifyTest {

    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        synchronized (t1){
            try{
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();
                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");
                //t1.wait();
                t1.wait(2000);
                System.out.println(Thread.currentThread().getName()+" continue");

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run(){
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()+" call notify()");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 唤醒当前的wait线程
                notify();
            }
        }
    }

}

