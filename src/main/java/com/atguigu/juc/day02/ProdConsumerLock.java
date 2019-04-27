package com.atguigu.juc.day02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdConsumerLock {
    public static void main(String[] args) {
        AccLock accLock = new AccLock();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //具体业务逻辑
                    accLock.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //具体业务逻辑
                    accLock.redcue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}

class AccLock{
    private static int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //添加的方法
    public void add() throws InterruptedException {
        lock.lock();
        try {
            //如果num不为零，等待消费
            while (num != 0){
                condition.await();
            }
            //num为零的时候，进行生产
            num++;
            System.out.println(Thread.currentThread().getName()+"；num="+num);
            //唤醒其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //减少的方法
    public synchronized void redcue() throws InterruptedException {
        lock.lock();
        try {
            //如果num为零，等待生产
            while (num == 0){
                condition.await();
            }
            //num不为零的时候，进行消费
            num--;
            System.out.println(Thread.currentThread().getName()+"；num="+num);
            //唤醒其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}