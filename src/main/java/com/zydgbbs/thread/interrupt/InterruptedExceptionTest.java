package com.zydgbbs.thread.interrupt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 中断异常
 */
public class InterruptedExceptionTest implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("The FileClock has been interrupted");
                //break;
            }
        }
    }

    public static void main(String[] args) {
        InterruptedExceptionTest clock = new InterruptedExceptionTest();
        Thread thread=new Thread(clock);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

}
