package com.zydgbbs.thread.create;

public class ExtendsThread extends Thread{
    @Override
    public void run() {
        System.out.println("开始执行Extends创建的线程");
    }

    public static void main(String[] args) {
        Thread thread = new ExtendsThread();
        thread.start();
    }
}
