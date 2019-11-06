package com.zydgbbs.thread.interrupt;

/**
 * interrupt()
 * 其作用是中断此线程（此线程不一定是当前线程，而是指调用该方法的Thread实例所代表的线程），
 * 但实际上只是给线程设置一个中断标志，线程仍会继续运行。
 */
public class InterruptTest {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        System.out.println("第一次调用thread.isInterrupted()："+thread.isInterrupted());
        thread.interrupt();
        System.out.println("第二次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第三次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("thread是否存活："+thread.isAlive());
        /**
         * 从结果可以看出调用interrupt（）方法后，线程仍在继续运行，并未停止，
         * 但已经给线程设置了中断标志，第一个isInterrupted（）方法输出false,第二个isInterrupted（）方法输出true，说明interrupt()是设置中断标志；
         * 第二个和第三个isInterrupted（）方法都输出true，也说明isInterrupted（）方法并不会清除中断状态。
         */
    }
}
