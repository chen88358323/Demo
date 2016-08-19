package org.cc.algorithm.mergeArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cc on 16-8-19.
 */
public class Lianxu {
    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 5, 6, 8, 8,8,8,8,9, 9, 3, 9,3,4,4 };
        int[] arr1 = new int[] { 1, 2, 3, 4, 5, 6, 1, 9 };
        int[] arr2 = new int[] { 2, 3, 9 };
        //System.out.println(IsSeries(arr, 1, 10).toString());
//      System.out.println(IsSeries(arr1, 1, 15).toString());
//      System.out.println(IsRepeat(arr).toString());
        System.out.println(IsSeries(arr1, 0, 7).toString());

    }


    /**
     * 获取一个数组里面的重复的项
     * @param num
     * @return
     */
    private static List<Integer> IsRepeat(int[] num) {

        List<Integer> r = new ArrayList<Integer>();
        // 循环数组中所有的数据
        for (int i = 0; i < num.length; i++) {
            if (i < num.length - 1) {
                if (num[i] == num[i + 1]) {
                    // 当数据处于循环再开始时
                    r.add(num[i]);
                }

            }
        }
        return r;

    }

    /**
     * 获取一个数组中部连续的项
     * @param num 目标数组
     * @param start 数组的开始项
     * @param end 数组的结束项
     * @return
     */
    private static List<Integer> IsSeries(int[] num, int start, int end) {

        List<Integer> r = new ArrayList<Integer>();
        // 循环数组中所有的数据
        for (int i = 0; i < num.length; i++) {
            // 判断是不是到了数组末尾
            if (i < num.length - 1) {
                // 排除重复数据的情况
                // 当数据不连续的时候
                if (num[i] + 1 != num[i + 1]) {
                    // 当数据处于循环再开始时
                    if (num[i + 1] - num[i] < 0) {
                        // 判断最后的数字是不是最大的开始数字start
                        int cha = end - num[i];
                        for (int j = 1; j <= cha; j++) {
                            r.add(num[i] + 1 * j);
                        }
                        // 判断刚开始的数字是不是end
                        int cha1 = num[i + 1] - start;
                        for (int j = 0; j < cha1; j++) {
                            r.add(start + 1 * j);
                        }
                    } else {
                        // 处理既不连续又不是开头和结尾的情况
                        int cha = num[i + 1] - num[i];
                        for (int j = 1; j < cha; j++) {
                            r.add(num[i] + 1 * j);
                        }
                    }

                }

            }
        }
        return r;

    }

}
