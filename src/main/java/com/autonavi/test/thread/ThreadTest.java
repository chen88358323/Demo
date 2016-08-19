package com.autonavi.test.thread;

/**
 * Created by chichen.cc on 2015/10/31.
 */
public class ThreadTest {
    private static boolean ready= true;;
    private static int number;
    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
    public static void main(String[] args) throws Exception {

        number = 1;
        ready = true;
        new ReaderThread().start();
    }
}
