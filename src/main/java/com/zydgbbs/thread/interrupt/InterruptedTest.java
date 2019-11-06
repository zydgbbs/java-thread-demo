package com.zydgbbs.thread.interrupt;

/**
 * interrupted（）方法有检测中断并清除中断状态的作用
 * 测试当前线程是否被中断（检查中断标志），返回一个boolean并清除中断状态，第二次再调用时中断状态已经被清除，将返回一个false。
 */
public class InterruptedTest {
    public static void main(String[] args) {
        /*MyThread thread = new MyThread();
        thread.start();
        System.out.println("第一次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第一次调用thread.interrupted()："
                +Thread.currentThread().interrupted());
        thread.interrupt();
        System.out.println("第二次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第二次调用thread.interrupted()："
                +Thread.currentThread().interrupted());
        System.out.println("第三次调用thread.interrupted()："
                +Thread.currentThread().interrupted());*/
        MyThread thread = new MyThread();
        thread.start();
        thread.interrupt();
        System.out.println("第一次调用Thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第二次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第一次调用thread.interrupted()："
                +thread.interrupted());//输出的是false，而不是true，原因如下
        /**
         * interrupted（）方法测试的是当前线程是否被中断，当前线程！！！当前线程！！！
         * 这里当前线程是main线程，而thread.interrupt(）中断的是thread线程，这里的此线程就是thread线程。
         * 所以当前线程main从未被中断过，尽管interrupted（）方法是以thread.interrupted（）的形式被调用，
         * 但它检测的仍然是main线程而不是检测thread线程，所以thread.interrupted（）在这里相当于main.interrupted（）
         */
        System.out.println("第二次调用thread.interrupted()："
                +thread.interrupted());
        System.out.println("thread是否存活："+thread.isAlive());
    }
}
