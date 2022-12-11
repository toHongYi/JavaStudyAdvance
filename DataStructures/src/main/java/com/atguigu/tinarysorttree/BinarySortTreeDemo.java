package com.atguigu.tinarysorttree;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/12/11 20:04
 * @description: 分三种情况
 * 情况一:
 * 删除叶子节点
 * 1.需要先找到要删除的结点 targetNode
 * 2.找到target的父结点 parent
 * 3.从targetNode的右子树找到最小的结点
 * 4.根据前面的情况来对应删除
 * <p>
 * 情况二: 删除只有一颗子树的节点 比如 1
 * 1.需要先找到要删除的结点 targetNode
 * 2.找到target的父结点 parent
 * 3.从targetNode的右子树找到最小的结点
 * 4.targetNode是parent的左子节点还是右子节点
 * 5.如果targetNode有左子节点
 * 5.1 如果targetNode 是parent的左子结点
 * parent.left = targetNode.left
 * 5.2 如果targetNode 是parent的右子结点
 * parent.right = targetNode.left
 * 6.如果targetNode有右子结点
 * 6.1 如果targetNode是 parent 的左子结点
 * parent.left = targetNode.right
 * 6.2 如果targetNode 是parent的右子结点
 * parent.right = targetNode.right
 * <p>
 * 情况三：删除带有两颗子树的节点()
 * 思路
 * 1.需要先找到要删除的结点 targetNode
 * 2.找到target的父结点 parent
 * 3.从targetNode的右子树找到最小的结点
 * 4.用一个临时变量，将最小结点的值保存temp = 11
 * 5.删除该最小结点
 * 6. targetNode.value = temp
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
//        int[] arr = {7, 3, 10, 12, 5, 1, 9, 0};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        // 中序遍历二叉树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder(); // 1,3,5,7,9,10,12

        //测试一下删除叶子结点
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);

        binarySortTree.delNode(1);
        binarySortTree.delNode(10); // 处理根结点为null;

        System.out.println("root=" + binarySortTree.getRoot());
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }

}


// 创建二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }


    // 查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 编写方法
    // 1.返回的 以node 为根节点的二叉排序树的最小结点的值
    // 2.删除node 为根结点的二叉排序树的最小结点

    /**
     * @param node 传入的结点(当作二叉排序树的根结点)
     * @return 返回的以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环的查找左子结点,就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这是target就指向额最小结点
        // 删除最小结点
        delNode(target.value);
        return target.value;
    }

    // 删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1、需要先去找到要删除的结点,targetNode
            Node targetNode = search(value);
            // 如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现当前这课二叉排序树最有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 去找到targetNode的父结点
            Node parent = searchParent(value);
            // 如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断target是父结点的左子结点,还是右子结点
                if (parent.left != null && parent.left.value == value) { // 是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { // 是右子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的结点
                int minValue = delRightTreeMin(targetNode.right);
                targetNode.value = minValue;

            } else { // 删除一颗子树的结点
                // 如果要删除的节点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果targetNode 是parent的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { // target 是parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { // 如果要删除的结点有右子结点
                    // 如果targetNode 是 parent 的左子结点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { // 如果 targetNode 是parent的 右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }

            }

        }
    }

    // 添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node; // 如果root为空则直接让root指向node
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空,不能遍历");
        }
    }


}

// 创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 查找要删除的结点

    /**
     * @param value 希望删除的结点的值
     * @return 如果找到返回该结点, 否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {  // 找到就是该结点
            return this;
        } else if (value < this.value) { // 如果查找的值小于当前结点,向左子树递归查找
            // 如果左子节点为空
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else { //如果查找的值不小于当前结点,向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除结点的父结点

    /**
     * @param value 要找的结点的值
     * @return 返回是要删除结点的父节点, 如果没有就返回nu
     */
    public Node searchParent(int value) {
        // 如果当前结点就是要删除结点的父节点,就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值,并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有找到父结点
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 添加结点的方法
    // 递归的形式添加结点,需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        // 判断传入结点的值,和当前子树的根节点的关系
        if (node.value < this.value) {
            // 如果当结点左子结点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else { // 添加的结点的值大于当前结点的值
            if (this.right == null) {
                this.right = node;
            } else {
                // 递归向右子树添加
                this.right.add(node);
            }
        }

    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
