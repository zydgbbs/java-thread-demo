package com.zydgbbs.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 它是一种固定大小的线程池；
 * corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
 * keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉；
 * 但这里keepAliveTime无效；
 * 阻塞队列采用了LinkedBlockingQueue，它是一个无界队列；
 * 由于阻塞队列是一个无界队列，因此永远不可能拒绝任务；
 * 由于采用了无界队列，实际线程数量将永远维持在nThreads，因此maximumPoolSize和keepAliveTime将无效
 */
public class FixedThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println(Thread.currentThread().getName()+"正在执行"+index);
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
