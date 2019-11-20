package com.zydgbbs.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {
    private static BlockingQueue<Food> queue = new ArrayBlockingQueue<Food>(2);
    public static void main(String[] args) {
        for (int i = 0 ; i < 5 ; i++) {
            new Thread(new ArrayBlockingQueueTest().new Producer()).start();
        }
        new Thread(new ArrayBlockingQueueTest().new Consumer()).start();
        new Thread(new ArrayBlockingQueueTest().new Consumer()).start();
        new Thread(new ArrayBlockingQueueTest().new Consumer()).start();
    }
    class Food{
        private String name;
        public void setName(String name) {
            this.name = name;
        }
    }
    class Producer implements Runnable {
        @Override
        public void run() {
            Food food = new Food();
            food.setName("banana");
            try {
                queue.put(food);
                System.out.println(Thread.currentThread().getName() + "provider : " + food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                Food food = queue.take();
                System.out.println(Thread.currentThread().getName() + "consumer : " + food);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
