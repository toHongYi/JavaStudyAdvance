package com.atguigu.kruskal;

import java.util.Arrays;

/**
 * @author HongYi
 * @version 1.0
 * @date 2023/1/12 21:55
 * @description:
 */
public class KruskalCase {

    private int edgeNum; // 边的个数
    private char[] vertexs; // 顶点数组
    private int[][] matrix; // 邻接矩阵
    // 使用 INF 表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
        /*A*/  {   0,  12, INF, INF, INF,  16,  14},
        /*B*/  {  12,   0,  10, INF, INF,   7, INF},
        /*C*/  { INF,  10,   0,   3,   5,  16, INF},
        /*D*/  { INF, INF,   3,   0,   4, INF, INF},
        /*E*/  { INF, INF,   5,   4,   0,   2,   8},
        /*F*/  {  16,   7,   6, INF,   2,   0,   9},
        /*G*/  {  14, INF, INF, INF,   8,   9,   0},
        };
        // 创建KrusalCase 对象实例
        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        // 输出构建
        kruskalCase.print();
        kruskalCase.kruskal();

//        System.out.println("xx=" + Arrays.toString(kruskalCase.getEdges())); // 没有排序
//        EData[] edges = kruskalCase.getEdges();
//        kruskalCase.sortEdge(edges);
//        System.out.println("xx=" + Arrays.toString(edges)); // 排序后

    }

    // 构造器
    public KruskalCase(char[] vertexs, int[][] matrix) {
        // 初始化定点数和边的个数
        int vlen = vertexs.length;

        // 初始化顶点, 复制、拷贝
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

//        this.vertexs = vertexs; 考虑深拷贝的情况,不影响源数据; 不采用;

        // 初始化边, 使用的复制拷贝
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) { //
                    edgeNum++;
                }
            }
        }
    }

    //
    public void kruskal() {
        int index = 0; // 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; // 用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        // 创建结果数组,保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        // 获取图中所有的边的集合,一共有12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "共" + edges.length);

        // 按照边的权值大小进行排序(从小到大)
        sortEdge(edges);

        // 遍历edges 数组,将边添加到最小生成树中时,判断准备加入的边是否形成了回路,如果没有,就加入rets,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第i条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);
            // 获取到第i条边的第二个顶点(起点)
            int p2 = getPosition(edges[i].end);

            // 获取p1这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1); // m = 4
            // 获取p2这个顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2); // n = 5
            // 是否构成回路【顶点一致】
            if (m != n) { // 没有构成回路
                ends[m] = n; // 设置m在"已有最小生成树"中的终点<E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                rets[index++] = edges[i]; // 有一条边加入到rest数组
            }
        }

        // 统计并打印"最小生成树",输出rets
        for (int i = 0; i <index; i++) {
            System.out.println(rets[i]);
        }

    }

    // 打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println(); // 换行处理
        }
    }
    // 对边进行排序处理,冒泡

    /**
     * 功能: 对边进行排序处理,冒泡排序
     *
     * @param edges
     */
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) { // 交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值, 比如'A','B'
     * @return 返回ch顶点对应的下标, 如果找不到, 返回-1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) { // 找到
                return i;
            }
        }

        // 找不到
        return -1;
    }

    /**
     * 功能: 获取图中的边,放到EData[] 数组中,后面我们需要遍历该数组
     * 是通过matrix 邻接矩阵来获取
     * EData[] 形式[['A','B',12],['B','F',7],......]
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 功能: 获取下标为i的定点给的终点,用于后面判断两个顶点的重点是否相同
     * @param ends : 数组就是记录了各个顶点记录的终点是哪个,ends 数组是在遍历过程中,逐步形成的
     * @param i : 传入的顶点对应的下标
     * @return 返回的就是下标为i的这个顶点对应的终点的下标,一会儿回头还要来理解
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    // 创建一个类EData, 它的对象实例就表示一条边
    class EData {
        char start; // 边的一个起点
        char end; // 边的另外一个点
        int weight; // 边的权值

        // 构造器
        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        // 重写toString方法,便于输出边信息

        @Override
        public String toString() {
            return "EData{" +
                    "start=" + start +
                    ", end=" + end +
                    ", weight=" + weight +
                    '}';
        }

    }

}
