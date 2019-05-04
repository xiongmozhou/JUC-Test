package com.atguigu.juc.ZhouYangMianShiTi;

class SimpleExample{
    private volatile static  SimpleExample instance = null;

    private SimpleExample(){
        System.out.println(Thread.currentThread().getName()+"单例设计模式");
    }

    public static  SimpleExample getInstance(){
        if(instance == null){
            synchronized ("123"){
                if (instance == null){
                    instance = new SimpleExample();
                }
            }
        }
        return instance;
    }

    /**
     * 测试
     *
     */
    public static void main(String[] args){
        for(int i=0;i<10;i++){
            new Thread(()->{
                SimpleExample instance = SimpleExample.getInstance();
            },"线程"+i).start();
        }
    }
}
