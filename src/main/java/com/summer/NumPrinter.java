package com.summer;

/**
 * All rights Reserved, Designed By 10buns
 *
 * @Version V1.0
 * @Title: NumPrinter
 * @Package com.summer
 * @Author: summer
 * @Date: 2019-01-30 16:08
 */
public class NumPrinter extends Thread{

    private int threadId;

    volatile private static int num = 1;

    public NumPrinter(int threadId){
        this.threadId = threadId;
    }

    @Override
    public void run() {

        synchronized (NumPrinter.class){
            while (num <=100){
                if (num%2==threadId){
                    System.out.println(Thread.currentThread().getName() + ":" + num + ", ");
                    num++;
                    NumPrinter.class.notifyAll();
                }else {
                    try {
                        NumPrinter.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        new Thread(new NumPrinter(0)).start();
        new Thread(new NumPrinter(1)).start();
    }

}
