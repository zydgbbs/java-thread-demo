package com.zydgbbs.thread.lock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class VolatileTest {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>2) {  //保证前面的线程都执行完,main和Monitor Ctrl-Break
            Thread.yield();
        }

        /*if(Thread.activeCount()==2){
            ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
            ThreadInfo[] threadInfos = tmxb.dumpAllThreads(false, false);
            for (ThreadInfo info : threadInfos) {
                System.out.println("[" + info.getThreadId() + "] " + info.getThreadName());
            }
        }
        Thread.currentThread().getThreadGroup().list();*/

        System.out.println(test.inc);
    }
}
