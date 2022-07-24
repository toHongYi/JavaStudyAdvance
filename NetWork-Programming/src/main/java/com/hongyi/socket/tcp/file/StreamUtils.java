package com.hongyi.socket.tcp.file;

import java.io.*;

/**
 * @author HongYi
 * @version 1.0
 * @date 2022/7/24 17:20
 * @description: 用于演示流的读写方法
 *      手撕一下当前类的一些细节吧;
 */
public class StreamUtils {

    /*** 功能：将输入流转换成 byte[] * @param is * @return * @throws Exception */
    public static byte[] streamToByteArray(InputStream is) throws Exception {

        /** InputStream.read() 和 OutputStream.write()方法组合使用可以完成文件的复制功能。 */
        //ByteArrayOutputStream 对byte类型数据进行写入的类 相当于一个中间缓冲层，将类写入到文件等其他outputStream。它是对字节进行操作，属于内存操作流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //创建输出流对象
        byte[] b = new byte[1024];
        int len;
        while ((len = is.read(b)) != -1) {
            //将读取出来的数据放入缓存区
            // 若不放入缓存区,直接bos.write(len); 效率极差[5:2893]
            bos.write(b, 0, len);
        }
        byte[] array = bos.toByteArray();
        bos.close();
        return array;
    }

    /*** 功能：将 InputStream 转换成 String * @param is * @return * @throws Exception */
    public static String streamToString(InputStream is) throws Exception {
        //用于加快读写效率;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            //当读取到 null 时，就表示结束
            builder.append(line + "\r\n");
        }
        return builder.toString();
    }

}
