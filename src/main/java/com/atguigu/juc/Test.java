package com.atguigu.juc;

import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                synchronized ("123"){
                    list.add(123+"");
                    System.out.println(list);
                }
            }).start();
        }
    }
}

