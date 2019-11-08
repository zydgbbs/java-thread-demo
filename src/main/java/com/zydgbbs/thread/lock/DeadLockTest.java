package com.zydgbbs.thread.lock;

public class DeadLockTest implements Runnable{

    private String tag;
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public DeadLockTest(String tag){
        this.tag = tag;
    }

    @Override
    public void run() {
        if(tag.equals("a")){
            synchronized (lock1) {
                try {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
                }
            }
        }
        if(tag.equals("b")){
            synchronized (lock2) {
                try {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock2执行");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("当前线程 : "  + Thread.currentThread().getName() + " 进入lock1执行");
                }
            }
        }
    }

    public static void main(String[] args) {

        DeadLockTest d1 = new DeadLockTest("a");
        DeadLockTest d2 = new DeadLockTest("b");

        Thread t1 = new Thread(d1, "t1");
        Thread t2 = new Thread(d2, "t2");

        t1.start();
        t2.start();

    }
}
