package com.zydgbbs.thread.common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTest {
    public static void main(String[] args) throws Exception{
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(Thread.currentThread().getName()+"正在执行结果");
                TimeUnit.SECONDS.sleep(1);
                return 1;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println("结果执行完毕，"+futureTask.get());

    }
}
