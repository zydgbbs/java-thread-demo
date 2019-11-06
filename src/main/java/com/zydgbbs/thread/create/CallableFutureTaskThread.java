package com.zydgbbs.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableFutureTaskThread implements Callable<Integer> {

    private int sum;

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable子线程开始计算啦！");
        Thread.sleep(2000);
        for (int i = 0; i < 5000; i++) {
            sum = sum + i;
        }
        System.out.println("Callable子线程计算结束！");
        return sum;
    }

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        // 创建Callable对象任务
        CallableFutureTaskThread calTask = new CallableFutureTaskThread();
        // 创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask(calTask);
        // 执行任务
        es.submit(futureTask);
        // 关闭线程池
        es.shutdown();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if (futureTask.get() != null) {
                // 输出获取到的结果
                System.out.println("futureTask.get()-->" + futureTask.get());
            } else {
                // 输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }
}
