package com.zydgbbs.thread.method;

import java.util.concurrent.TimeUnit;

/**
 * 只能在start() 方法之前可以调用 setDaemon() 方法。一旦线程运行了，就不能修改守护状态。
 * 可以使用 isDaemon() 方法来检查线程是否是守护线程（方法返回 true) 或者是使用者线程 (方法返回 false)。
 * Java有一种特别的线程叫做守护线程。这种线程的优先级非常低，通常在程序里没有其他线程运行时才会执行它。
 * 当守护线程是程序里唯一在运行的线程时，JVM会结束守护线程并终止程序。
 */
public class DaemonTest {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Daemon Thread run");
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.isDaemon());

        TimeUnit.SECONDS.sleep(3);

    }
}
