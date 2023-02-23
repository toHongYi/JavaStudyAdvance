package com.leetCode.rainWater42;

import java.util.Stack;

/**
 * @Author lin.lvhua
 * @Date 2023/2/21 9:06
 * @Version 1.0
 * @Description
 */
public class TrappingRainWaterDemo003 {
    public static void main(String[] args) {

//        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = new int[]{1, 2, 0, 3, 2, 5};
        int[] height = new int[]{1, 0, 2};

        int trap = trapStack(height);
        System.out.println("trap = " + trap);

    }

    /**
     * 单调栈
     * @param height
     * @return
     */
    private static int trapStack(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        int current = 0;
        while (current < height.length) {

            //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
            while (!stack.empty() && height[current] > height[stack.peek()]) {
                int h = height[stack.peek()]; //取出要出栈的元素
                stack.pop(); //出栈
                if (stack.empty()) { // 栈空就出去
                    break;
                }
                int distance = current - stack.peek() - 1; //两堵墙之前的距离。
                int min = Math.min(height[stack.peek()], height[current]);
                sum = sum + distance * (min - h);
            }
            stack.push(current); //当前指向的墙入栈
            current++; //指针后移
        }
        return sum;

    }


    /**
     * 空间优化_处理其内容
     * 因为存在重复遍历,也就是说;
     * 从左往右遍历出现了两次,可尝试将其合并为一次遍历;存储两个遍历结果。
     *
     * @param height
     * @return
     */
    private static int trapDPBest(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右继续
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;

//        作者：windliang
//        链接：https://leetcode.cn/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
//        来源：力扣（LeetCode）
//        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    }

    public static int trap(int[] height) {
        int sum = 0;
        int len = height.length;

        // 遍历所有的列
        // 初始化位置,1开始到最后前一个;第一个和最后一个无意义。接不住雨水
        for (int i = 1; i < len - 1; i++) {
            int max_left = 0, max_right = 0;

            // 先求左边
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }

            // 再求右边
            for (int j = i + 1; j < len; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }

            // 计算出当前位置的雨水数;
            int min = Math.min(max_left, max_right);
            if (min > height[i]) {
                sum = sum + min - height[i];
            }
        }

        return sum;
    }

    /**
     * 动态规划版
     * 发现上方问题存在重复计算; 可将其转化为重复子问题;
     * 不如直接维护一个队列将关键信息进行存储;
     *
     * @param height
     * @return
     */
    public static int trapDP(int[] height) {

        int sum = 0;
        int len = height.length;
        int max_left = 0;
        int[] rightHigh = new int[len];

        // 右边,从尾到头遍历
        for (int i = len - 2; i > 0; i--) {
            if (rightHigh[i + 1] < height[i + 1]) {
                rightHigh[i] = height[i + 1];
            } else {
                rightHigh[i] = rightHigh[i + 1];
            }
        }

        // 左边,从头到尾遍历【存在重复遍历,试图将两次遍历合并为一次】
        for (int i = 1; i < len - 1; i++) {
            // 计算做左边最大;
            max_left = Math.max(max_left, height[i - 1]);

            // 计算出当前位置的雨水数;
            int min = Math.min(max_left, rightHigh[i]);
            if (min > height[i]) {
                sum = sum + min - height[i];
            }
        }

        return sum;
    }

}
