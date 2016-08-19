package com.autonavi.test.thread.testvolatide;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chichen.cc on 2016/1/12.
 */
public class Test {

    private int i = 10;
    private Object object = new Object();

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        Thread thread1 = test.new MyThread("Thread-1");
        Thread thread2 = test.new MyThread("Thread-2");
        thread1.start();
        thread2.start();
        ((MyThread)thread1).flag = false;
        ((MyThread)thread2).flag = false;
        final Lock lock = new ReentrantLock();

        //第一个条件当屏幕上输出到3
        final Condition cc = lock.newCondition();
        try {
            cc.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class MyThread extends Thread {
        volatile boolean flag = true;

        MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (flag) {
                synchronized (object) {
                    i++;
                    System.out.println("i:" + i);
                    System.out.println("线程" + Thread.currentThread().getName() + "进入睡眠状态");
                    try {
                        Thread.currentThread().sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "睡眠结束");
                    i++;
                    System.out.println("i:" + i);
                }
            }
        }
    }
}
