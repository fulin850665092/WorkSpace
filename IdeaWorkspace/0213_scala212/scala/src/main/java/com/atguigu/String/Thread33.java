package com.atguigu.String;

public class Thread33{

    public static void main(String[] args)throws  Exception {
        new Thread(
                new Runnable() {
                    public void run() {

                    }
                }
        ).start();
        Thread thread2 = new Thread();
        Thread thread1 = new Thread();
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
        thread1.sleep(1000);
        thread2.wait(1000);
    }
}
