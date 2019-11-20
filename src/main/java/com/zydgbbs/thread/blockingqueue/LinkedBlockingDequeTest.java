package com.zydgbbs.thread.blockingqueue;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTest {
    // TODO: queue是LinkedList对象时，程序会出错。
    //private static Queue<String> queue = new LinkedList<String>();
    private static LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<String>();
    public static void main(String[] args) {
        Thread t1 = new MyThread("ta");
        Thread t2 = new MyThread("tb");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            // 获取并移除此双端队列表示的队列的头部（即此双端队列的第一个元素），必要时将一直等待可用元素。
            String value = queue.take();
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printAll();
    }

    private static void printAll() {
        String value;
        Iterator iter = queue.iterator();
        while (iter.hasNext()) {
            value = (String) iter.next();
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                String val = Thread.currentThread().getName() + i;
                queue.add(val);
                // 通过“Iterator”遍历queue。
                printAll();
            }
        }
    }

}
