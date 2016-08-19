package net.threadgroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chichen.cc on 2016/1/11.
 */
public class TestInnerFinal {
    public static void main(String asd[]){
        final List<String> l = new ArrayList<String>();
        l.add("1");
        l.add("2");
        l.add("3");

        Thread t = new Thread(new Runnable() {

            public void run() {
                while(true){
                    int size = l.size();
                    try {
                        Thread.currentThread().sleep(50);
                    } catch (InterruptedException e) {
// TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(size != l.size()){
                        System.out.println(size);
                        System.out.println(l.size());
                        System.out.println("+" + l.size());
                    }
                }
            }
        });

        t.start();
        try {
            Thread.currentThread().sleep(50);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        l.add("4");
        l.add("5");
    }
}
