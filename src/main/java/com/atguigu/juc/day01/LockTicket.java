package com.atguigu.juc.day01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTicket {
    public static void main(String[] args) {
        SaleTicket saleTicket = new SaleTicket();

        new Thread(()->{for (int i=1;i<40;i++) saleTicket.sale();},"线程1").start();
        new Thread(()->{for (int i=1;i<40;i++) saleTicket.sale();},"线程2").start();
        new Thread(()->{for (int i=1;i<40;i++) saleTicket.sale();},"线程3").start();


        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<40;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    saleTicket.sale();
                }
            }
        },"线程1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<40;i++){
                    saleTicket.sale();
                }
            }
        },"线程1").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1;i<40;i++){
                    saleTicket.sale();
                }
            }
        },"线程1").start();
        */


    }
}

class SaleTicket{//资源类 = 熟悉 + 方法
    private static int num = 30;

    //获取lock锁,可以重复使用的锁
    private Lock lock = new ReentrantLock();

    public void sale(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        try {
            if (num>0){
                System.out.println("当前票数为"+num--+"张,"+ Thread.currentThread().getName()+"卖出一张。还剩"+num+"张。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
