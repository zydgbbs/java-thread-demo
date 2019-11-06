package com.zydgbbs.thread.create;

public class RunnableThread implements Runnable{
    @Override
    public void run() {
        System.out.println("开始执行Runnable创建的线程");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableThread());
        thread.start();
    }
}
