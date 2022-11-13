package com.atguigu.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/13 15:50
 * @description: 注意！二分查找的前提：该数组必须是有序的;
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr2[] = {1, 8, 10, 89, 1000, 1234};
        int arr[] = {1, 8, 10, 89, 1000, 1000,1000,1000, 1234}; // 重复值全部找到;

        int resIndex = binarySearch(arr2, 0, arr2.length - 1, 88);
        System.out.println("resIndex = " + resIndex);
        List<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("integers = " + integers);
    }

    /**
     * 二分查找算法
     *
     * @param arr      :
     * @param left     :
     * @param right    :
     * @param finalVal : 目标值
     * @return 如果找到就返回下标, 如果没有找到, 就返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int finalVal) {
        System.out.println(" binarySearch-find-Count~~");


        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 当left > right 说明递归整个数组,但是没有找到
        if (left > right) {
            return -1;
        }
        if (finalVal > midVal) { //向右递归
            return binarySearch(arr, mid + 1, right, finalVal);
        } else if (finalVal < midVal) { //向左递归
            return binarySearch(arr, left, mid - 1, finalVal);
        } else {
            return mid;
        }

    }

    // 完成一个课后思考题,

    /**
     * 课后思考题:  {1, 8, 10, 89, 1000,1000, 1234}; 当一个有序数组中,有多个相同的值时,如果将所有的值全部找到,
     * 比如这里的1000;
     * 思路分析:
     * 1、在找到 mid 索引值,不要马上返回
     * 2、向mid 索引的左边扫描,将所有满足1000,的元素下标,加入到集合中ArrayList;
     * 3、向mid 索引的右边扫描,将所有满足1000,的元素下标,加入到集合中ArrayList;
     * 4、将该ArrayList返回
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int finalVal) {
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 当left > right 说明递归整个数组,但是没有找到
        if (left > right) {
            return new ArrayList<>();
        }
        if (finalVal > midVal) { //向右递归
            return binarySearch2(arr, mid + 1, right, finalVal);
        } else if (finalVal < midVal) { //向左递归
            return binarySearch2(arr, left, mid - 1, finalVal);
        } else {
            //
            List<Integer> resIndexList = new ArrayList<Integer>();
            // 向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != finalVal) { //退出
                    break;
                }
                // 否则,就把temp放入集合中;
                resIndexList.add(temp);
                temp -= 1;// temp左移
            }
            resIndexList.add(mid); //

            // 向右扫描
            temp = mid + 1;
            while (true) {
                if (temp > arr.length-1 || arr[temp] != finalVal) { //退出
                    break;
                }
                // 否则,就把temp放入集合中;
                resIndexList.add(temp);
                temp += 1;// temp右移
            }

            return resIndexList;
        }

    }
}
