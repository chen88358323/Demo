package com.autonavi.test.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chichen.cc on 2015/11/2.
 */
public class ListTest {
    private static final Logger ll = LoggerFactory.getLogger(ListTest.class);

    public static void main(String asd[]){
        List<String> list=new ArrayList<String>();
        list.add(null);
        System.out.println(list.size());

        ll.info("log "+list.size());
    }
}
