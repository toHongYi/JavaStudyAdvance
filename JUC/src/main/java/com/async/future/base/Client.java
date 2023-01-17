package com.async.future.base;

/**
 * @Author lin.lvhua
 * @Date 2023/1/16 16:56
 * @Version 1.0
 * @Description
 *
 * https://blog.csdn.net/qq_35190492/article/details/115609360?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522167392147416800215092646%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=167392147416800215092646&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~top_positive~default-1-115609360-null-null.142^v71^one_line,201^v4^add_ask&utm_term=future&spm=1018.2226.3001.4187
 */
public class Client {
    public static void main(String[] args) {

        Client client = new Client();
        // 这里会立即返回,因为得到的是FutureData而不是RealData
        Data data = client.request("name");
        System.out.println(" 请求完毕 ");

        try {
            // 这里可以用一个sleep代替了对其他业务逻辑的处理
            // 在处理这些业务逻辑的过程中,RealData被创建,从而充分利用了等待时间
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据=" + data.getResult());
    }


    // 这是一个异步方法,返回的Data接口是一个Future
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();

        new Thread() {
            @Override
            public void run() {
                // readDta的构建很慢,所以在单独的线程中进行
                RealData realData = new RealData(queryStr);
                // setRealData()的时候回notifyAll() 等待在这个future上的对象
                future.setRealData(realData);

            }
        }.start();

        return future;
    }

}
