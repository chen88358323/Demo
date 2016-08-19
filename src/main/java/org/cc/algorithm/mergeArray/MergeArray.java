package org.cc.algorithm.mergeArray;

/**
 * Created by cc on 16-8-19.
 */
public class MergeArray {

    public static void main(String[] args) {
        int[] arr1 = { 10, 20, 30, 40, 50 };
        int[] arr2 = {12, 31, 56, 78, 90, 100, 201};
        int[] res = mergeArr(arr1, arr2);
        for (int i : res)
        {
            System.out.print(i + " ");
        }
    }

    private static int[] mergeArr(int[] arr1, int[] arr2) {
        int[] res = new int[arr1.length + arr2.length];
        // i 标识 arr1数组, j 标识 arr2数组 , k标识res数组
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) { //数组长度作为条件
            if (arr1[i] > arr2[j])
            {
                res[k++] = arr2[j++];
            }
            else {
                res[k++] = arr1[i++];
            }
        }
        while(i < arr1.length)
        {
            res[k++] = arr1[i++];
        }
        while(j < arr2.length)
        {
            res[k++] = arr2[j++];
        }
        return res;   //返回有序数组
    }
}
