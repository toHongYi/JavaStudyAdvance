package com.atguigu.tree;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/17 21:37
 * @description:
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 说明,我们先手动创建该二叉树,后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        // 测试
        System.out.println("前序遍历");  // 1,2,3,4
        binaryTree.preOrder();

        // 测试
        System.out.println("中序遍历");  // 2,1,3,4
        binaryTree.infixOrder();

        // 测试
        System.out.println("后序遍历");  // 2,4,3,1
        binaryTree.postOrder();

        // 前序遍历
        // 前序遍历的次数 : 4
        System.out.println("前序遍历方式~");  // 1,2,3,4
        HeroNode resNode = binaryTree.preOrderSearch(5); //5
        if (resNode != null) {
            System.out.printf("找到了,信息为no=%d name %s", resNode.getNo(), resNode.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

        // 中序遍历
        // 中序遍历的次数 : 3
        System.out.println("中序遍历方式~");  // 1,2,3,4
        HeroNode res2Node = binaryTree.infixOrderSearch(5); //5
        if (res2Node != null) {
            System.out.printf("找到了,信息为no=%d name %s", res2Node.getNo(), res2Node.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }

        // 后序遍历
        // 后序遍历的次数 : 2
        System.out.println("后序遍历方式~");  // 1,2,3,4
        HeroNode res3Node = binaryTree.postOrderSearch(5); //5
        if (res3Node != null) {
            System.out.printf("找到了,信息为no=%d name %s", res3Node.getNo(), res3Node.getName());
        } else {
            System.out.printf("没有找到 no = %d 的英雄", 5);
        }
    }
}

// 定义BinaryTree 二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }


    // 前序遍历查找
    public HeroNode preOrderSearch(int no){
        if (root!=null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if (root!=null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    // 后续遍历查找
    public HeroNode postOrderSearch(int no){
        if (root!=null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

}

// 先创建HeroNode节点
class HeroNode {

    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 编写前序遍历的方法
    public void preOrder() {
        System.out.println(this); // 先输出父节点
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    // 中序遍历的方法
    public void infixOrder() {
        // 递归向左字树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this); // 先输出父节点
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历的方法
    public void postOrder() {
        // 递归向左字树中序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this); // 先输出父节点
    }

    // 前序遍历查找

    /**
     * @param no 查找no
     * @return 如果找到就返回该Node, 如果没有哦找到返回Null
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        // 则判断当前节点的左子节点是不是空,如果不为空,则递归前序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { // 说明我们左子树找到
            return resNode;
        }
        // 1.如果左递归前序找到,找到节点,则返回,否继判断
        // 2.当前的节点右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        // 必须返回;
        return resNode;
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        // 判断当前结点的左子节点是否为空,如果不为空,则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) { // 说明我们左子树找到
            return resNode;
        }
        System.out.println("进入中序查找");
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        // 1.如果左递归前序找到,找到节点,则返回,否继判断
        // 2.当前的节点右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        // 必须返回;
        return resNode;
    }

    // 后续查找
    public HeroNode postOrderSearch(int no){
        // 判断当前结点的左子节点是否为空,如果不为空,则递归中序查找
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) { // 说明我们左子树找到
            return resNode;
        }
        // 1.如果左递归前序找到,找到节点,则返回,否继判断
        // 2.当前的节点右子节点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) { // 说明我们左子树找到
            return resNode;
        }

        // 如果左右子树都没有找到,就比较当前结点是不是
        System.out.println("进入后序查找");
         // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        // 必须返回;
        return resNode;
    }

}

//
