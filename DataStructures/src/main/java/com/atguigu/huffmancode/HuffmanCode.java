package com.atguigu.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/11/29 22:00
 * @description: 功能:根据赫夫曼编码压缩数据的原理,需要创建"i like like like java do you like a java"对应的赫夫曼树
 * 思路:
 * 1.Node{data(存放数据),weight(权值),left和right}
 * 2.得到“i like like like java do you like a java” 对应的byte[]数组
 * 3.编写一个方法,将准备构建赫夫曼树的Node节点放到List中,形式{Node[data=97,weight=5], 【其中97为字符ASCII对应的编码数】
 * Node[date=32,weight = 9]......}  体现 d:1 y:1 u:1 j:2 v:2 o:2 i:4 k:4 e:4 i:5 a:5  :9
 * 4.可以通过List创建对应的赫夫曼树
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("contentBytes = " + contentBytes.length); // 40

        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + "长度=" + huffmanCodesBytes.length);
        // 压缩率:

        // 测试一把byteToVitString方法
//        System.out.println(byteToBitString((byte) 1));
        byte[] sourceBytes = decode(huffmanCode, huffmanCodesBytes);
        System.out.println("原来的字符串=" + new String(sourceBytes)); // "i like like like java do you like a java";

        // 测试压缩文件
        String srcFile = "C:\\Users\\theLin\\IdeaProjects\\JavaStudyAdvance\\DataStructures\\src\\main\\resources\\683A1561.JPG";
        String dstFile = "C:\\Users\\theLin\\Desktop\\dst.zip";
        zipFile(srcFile, dstFile);
        System.out.println("压缩文件ok~~");

        // 测试解压文件
        String zipFile = "C:\\Users\\theLin\\Desktop\\dst.zip";
        String dstFile2 = "C:\\Users\\theLin\\Desktop\\683A156122.JPG";
        unZipFile(zipFile,dstFile2);
        System.out.println("解压文件ok~~");
        /** 分步过程
         List<Node> nodes = getNodes(contentBytes);
         System.out.println("nodes = " + nodes);

         // 测试一把,创建的二叉树
         System.out.println("赫夫曼树");
         Node huffmanTreeRoot = createHuffmanTree(nodes);
         System.out.println("前序遍历");
         huffmanTreeRoot.preOrder();

         // 测试一把,是否生成了对应的赫夫曼编码
         //        getCodes(huffmanTreeRoot, "", stringBuilder);
         Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
         System.out.println("~生成的赫夫曼编码表" + huffmanCodes);

         String strByte = "10101000";
         System.out.println((byte) Integer.parseInt(strByte, 2)); //-88 第一位为符号位;

         //
         byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
         System.out.println("zip = " + Arrays.toString(huffmanCodeBytes)); //17

         // 发送huffmanCodeBytes 数组  */

    }

    // 编写一个方法,完成对压缩文件的解压

    /**
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件压缩到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件的输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和is关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    // 编写方法，将一个文件进行压缩

    /**
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        // 创建输出流
        OutputStream os = null;
        // 创建文件输入流
        FileInputStream is = null;
        // 创建文件的输入流
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b); // 流全部入b
            // 获取到文件对应的赫夫曼编码表
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创建文件的输出流,存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutPutStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes); // 我们先把huffmanBytes

            // 这里我们以对象流的方式写入赫夫曼编码,是为了以后我们恢复源文件时使用
            // 注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanBytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // 完成数据解压
    // 思路
    //1.将huffmanCodeBytes[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //    重写先转成 赫夫曼编码对应的二进制的字符串 "10101000010111..."
    //2. 赫夫曼对应的二进制的字符串""1010100010111..." =》对照赫夫曼编码 => "i like like like java do you like a java"

    // 编写一个方法,完成对压缩数据的解码

    /**
     * @param huffmanCode  赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCode, byte[] huffmanBytes) {

        // 1.先得到huffmanBytes 对应的二进制的字符串, 形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        // 2.将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
//        System.out.println("赫夫曼字节数组对应的二进制字符串 = " + stringBuilder1.toString());
        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换,因为反向查询 a->100  100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
//        System.out.println("map = " + map);

        // 创建要给集合,存放byte
        List<Byte> list = new ArrayList<>();
        // i可以理解成就是一个索引,扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) { // 去掉 i++
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 递增的取出key 1  取出一个'1' '0'
                String key = stringBuilder.substring(i, i + count); //i 不动,让count移动,直到匹配到一个字符
                b = map.get(key);
                if (b == null) { // 说明没有匹配到
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; //i 直接移动到count
        }
        // 当for循环结束后,我们list中就存放了所有的字符 "i like like like java do you like a java"
        // 把list中的数据放入到byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /**
     * 将一个byte转成一个二进制的字符串,  可以参考Java基础 二进制的原码,反码,补码[韩顺平]
     *
     * @param b
     * @param flag 标识是否需要补高位,如果是true,表示需要补高位, 如果false表示不补,如果是最后一个字节,无需补高位
     * @return 是该b 对应的二进制的字符串, (注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b; // 将b转成int
        // 如果是正数,我们还存在补高位
        if (flag) {
            temp |= 256; // 按位与  256  1 0000 0000  |  0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); // 返回的是temp对应的二进制的补码
        if (flag) {
            System.out.println("str = " + str);
        } else {
            return str;
        }
        return str.substring(str.length() - 8);
    }

    // 使用一个方法,将前面的方法封装起来;便于我们的调用;

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 根据nodes 创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 根据赫夫曼树,生成了对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的赫夫曼编码,压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    // 编写一个方法,将一个字符串对应的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码处理后的byte[]

    /**
     * @param bytes       原始的字符串对应的byte[]
     * @param huffmanCode 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 举例: String content = "i like like like java do you like a java"; =》  byte[] contentBytes = content.getBytes();
     * 返回的是字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的byte[] huffmanCodeBytes,既8位对应一个byte,放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000=> 10101000 -1 => 10100111(返码)=> 11011000 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {

        // 1.先利用huffmanCodes 将 传入的 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCode.get(b));
        }
//        System.out.println("stringBuilder = " + stringBuilder); // 133

        // 将"1010100010111111......" 转成byte[]

        // 统计返回的 byte[] huffmanCodeBytes 长度
        // 一句话搞定 int len = (stringBuilder.length() + 7)/8;
        int len;
        if (stringBuilder.length() % 8 == 8) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的byte数组  huffmanCodeBytes
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; //记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为是每8位对应一个byte,所以步长 +8
            String strByte;
            if ((i + 8) > stringBuilder.length()) { // 不够8位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte,放入到
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;
    }


    // 生成赫夫曼树对应的赫夫曼编码
    // 思路
    // 1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    static Map<Byte, String> huffmanCode = new HashMap<Byte, String>();
    //      32->01 97->100 100->1100 等等[形式]
    // 2. 在生成赫夫曼编码表时,需要去拼接路径,定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便,我们重载
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCode;
    }


    /**
     * 功能: 将传入的node结点的所有叶子结点的赫夫曼编码得到,并放入到huffmanCodes集合
     *
     * @param node          传入结点,
     * @param code          路径: 左子结点是0,右子结点是1
     * @param stringBuilder 是用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将传入的code加入到 stringBuilder2
        stringBuilder2.append(code);
        if (code != null) { // 如果node == null不处理
            // 判断当前node是叶子结点还是非叶子结点
            if (node.data == null) { // 非叶子结点
                // 递归处理
                // 向左
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { // 说明是一个叶子结点
                // 就表示找到了某个叶子结点的最后
                huffmanCode.put(node.data, stringBuilder2.toString());
            }

        }
    }

    // 前序遍历的方法
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }


    /**
     * @param bytes 接收字节数值
     * @return 返回的就是List形式 [Node[date=97] ]
     */
    private static List<Node> getNodes(byte[] bytes) {
        //1.创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        //遍历bytes, 统计每个byte出现的次数 ->map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { //Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        // 把每个键值对转成一个Node对象,并加入nodes集合
        // 遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 可以通过List 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树,它的根节点 没有data, 只有权值
            Node parent = new Node(null, (leftNode.weight + rightNode.weight));
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理的二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树加入到nodes
            nodes.add(parent);

        }
        // nodes 最后的结点,就是赫夫曼树的根结点;
        return nodes.get(0);
    }

}

// 创建Node,待数据和权值
class Node implements Comparable<Node> {
    Byte data; // 存放数据本身, 比如 'a'=> 97  '' =>32
    int weight; //权值,表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}