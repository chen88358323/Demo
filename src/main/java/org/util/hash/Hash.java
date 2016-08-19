package org.util.hash;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by chichen.cc on 2015/10/27.
 */
public class Hash {
    private static Logger ll =  LoggerFactory.getLogger(Hash.class);

    public static void  main(String asd[]){
HashMap a=new HashMap();
ll.info(getHashcode("testcccc")+"");
System.out.print(getHashcode("testcccc"));
    }
    private static int getHashcode(String str){
        return str.hashCode();
    }
}
