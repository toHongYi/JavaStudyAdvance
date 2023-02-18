package com.leetCode.shortPath64;

/**
 * @Author lin.lvhua
 * @Date 2023/2/17 17:45
 * @Version 1.0
 * @Description 最小路径
 */
public class ShortPathDemo01 {

    public static void main(String[] args) {
//        int[][] arr = {
//                {1, 3, 1},
//                {1, 5, 1},
//                {4, 2, 1},
//        };  // 目标二维数组
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
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
//        int[][] arr = grid; // 引用传递不可用,创建对应表

        // 获取二维数组的行和列
        int row = grid.length;;
        int column = grid[0].length;

        int[][] arr = new int[row][column];
        // 考虑初始值
        // 初始化行
        arr[0][0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            arr[0][i] = arr[0][i-1] + grid[0][i]; // 前一个加当前
        }
        // 初始化列
        for (int i = 1; i < row; i++) {
            arr[i][0] = arr[i-1][0] + grid[i][0]; // 前一个加当前
        }


        for (int i =1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                arr[i][j] = Math.min(arr[i-1][j],arr[i][j-1]) + grid[i][j];
            }
        }

        // 获取二维数组中的最小值;

        return arr[grid.length-1][arr[0].length-1];
    }

    /**
     * 动态规划的做法
     */


    /**
     * dps递归的做法
     */

}
