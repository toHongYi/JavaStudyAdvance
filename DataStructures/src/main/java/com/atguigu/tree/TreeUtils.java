package com.atguigu.tree;

/**
 * @Author lin.lvhua
 * @Date 2023/2/16 13:47
 * @Version 1.0
 * @Description
 */
public class TreeUtils {

    private Integer res = 0;

    public static void main(String[] args) {
        // 先需要创建一颗二叉树
        NodeTree binaryTree = new NodeTree();
        // 创建需要的节点
        RootNode root  = new RootNode(1, "宋江");
        RootNode node2 = new RootNode(2, "吴用");
        RootNode node3 = new RootNode(3, "卢俊义");
        RootNode node4 = new RootNode(4, "林冲");
        RootNode node5 = new RootNode(5, "关胜");

        // 说明,我们先手动创建该二叉树,后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setNode(root);

//        int i = maxDepth(binaryTree);
//        System.out.println("i = " + i);

    }

    public int maxDepth(NodeTree root) {
        dps(root.getNode(),0);
        return this.res;
    }

    public void dps(RootNode node,Integer length){

        if (node == null){
            // 选取最深的叶子节点作为结果
            res = Math.max(this.res,length);
        }

        if (node.left!= null){
            dps(node.left,length++);
        }
        if (node.right!=null){
            dps(node.right,length++);
        }

    }
}
