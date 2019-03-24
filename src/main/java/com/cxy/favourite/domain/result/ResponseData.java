package com.cxy.favourite.domain.result;

import com.cxy.favourite.domain.enums.ExceptionEnums;

/**
 * Controller返回json封装
 */
public class ResponseData extends Response {
    public Object data;
    //data
    public ResponseData(Object data){
        this.data = data;
    }
    //失败不加data(常用)
    public ResponseData(ExceptionEnums exception){
        super(exception);
    }

    //(信息)
    public ResponseData(Integer rspCode, String rspMsg){
        super(rspCode,rspMsg);
    }

    //信息+data
    public ResponseData(Object data,Integer rspCode, String rspMsg){
        super(rspCode,rspMsg);
        this.data = data;
    }
    //失败+data
    public ResponseData(ExceptionEnums exception,Object data){
        super(exception);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    /**
     * 自定义，就不是用工具类了
     * @return
     */
    @Override
    public String toString() {
        return "ResponseData{" +
                "data=" + data +
                '}'+super.toString();
    }
}
