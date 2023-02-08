package com.leetCode.waterMost11;

/**
 * @Author lin.lvhua
 * @Date 2023/2/8 10:57
 * @Version 1.0
 * @Description 尝试使用双指针吧
 */
public class WaterMostDemo02 {

    public static void main(String[] args) {

        // 对于高度进行判断;
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int[] height = new int[]{0,1};
//        int[] height = new int[]{9384,887,2778,6916,6328,337,6506,847,1730,1314,5858,6125,3896,9583,546,8815,3368,5435,365,4044,3751,2619,2246,2847,3452,2922,3556,2380,7489,7765,8229,9842,2351,5194,1501,7035,7765,125,4915,6988,5857,3744,6492,2228,8366,9860,1937,1433,2552,6438,9229,3276,5408,1475,6122,8859,4396,6030,1238,8236,3794,5819,4429,6144,1012,5929,9530,8777,2405,4444,5764,4614,4539,8607,6841,2905,4819,5129,689,7370,7918,9918,6997,3325,7744,9471,2184,8491,5500,9773,6726,5645,5591,7506,8140,2955,9787,7670,8083,8543,8465,198,9508,9356,8805,6349,8612,3623,7829,9300,7344,5747,5569,4341,5423,3312,3811,7606,1802,5662,3731,4879,1306,9321,8737,9445,8627,8523,3466,6709,3417,8283,3259,2925,7638,2063,5625,2601,2037,3453,1900,9380,5551,7469,72,974,7132,3882,4931,8934,5895,8661,164,7200,7982,8900,2997,2960,3774,2811};

        int area = maxAreaTwo(height);
        System.out.println("area = " + area);

    }

    /**
     * 线性表动态规划
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    /**
     * 线性表动态规划
     *
     * @param height
     * @return
     */
    public static int maxAreaTwo(int[] height) {

        int l = 0, r = height.length - 1, max = 0;
        while (l < r) {
            // 左右指针
            if (height[l] < height[r]) {
                max = Math.max(max, (r - l) * height[l]);
                l++;
            } else {
                max = Math.max(max, (r - l) * height[r]);
                r--;
            }
        }
        return max;
    }
}
