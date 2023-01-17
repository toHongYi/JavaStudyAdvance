package com.async.future.base;

/**
 * @Author lin.lvhua
 * @Date 2023/1/16 16:47
 * @Version 1.0
 * @Description
 */
public class RealData implements Data{

    protected final String result;

    public RealData(String result) {
        StringBuffer sb = new StringBuffer(result);
        // 假设这里很慢,，构造RealData不是一个容易的事
        this.result = sb.toString();
    }


    @Override
    public String getResult() {
        return result;
    }

}
