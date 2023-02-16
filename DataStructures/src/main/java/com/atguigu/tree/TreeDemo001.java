package com.atguigu.tree;

/**
 * @Author lin.lvhua
 * @Date 2023/2/16 13:34
 * @Version 1.0
 * @Description
 */
public class TreeDemo001 {
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
        node3.setLeft(node4);
        node3.setRight(node5);
        binaryTree.setNode(root);

        binaryTree.preOrder();


    }


}
class NodeTree{

    private RootNode node;

    public RootNode getNode() {
        return node;
    }

    public void setNode(RootNode node) {
        this.node = node;
    }

    // 前序遍历
    public void preOrder(){
        if (this.node != null){
            this.node.preOrder();
        }else {
            System.out.println("二叉树为空,前序遍历失败");
        }

    }

    // 中序遍历


    // 后序遍历


}


class RootNode {

    int i;
    String name;
    RootNode left;
    RootNode right;

    public RootNode(int i, String name) {
        this.i = i;
        this.name = name;
    }

    public RootNode(int i, String name, RootNode left, RootNode right) {
        this.i = i;
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RootNode getLeft() {
        return left;
    }

    public void setLeft(RootNode left) {
        this.left = left;
    }

    public RootNode getRight() {
        return right;
    }

    public void setRight(RootNode right) {
        this.right = right;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println("输出父节点"+this.name+this.i); // 输出父节点
        // 递归
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }

    }

    // 中序遍历

    // 后序遍历


}
