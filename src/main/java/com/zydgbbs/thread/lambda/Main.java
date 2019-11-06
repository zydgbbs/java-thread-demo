package com.zydgbbs.thread.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int sum(List<Integer> list){
        return list.parallelStream().mapToInt(a->a).sum();
    }

    public static void print(List<Integer> list){
        list.parallelStream().forEach(System.out::println);
    }

    public static void printOrder(List<Integer> list){
        list.parallelStream().forEachOrdered(System.out::println);
    }

    public static void main(String[] args) {
        /*List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(3);*/
        List<Integer> list = Arrays.asList(20,30);
        System.out.println(sum(list));
        print(list);
        printOrder(list);
    }

}
