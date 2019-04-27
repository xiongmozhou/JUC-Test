package com.atguigu.juc.day02;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 备注：多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 *
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * 来10轮
 */
public class ConditionDemo {
    public static void main(String[] args) {
        TestCond testCond = new TestCond();

        new Thread(() -> {
            try {
                //具体业务逻辑
                for (int i = 0; i < 10; i++) {
                    testCond.TestABC(0,5,"AA");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程A=>").start();

        new Thread(() -> {
            try {
                //具体业务逻辑
                for (int i = 0; i < 10; i++) {
                    testCond.TestABC(1, 10, "BB");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程B=>").start();

        new Thread(() -> {
            try {
                //具体业务逻辑
                for (int i = 0; i < 10; i++) {
                    testCond.TestABC(2, 15, "CC");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"线程C=>").start();

        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}

class TestCond{
    private static int num = 0;
    private Lock lock = new ReentrantLock();
//    private Condition cond_A = lock.newCondition();
//    private Condition cond_B = lock.newCondition();
//    private Condition cond_C = lock.newCondition();
    private Condition cond = lock.newCondition();


    public void TestABC(int flag,int count,String str){
        lock.lock();
        try {
            //判断
            while(num %3 != flag){
                cond.await();
            }
            //业务逻辑
            for (int i = 0; i < count; i++) {
                System.out.println(Thread.currentThread().getName()+str);
            }
            //唤醒
            num++;
            cond.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /*
    public void TestA(){
        lock.lock();
        try {
            //判断
            while(num != 1){
                cond_A.await();
            }
            //业务逻辑
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"AA");
            }
            //唤醒
            num++;
            cond_B.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void TestB(){
        lock.lock();
        try {
            //判断
            while(num != 2){
                cond_B.await();
            }
            //业务逻辑
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"BB");
            }
            //唤醒
            num++;
            cond_C.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void TestC(){
        lock.lock();
        try {
            //判断
            while (num != 3){
                cond_C.await();
            }
            //业务逻辑
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"CC");
            }
            //唤醒
            num = 1;
            cond_A.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    */
}