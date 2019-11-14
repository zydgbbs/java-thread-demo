package com.zydgbbs.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        Producer producer = test.new Producer();
        producer.setName("product");
        Consumer consumer = test.new Consumer();
        consumer.setName("consumer");
        consumer.start();
        producer.start();
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }
        private void consume() {
            try {
                lock.lock();
                System.out.println("我在等一个新信号" + this.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                condition.await();

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                System.out.println("拿到一个信号" + this.currentThread().getName());
                lock.unlock();
            }

        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }
        private void produce() {
            try {
                lock.lock();
                System.out.println("我拿到锁" + this.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signalAll();
                System.out.println("我发出了一个信号：" + this.currentThread().getName());
            } finally {
                lock.unlock();
            }
        }
    }
}
