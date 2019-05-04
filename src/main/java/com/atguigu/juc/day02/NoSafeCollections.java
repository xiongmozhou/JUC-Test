package com.atguigu.juc.day02;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoSafeCollections {
    public static void main(String[] args) throws Exception{
        List<String> safeList = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());
//        Set<String> hs = new CopyOnWriteArraySet();

        //会出现ConcurrentModificationException异常：并发修改异常
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                safeList.add(UUID.randomUUID().toString());
                    System.out.println(safeList);
            }).start();
        }
    }


}
