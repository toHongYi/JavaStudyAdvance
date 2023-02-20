package com.leetCode.rainWater42;

/**
 * @Author lin.lvhua
 * @Date 2023/2/20 13:13
 * @Version 1.0
 * @Description 开始进行自主撰写接雨水相关代码
 */
public class TrappingRainByMMyself01 {

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = new int[]{1, 2, 0, 3, 2, 5};
//        int[] height = new int[]{1, 0, 2};

        int trap = trap(height);
        System.out.println("trap = " + trap);
    }

    /**
     * 按列进行划分和积累
     * 可将该问题进行解构和结构化处理,形成计算机可以解决的处理方式
     * 该字符串中,每一个字符所能容纳的可使用空间范围,
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        // 设置总量
        int sum = 0;
        // 设置数组长度
        int len = height.length;

        // 遍历整个数组
        // 首位无意义,接不住雨水; 最后一位也无意义;
        for (int i = 1; i < len - 1; i++) {
            // 获取左边最高和右边最高
            int max_left = 0, max_right = 0;

            // 获取从目标点开始的左边做高长度
            for (int j = i - 1; j >= 0; j--) {
                // 判断是否大于当前高度,再执行交换
                if (height[j] > max_left) {
                    max_left = height[j];
                }
            }

            for (int j = i + 1; j < len; j++) {
                // 判断是否大于当前高度,再执行交换
                if (height[j] > max_right) {
                    max_right = height[j];
                }
            }

            // 获取从目标点开始的右边做高长度

            int min = Math.min(max_left, max_right);
            // 设计容量计算公式: min(left,right) - height
            if (height[i] < min) {
                sum = sum + min - height[i];
            }
        }

        return sum;
    }

}
