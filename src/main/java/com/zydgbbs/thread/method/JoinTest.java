package com.zydgbbs.thread.method;

/**
 * join()方法的作用，是等待这个线程结束；
 * 也就是说，t.join()方法阻塞调用此方法的线程(calling thread)进入 TIMED_WAITING 状态，直到线程t完成，此线程再继续；
 * 通常用于在main()主线程内，等待其它线程完成再结束main()主线程。
 */
public class JoinTest {
    public static void main(String[] args) throws Exception{
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("a线程休眠两秒开始");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("a线程休眠两秒结束");
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b线程休眠三秒开始");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("b线程休眠三秒结束");
            }
        });

        b.start();
        //b没有join时,b肯定是后执行完毕,因为b休眠了2秒.
        //如果加上了join,则main的线程会等待b执行完毕以后,在继续往下执行a.
        //b.join();
        a.start();

    }
}
