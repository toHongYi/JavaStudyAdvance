package com.atguigu.tree;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/21 10:31
 * @description:
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();// 1，2，4，5，3，6，7
    }
}

// 编写一个Arra yBinaryTree,实现顺序存储二叉树遍历

class ArrBinaryTree {
    private int[] arr; //存储数据结点的数据

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }


    // 编写一个方法,完成顺序存储二叉树的一个前序遍历
    // 重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
    /**
     * @param index 数组的下标
     */
    public void preOrder(int index) {
        // 如果数组为空,或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

}
