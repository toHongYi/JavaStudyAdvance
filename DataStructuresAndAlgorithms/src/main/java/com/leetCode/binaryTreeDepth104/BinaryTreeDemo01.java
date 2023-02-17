package com.leetCode.binaryTreeDepth104;

/**
 * @Author lin.lvhua
 * @Date 2023/2/17 11:16
 * @Version 1.0
 * @Description
 */
public class BinaryTreeDemo01 {

    public static void main(String[] args) {
        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        Node root  = new Node(1, "宋江");
        Node node2 = new Node(2, "吴用");
        Node node3 = new Node(3, "卢俊义");
        Node node4 = new Node(4, "林冲");
        Node node5 = new Node(5, "关胜");

        // 说明,我们先手动创建该二叉树,后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);

        binaryTree.setNode(root);
        // 前序遍历
        binaryTree.proOrder();

    }

    // 使用递归进行实现
    public int maxDepth(Node root) {
        if (root == null){
            return 0;
        }
        int left = maxDepth(root.left); //左节点是否存在
        int right = maxDepth(root.right);
        return Math.max(left,right)+1;

    }

}


class BinaryTree{

    Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    // 前序遍历
    public void proOrder() {
        if (this.node != null){
            this.node.preOrder(this.node);
        }else {
            System.out.println("无法执行前缀遍历");
        }
    }

}

class Node{
    Integer index;
    String name;
    Node left;
    Node right;

    public Node(Integer index, String name) {
        this.index = index;
        this.name = name;
    }

    public Node(String name, Integer index, Node left, Node right) {
        this.name = name;
        this.index = index;
        this.left = left;
        this.right = right;
    }


    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    // 结点前序遍历
    public void preOrder(Node node) {
        System.out.println("当前结点为:" + node.name);
        if (node.left != null) {
            node.preOrder(node.left);
        }
        if (node.right != null) {
            node.preOrder(node.right);
        }

    }
}
