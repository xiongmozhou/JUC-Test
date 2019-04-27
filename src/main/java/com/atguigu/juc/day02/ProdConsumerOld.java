package com.atguigu.juc.day02;


/**
 * 题目：现在两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 *
 * 1    高内聚低耦合前提下，线程操作资源类
 * 2    判断/干活/通知
 * 3    多线程编程需要注意，防止多线程的虚假唤醒，多线程的判断不可以使用if，用while
 */
public class ProdConsumerOld {
    public static void main(String[] args) throws Exception{
        Acc acc = new Acc();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    acc.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产1").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    acc.redcue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费1").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    acc.add();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产2").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    acc.redcue();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费2").start();
    }
}

class Acc{
    private static int num = 0;

    //添加的方法
    public synchronized void add() throws InterruptedException {
        //如果num不为零，等待消费
        while (num != 0){
            this.wait();
        }
        //num为零的时候，进行生产
        num++;
        System.out.println(Thread.currentThread().getName()+"；num="+num);
        //唤醒其他线程进行消费
        this.notifyAll();
    }

    //减少的方法
    public synchronized void redcue() throws InterruptedException {
        //如果num为零，等待生产
        while (num == 0){
            this.wait();
        }
        //num不为零的时候，进行消费
        num--;
        System.out.println(Thread.currentThread().getName()+"；num="+num);
        //唤醒其他线程进行消费
        this.notifyAll();
    }
}
