package com.zydgbbs.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock是互斥排他锁，同一时间只能有一个线程在执行任务，ReentrantLock支持锁的重入功能，
 * 虽然保证了线程的安全性，但是效率不高，实际上应该是写操作互斥，读操作共享。
 * 而jdk提供了读写锁ReentrantReadWriteLock。
 * 读操作的锁叫共享锁，写操作的锁叫排他锁。就是遇见写锁就需互斥。那么以此可得出读读共享，写写互斥，读写互斥，写读互斥。
 */
public class ReentrantReadWriteLockTest {
    final ReadWriteTask readWriteTask = new ReadWriteTask();

    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        //test.readReadTest();
        //test.readWriteTest();
        //test.writeReadTest();
        test.writeWriteTest();
    }

    /**
     * 读读共享
     */
    public void readReadTest(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.read();
            }
        });
        t1.setName("Read1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.read();
            }
        });
        t2.setName("Read2");
        t1.start();
        t2.start();
    }

    /**
     * 读写互斥
     */
    public void readWriteTest(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.read();
            }
        });
        t1.setName("Read1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.write();
            }
        });
        t2.setName("Write1");
        t1.start();
        t2.start();
    }

    /**
     * 写读互斥
     */
    public void writeReadTest(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.write();
            }
        });
        t1.setName("Write1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.read();
            }
        });
        t2.setName("Read1");
        t1.start();
        t2.start();
    }

    /**
     * 写写互斥
     */
    public void writeWriteTest(){
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.write();
            }
        });
        t1.setName("Write1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteTask.write();
            }
        });
        t2.setName("Write2");
        t1.start();
        t2.start();
    }
}
