package org.cc.algorithm.quicksort;

import java.util.Arrays;

/**
 * Created by cc on 16-8-15.
 */
public class QuickSort {

    /**
     * 冒泡排序
     *
     * @param data
     *            目标数组
     */
    public static void bubbleSort(int[] data) {

        for (int i = 0; i < data.length - 1; i++) {// 控制趟数
            for (int j = 0; j < data.length - i - 1; j++) {

                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }

    }

    /**
     * 选择排序
     *
     * @param data
     *            目标数组
     */
    public static void selectSort(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }

        for (int i = 0; i < data.length - 1; i++) {
            int min = i;// 将当前下标定为最小值下标
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[min]) {
                    min = j;
                }
            }

            if (i != min) {
                int tmp = data[i];
                data[i] = data[min];
                data[min] = tmp;
            }
        }
    }

    /**
     * 选择排序 此算法效率不如上面的高
     *
     * @param data
     */
    public static void selectSort2(int[] data) {

        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[i]) {
                    int temp = data[j];
                    data[j] = data[i];
                    data[i] = temp;
                }
            }
        }
    }

    /**
     * 快速排序算法
     *
     * @param data
     *            目标数组
     * @param start
     *            起始位
     * @param end
     *            结束位
     */
    public static void quickSort(int[] data, int start, int end) {
        // 设置关键数据key为要排序数组的第一个元素，
        // 即第一趟排序后，key右边的数全部比key大，key左边的数全部比key小
        int key = data[start];
        // 设置数组左边的索引，往右移动比key大的数
        int i = start;
        // 设置数组右边的索引，往左移动比key小的数
        int j = end;
        // 如果左边索引比右边索引小，则还有数据没有排序
        while (i < j) {
            while (data[j] > key && j > i) {
                j--;
            }
            data[i] = data[j];

            while (data[i] < key && i < j) {
                i++;
            }
            data[j] = data[i];
        }
        // 此时 i==j
        data[i] = key;

        // 递归调用
        if (i - 1 > start) {
            // 递归调用，把key前面的完成排序
            quickSort(data, start, i - 1);
        }
        if (i + 1 < end) {
            // 递归调用，把key后面的完成排序
            quickSort(data, i + 1, end);
        }
    }
    /**
     * description : 快速排序
     * @autor kwzhang
     * modify :2012-6-20
     *
     * @param left
     * @param right
     * @return
     */
    static void quickSort2(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            quickSort2(n, left, dp - 1);
            quickSort2(n, dp + 1, right);
        }
    }

    static int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] p = { 34, 21, 54, 18, 23, 76, 38, 98, 45, 33, 27, 51, 11, 20, 79,
                30, 89, 41 };

        int[] p2=new int[10];
        for (int i = 0; i < p2.length; i++) {
            p2[i]=(int)(Math.random()*(100+1));
            System.out.print(p2[i] + " ");
        }
        long start = System.currentTimeMillis();
        System.out.println("");
//      QuickSort.bubbleSort(p);// 冒泡排序
//      QuickSort.selectSort(p);// 选择排序
//      QuickSort.selectSort2(p);// 选择排序2
        QuickSort3.quickSort(p);// 快速排序
//        QuickSort.quickSort2(p, 0, p.length - 1);// 快速排序2
        System.out.println("所用时间：" + (System.currentTimeMillis() - start));
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }
    }


}
class QuickSort3{
    public static void quickSort(int[] arr){
        qsort(arr, 0, arr.length-1);
    }
    private static void qsort(int[] arr, int low, int high){
        if (low < high){
            int pivot=partition(arr, low, high);        //将数组分为两部分
            qsort(arr, low, pivot-1);                   //递归排序左子数组
            qsort(arr, pivot+1, high);                  //递归排序右子数组
        }
    }
    private static int partition(int[] arr, int low, int high){
        int pivot = arr[low];     //枢轴记录
        while (low<high){
            while (low<high && arr[high]>=pivot)
                --high;
            arr[low]=arr[high];             //交换比枢轴小的记录到左端
            while (low<high && arr[low]<=pivot)
                ++low;
            arr[high] = arr[low];           //交换比枢轴小的记录到右端
        }
        //扫描完成，枢轴到位
        arr[low] = pivot;
        //返回的是枢轴的位置
        System.out.println(Arrays.toString(arr) );
        System.out.println("position is "+low+" val is "+arr[low]);
        return low;
    }
}