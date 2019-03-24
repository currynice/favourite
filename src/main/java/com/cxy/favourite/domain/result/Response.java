package com.cxy.favourite.domain.result;

import com.cxy.favourite.domain.enums.ExceptionEnums;


/**
 * Response
 */
public class Response {
    /** 返回信息码*/
    private Integer rspCode=000000;
    /** 返回信息内容*/
    private String rspMsg="操作成功";

    public Response(){

    }

    /**
     * 默认000000
     * 操作成功
     * @param rspCode
     * @param rspMsg
     */
    public Response(Integer rspCode, String rspMsg) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
    }

    /**
     * 没有返回信息文本
     * @param rspCode
     */
    public Response(Integer rspCode) {
        this.rspCode = rspCode;
        this.rspMsg = "";
    }

    /**
     * 错误Resposne
     * @param exception
     */
    public Response(ExceptionEnums exception){
        this.rspCode = exception.getCode();
        this.rspMsg = exception.getMsg();
    }
    public Integer getRspCode() {
        return rspCode;
    }

    public void setRspCode(Integer rspCode) {
        this.rspCode = rspCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    /**
     * 自定义，就不是用工具类了
     * @return
     */
    @Override
    public String toString() {
        return "Response{" +
                "rspCode=" + rspCode +
                ", rspMsg='" + rspMsg + '\'' +
                '}';
    }
}
