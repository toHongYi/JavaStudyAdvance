package com.atguigu.recursion;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/10/7 22:29
 * @description: 8皇后问题
 */
public class Queue8 {

    // 定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array,保存皇后放置位置的结果,比如 arr[8]={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] args) {
        // 测试一把,八皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        //
        System.out.printf("一共有%d解法",count);
        System.out.println();
        System.out.printf("一共判断冲突得次数有%d",judgeCount); // 1.5W 次
        System.out.println();
    }

    // 编写一个方法、放置第n个皇后
    // 特比注意: check 是每一次递归时、进入到check方法中 都有一套 for(int i = 0; i < max; i++),因此会有回溯;
    private void check(int n){
        if (n == max) { // n = 8,其实八个皇后就已然放好【放第九个皇后】
            print();
            return;
        }

        // 依次放入皇后,并判断是否冲突
        for (int i = 0;i<max;i++){
            // 先把当前这个皇后 n 放到该行的第1列;
            array[n] = i;
            // 判断当放置第n个皇后到i列时,是否冲突
            if (judge(n)){ //不冲突
                // 接着放n+1个皇后,既开始递归
                check(n+1); // 8个皇后,
            }
            // 如果冲突,就继续执行 array[n] = i;既将第n个皇后,放置在本行得后移得一个位置
        }
    }

    // 查看当我们放置第n个皇后的时候,就去检测该皇后是否和前面已经摆放的皇后冲突;
    /**
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 同一列 || 同一个斜线
            // 1、array[i] == array[n] 表示：第n个皇后是否和前面的n-1个皇后在同一列;
            // 2、Math.abs(n-i) == Math.abs(array[n-array[i]]) 表示判断第n个皇后是否和第i个皇后是否在同一斜线;
            //   n = 1 放置第2列1 n = 1 array[1] = 1
            //  Math.abs(1-0) == 1 Math.abs(array[n] - array[i]) = Math.abs(1-0) = 1
            // 3、判断是否在同一行,没有必要,n每次都在递增
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    // 写一个方法,可以将皇后摆放的位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
        }
        System.out.println();
    }

}
