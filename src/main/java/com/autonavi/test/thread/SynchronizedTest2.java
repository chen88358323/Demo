package com.autonavi.test.thread;

/**
 * Created by chichen.cc on 2015/12/9.
 */
public class SynchronizedTest2 {
    public static void main(String[] args) throws InterruptedException {
        final Father son = new Son();
        Thread sonT = new Thread() {
            @Override
            public void run() {
                son.say();
            }
        };

        Thread sonT2 = new Thread() {
            @Override
            public void run() {
                son.walk();
            }
        };
        sonT2.start();
        sonT.start();
    }

    static class Father {
        public synchronized void say() {
            System.out.println("father say...");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("father say over...");
        }

        public synchronized void walk() {
            System.out.println("father walk...");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("father walk yield...");
            Thread.yield();
            System.out.println("father walk over...");
        }
    }

    static class Son extends Father {
        public synchronized void say() {
            System.out.println("son say...");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("son say over...");
        }

        public void walk(){
            System.out.println("son walk...");
            super.walk();
        }
    }
}
