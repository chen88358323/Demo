package org.cc.algorithm.quanpailie;

import java.util.*;

/**
 * Created by cc on 16-8-17.
 * 全排列～～`
 */
public class Quanpailie {
    public static void permutation1(String str ,String result ,int len){
        /* 全排列 递归实现
      递归树：
          str:          a            b                c
                      ab ac         ba   bc         ca   cb
        result:        abc acb        bac    bca      cab   cba
         */

        //结果 开始传入""   空字符进入   len   是这个数的长度
        if(result.length()==len){            //表示遍历完了一个全排列结果
            System.out.println(result);
        }
        else{
            for(int i=0;i<str.length();i++){
                if(result.indexOf(str.charAt(i))<0){    //返回指定字符在此字符串中第一次出现处的索引。
                    //System.out.println("字母："+str.charAt(i));
                    permutation1(str, result+str.charAt(i), len);
                }
            }
        }
    }



    public static void main(String args[]) throws Exception {
        String s = "abc";
        String result = "";
//        permutation1(s, result, s.length());
        String[] str = {"a","b","c"};

        //        permutation1("", result, s.length());


//        permutation2(str, 0, 2); //输出str[0..2]的所有排列方式

        List<String> l=new LinkedList<String>(Arrays.asList("a", "b", "c","d"));
        permutation3(l,"");
//        permutation3(null,"");
    }

    public static void permutation2(String[] str , int first,int end) {
        //输出str[first..end]的所有排列方式
        if(first == end) {    //输出一个排列方式
            for(int j=0; j<= end ;j++) {
                System.out.print(str[j]);
            }
            System.out.println();
        }

        for(int i = first; i <= end ; i++) {
            swap(str, i, first);
            permutation2(str, first+1, end);  //固定好当前一位，继续排列后面的
            swap(str, i, first);
        }
    }

    private static void swap(String[] str, int i, int first) {
        String tmp;
        tmp = str[first];
        str[first] = str[i];
        str[i] = tmp;
    }
    public static void permutation3(List candidate, String prefix) {
//        if(candidate.size()==1){
//            //一个元素时候，输出……；不过这个实现，这个if传递不到递归中，仅仅是测试代码中list的size为1时调用一次
//            System.out.println(candidate.get(0));
//        }
        if(candidate==null||candidate.size()==0)
            return;
        if(candidate.size()==2){
            //输出两个元素时候,颠倒顺序
            System.out.println(prefix+candidate.get(0)+candidate.get(1));
            System.out.println(prefix+candidate.get(1)+candidate.get(0));
        }else{
            for (int i = 0; i < candidate.size(); i++) {
//                permutation3(candidate, prefix + candidate.remove(i));//递归调用
                List temp = new LinkedList(candidate);
                permutation3(temp, prefix + temp.remove(i));//递归调用
            }
        }
    }
}
