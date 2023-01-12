package com.atguigu.kruskal;

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

        // 统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                if (this.matrix[i][j] != INF) { //
                    edgeNum++;
                }
            }
        }
    }

    // 打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%d\n", matrix[i][j]);
            }
            System.out.println(); // 换行处理
        }
    }


}
