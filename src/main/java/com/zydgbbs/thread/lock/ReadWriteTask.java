package com.zydgbbs.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteTask{

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read(){
        try{
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+" 读开始");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" 读结束");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }

    public void write(){
        try{
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+" 写开始");
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+" 写结束");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }

}
