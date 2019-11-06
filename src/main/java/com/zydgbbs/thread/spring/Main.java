package com.zydgbbs.thread.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring对多线程的支持
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);

        DemoService d = ac.getBean(DemoService.class);

        d.a();
        d.b();

    }
}
