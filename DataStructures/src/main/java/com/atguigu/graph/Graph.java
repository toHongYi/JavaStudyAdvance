package com.atguigu.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/12/17 23:13
 * @description:
 */
public class Graph {

    private ArrayList<String> vertexList; //存储顶点集合
    private int[][] edges; // 存储图对应的领结矩阵
    private int numOfEdges; // 表示边的数目
    // 定义给数组boolean[],记录某个结点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 测试一把图是否创建
        int n = 5; // 结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 循环的添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        // A->B  A-C  B-C  B-D  B-E
//        graph.insertEdge(v1,v2,weight);
        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1); // A-C
        graph.insertEdge(1, 2, 1); // B-C
        graph.insertEdge(1, 3, 1); // B-D
        graph.insertEdge(1, 4, 1); // B-E

        // 显示一把邻接矩阵
        graph.showGraph();

        // 测试一把,我们的dfs遍历是否ok
        System.out.println("深度遍历");
//        graph.dfs(); // A->B->C->D->E

        System.out.println("广度优先");
        graph.bfs(); // A->B->C->D->E

    }

    // 构造器
    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    // 得到第一个领接结点的下标 w

    /**
     * @param index
     * @return 如果存在, 就返回对应的下标, 否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个临接结点的下标来获取下一个领接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    // i的第一次就是0
    private void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该节点,输出
        System.out.print(getValueByIndex(i) + "->");
        // 将这个结点设置为已经访问过
        isVisited[i] = true;
        // 查找结点i的第一个领结点w
        int w = getFirstNeighbor(i);
        while (w != -1) { // 说明有
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }

    }

    // 对dfs进行重载,遍历所有的结点,并进行dfs
    public void dfs() {
        // 遍历所有的结点,进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 对一个结点进行广度优先遍历的一个方法
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头结点对应下标
        int w; // 邻接结点w的下标
        // 队列
        LinkedList queue = new LinkedList();
        // 访问结点,输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        // 标记为已访问
        isVisited[i] = true;
        // 将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个临结点的下标
            w = getFirstNeighbor(u);
            while (w != -1) { // 找到
                // 是否访问过
                if (isVisited[w]) {
                    System.out.println(getValueByIndex(i) + "=>");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以u去为前驱,找w后面的下一个邻结点
                w = getNextNeighbor(u, w); // 体现出广度优先

            }
        }
    }

    // 遍历所有的结点,都进行广度优先搜索
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    // 图中常用的方法
    // 返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    // 得到边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边

    /**
     * @param v1     表示点的下标既第几个顶点 "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;

    }

}
