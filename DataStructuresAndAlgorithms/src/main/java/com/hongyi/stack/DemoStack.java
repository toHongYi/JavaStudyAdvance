package com.hongyi.stack;

import java.util.List;
import java.util.Stack;

/**
 * @author HongYi
 * @version 1.0 对于数据结构和算法相关的知识体系还是需要深入的;
 * @date 2022/8/7 11:53
 * @description: 力扣的上面真题
 *      https://leetcode.cn/problems/exclusive-time-of-functions/comments/
 */
public class DemoStack {
    public static void main(String[] args) {

    }


    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<int[]> stack = new Stack<>();
        int[] res = new int[n];
        for (String log : logs) {
            String[] split = log.split(":");
            int id = Integer.parseInt(split[0]);
            int time = Integer.parseInt(split[2]);
            if ("start".equals(split[1])) {
                stack.push(new int[]{id, time});
            } else {
                int[] pop = stack.pop();
                int interval = time - pop[1] + 1;
                res[pop[0]] += interval;
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= interval;
                }
            }
        }

        return res;
    }
}
