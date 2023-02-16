package com.leetCode.rainWater42;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @Author lin.lvhua
 * @Date 2023/2/15 11:21
 * @Version 1.0
 * @Description : 获取到有效数据
 *
 *      n == height.length
 *      1 <= n <= 2 * 104
 *      0 <= height[i] <= 105
 *
 */
public class TrappingRainWaterDemo01 {

    public static void main(String[] args) {

        int[] arr = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height = new int[]{4,2,0,3,2,5};

        int trap = trap(height);
        System.out.println("trap = " + trap);

    }

    /**
     *
     * @param height
     * @return
     * 1、先求出最高的两根;
     */
    public static int trap(int[] height) {

        int[] secondHeight = height;

        List<Integer> indexList = new ArrayList<>();
        if (height.length==1){
            return 0;
        }

        // 求出最高的两个;
        Integer maxIndex2 = findMax2(height,indexList);
        Integer secIndex2 = findMax2(height,indexList);
        Integer tirIndex2 = findMax2(height,indexList);
        Integer forIndex2 = findMax2(height,indexList);

        // 计算该下标带来的容量,去掉已存储下标的位置
        Integer capacity1 = quertCapacity(maxIndex2,secIndex2,secondHeight,indexList);
        Integer capacity2 = quertCapacity(secIndex2,tirIndex2,secondHeight,indexList);
        Integer capacity3 = quertCapacity(tirIndex2,forIndex2,secondHeight,indexList);

        System.out.println("capacity = " + capacity1);
        return capacity1 + capacity2 + capacity3;
    }

    /**
     * 计算给定数据的容量;
     * @param maxIndex2 最大值下标
     * @param secIndex2 第二大值下标
     * @param secondHeight 原始数组
     * @param height 存储下标的集合
     * @return
     */
    private static Integer quertCapacity(Integer maxIndex2, Integer secIndex2, int[] secondHeight, List<Integer> height) {

        // 计算给定面积作为我们的容量设置
        Integer capacity = (secIndex2-maxIndex2) * Math.min(secondHeight[maxIndex2],secondHeight[secIndex2]);
        // 去掉不能被计算的容量范围

        // 取出一组数组中距离差别最大的两个
        // 1、取最小
        // 2、取最大
        AtomicReference<Integer> max = null;
        AtomicReference<Integer> min = null;
        height.stream().max(Comparator.comparing(Integer::valueOf)).ifPresent(t->{
            max.set(t);
        });
        height.stream().min(Comparator.comparing(Integer::valueOf)).ifPresent(t-> {
            min.set(t);
        });

        Integer ignoreCapacity = (max.get()-min.get()) * Math.min(secondHeight[maxIndex2],secondHeight[secIndex2]);

        return ignoreCapacity;
    }

    /**
     *
     * @param height
     * @param indexList
     * @return
     *    除了给定下标数组外最height的下标;
     */
    private static Integer findMax(List<Integer> height, List<Integer> indexList) {

        AtomicInteger index = new AtomicInteger();
        //
        height.stream().filter(t->height.contains(height.get(t))).max(Comparator.comparing(Integer::valueOf)).ifPresent(t->{
            System.out.println(t);
            System.out.println(height.indexOf(t));
            index.set(t);
        });
        indexList.add(index.intValue());
        return index.intValue();
    }


    /**
     *
     * @param collect
     * @param indexList
     * @return
     *    计算完之后将其height设置为 0;
     */
    private static Integer findMax2(int[] collect, List<Integer> indexList) {

        AtomicInteger index = new AtomicInteger();
        List<Integer> height = Arrays.stream(collect).boxed().collect(Collectors.toList());

        // 是
        height.stream().max(Comparator.comparing(Integer::valueOf)).ifPresent(t->{
            int indexOf = height.indexOf(t);
            System.out.println(indexOf);
            index.set(indexOf);
            System.out.println("最高的下标位置为"+indexOf+",值为"+collect[indexOf]);
            collect[indexOf] = 0;
        });


        indexList.add(index.intValue());
        return index.intValue();
    }

}
