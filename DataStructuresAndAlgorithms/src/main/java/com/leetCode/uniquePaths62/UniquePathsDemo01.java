package com.leetCode.uniquePaths62;

/**
 * @Author lin.lvhua
 * @Date 2023/2/17 13:29
 * @Version 1.0
 * @Description
 */
public class UniquePathsDemo01 {

    public static void main(String[] args) {

        int i = uniquePathsDP(1, 3);

        System.out.println("i = " + i);
    }

    public static int uniquePathsDP(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }


//    作者：LeetCode-Solution
//    链接：https://leetcode.cn/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    public static int uniquePaths2(int m, int n) {
        if (m - 1 == 0 || n - 1 == 0) {
            return 1;
        }
        int[][] arr = new int[m+1][n+1];
        return dps(arr,m,n);
    }

    // 递归调用
    private static int dps(int[][] arr, int m, int n) {
        if (m - 1 == 0 || n - 1 == 0) {
            return 1;
        }
        if (arr[m][n] > 0) {
            return arr[m][n];
        }
        return dps(arr, m - 1, n) + dps(arr, m, n - 1);
    }

    public static int uniquePaths(int m, int n) {
        if (m - 1 == 0 || n - 1 == 0) {
            return 1;
        }

        int conque = 1;
        conque = uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
        return conque;
    }

}
