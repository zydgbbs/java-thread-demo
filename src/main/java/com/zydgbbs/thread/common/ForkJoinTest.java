package com.zydgbbs.thread.common;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 实现1+2+...+100的计算任务
 */
public class ForkJoinTest extends RecursiveTask<Integer> {

    private int begin;
    private int end;

    public ForkJoinTest(int begin,int end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //拆分任务
        if(end-begin<=2){
            //计算
            for(int i=begin;i<=end;i++){
                sum += i;
            }
        }else{
            //拆分
            ForkJoinTest forkJoinTest1 = new ForkJoinTest(begin,(begin+end)/2);
            ForkJoinTest forkJoinTest2 = new ForkJoinTest((begin+end)/2+1,end);
            forkJoinTest1.fork();
            forkJoinTest2.fork();
            Integer value1 = forkJoinTest1.join();
            Integer value2 = forkJoinTest2.join();
            sum = value1+value2;
        }
        return sum;
    }

    public static void main(String[] args) throws Exception{

        ForkJoinTest forkJoinTest = new ForkJoinTest(1,10000);
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("开始计算...");
        Future<Integer> future = pool.submit(forkJoinTest);
        System.out.println("计算的值为："+future.get());

    }
}
