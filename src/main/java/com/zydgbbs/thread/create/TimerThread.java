package com.zydgbbs.thread.create;

import java.util.Timer;
import java.util.TimerTask;

public class TimerThread {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("TimerTask is start");
            }
        },0,1000);
    }
}
