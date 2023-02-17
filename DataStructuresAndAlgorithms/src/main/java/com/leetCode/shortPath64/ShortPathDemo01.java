package com.leetCode.shortPath64;

/**
 * @Author lin.lvhua
 * @Date 2023/2/17 17:45
 * @Version 1.0
 * @Description 最小路径
 */
public class ShortPathDemo01 {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1},
        };  // 目标二维数组

        int i = minPathSum(arr);
        System.out.println("获取到的最短路径为: " + i);

    }


    /**
     * 获取给定二维数组的最短路径
     *
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        // 记录该二维数组的缓存表;
        int[][] arr = grid; // 创建对应表


        return 0;
    }

    /**
     * 动态规划的做法
     */


    /**
     * dps递归的做法
     */

}
