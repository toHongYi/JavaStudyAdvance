package com.leetCode.rainWater42;

/**
 * @Author lin.lvhua
 * @Date 2023/2/20 10:31
 * @Version 1.0
 * @Description
 */
public class TrappingRainWaterDemo02 {
    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = new int[]{1, 2, 0, 3, 2, 5};
//        int[] height = new int[]{1, 0, 2};

        int trap = trapDP(height);
        System.out.println("trap = " + trap);
    }

    /**
     * 按列进行划分获取, 每次对单独的一列进行判定。 该列是否会存入水。
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0;
            //找出左边最高
            for (int j = i - 1; j >= 0; j--) {
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left, max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

    /**
     * 动态规划版【核心在于解决重复计算的问题】
     * @param height
     * @return 1、先求出最高的两根;
     */
    public static int trapDP(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) {
                sum = sum + (min - height[i]);
            }
        }
        return sum;
    }

}
