package com.zydgbbs.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 它接收SchduledFutureTask类型的任务，有两种提交任务的方式：
 * 1 scheduledAtFixedRate
 * 2 scheduledWithFixedDelay
 * SchduledFutureTask接收的参数：
 * time：任务开始的时间
 * sequenceNumber：任务的序号
 * period：任务执行的时间间隔
 * 它采用DelayQueue存储等待的任务
 * DelayQueue内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
 * DelayQueue也是一个无界队列；
 * 工作线程的执行过程：
 * 工作线程会从DelayQueue取已经到期的任务去执行；
 * 执行结束后重新设置任务的到期时间，再次放回DelayQueue
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        /**/
        scheduledThreadPool.schedule(new Runnable(){
            public void run(){
                System.out.println("延迟1秒执行");
            }
        }, 1, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行");
            }
        },1,1,TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("执行3");
            }
        },1,3,TimeUnit.SECONDS);
    }
}
