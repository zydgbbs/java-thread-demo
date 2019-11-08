package com.zydgbbs.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 深入浅出ReentrantLock：https://www.jianshu.com/p/4358b1466ec9
 * synchronized和RetreenLock锁区别
 * 1、synchronized是java关键字，RetreenLock是个java类
 * 2、synchronized无法获取锁状态，Lock可以判断是否持有锁
 * 3、synchronized会自动释放锁，Lock需要在finally中unlock()手动释放锁
 * 4、使用synchronized，线程1获取锁，线程2只能等待；使用Lock，线程1获取锁，线程2会尝试获取锁，如果获取不到，不会一直等待，可以直接结束。
 * 5、synchronized和Lock都是可重入，可中断的
 * 6、synchronized适合代码量少的同步，Lock适合大量代码同步
 */
public class ReentrantLockTest implements Runnable{

    private int count;
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        for(int j=0;j<1000;j++) {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        ReentrantLockTest t = new ReentrantLockTest();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(t.count);
    }
}
