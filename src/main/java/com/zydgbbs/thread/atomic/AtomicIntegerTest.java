package com.zydgbbs.thread.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicIntegerTest {

    private AtomicInteger value = new AtomicInteger(0);
    AtomicReference<User> user = new AtomicReference<>();
    AtomicIntegerFieldUpdater<User> age = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public int getNext(){
        User user = new User();
        age.incrementAndGet(user);
        System.out.println(user.getAge());
        return value.getAndIncrement();
    }

    public static void main(String[] args) {
        AtomicIntegerTest test = new AtomicIntegerTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.getNext();
            }
        }).start();

        /*AtomicIntegerTest test = new AtomicIntegerTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName()+" "+test.getNext());
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName()+" "+test.getNext());
                    try{
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }).start();*/

    }
}
