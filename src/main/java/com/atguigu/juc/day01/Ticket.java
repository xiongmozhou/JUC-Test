package com.atguigu.juc.day01;


//三个售票员 卖30 张票
public class Ticket {
    public static void main(String[] args) {
        Ticket_ziyuan ticket = new Ticket_ziyuan();

        new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<40;i++){
                    ticket.sale();
                }
            }
        },"线程1").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<40;i++){
                    ticket.sale();
                }
            }
        },"线程2").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i=1;i<40;i++){
                    ticket.sale();
                }
            }
        },"线程3").start();
    }
}

//资源类     操作
class  Ticket_ziyuan{

    private static int num = 30;

    public synchronized void sale(){
        if(num>0){
            System.out.println("当前票数为"+num--+"张,"+ Thread.currentThread().getName()+"卖出一张。还剩"+num+"张。");
        }
    }
}