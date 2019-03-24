package com.cxy.favourite.domain;

import com.alibaba.fastjson.JSON;
import com.cxy.favourite.domain.enums.MessageStatus;

import javax.persistence.*;
import java.io.Serializable;
//改成私信 TODO
/**
 * 聊天(影藏接收人)
 */
@Entity
public class Chat extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private MessageStatus type;//消息类型
    @Column(nullable = false)
    private Long username; //发送人
    @Column(nullable = false)
    private String msg; //发送消息
//    @Column(nullable = true)
//    private Long recieve;//接收人
    @Column(nullable = false)
    private int onlineCount; //在线用户数

    public Chat() {
        super();
    }

    public Chat(MessageStatus type, Long username, String msg, int onlineCount) {
        super();
        this.type = type;
        this.username = username;
        this.msg = msg;
        this.onlineCount = onlineCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageStatus getType() {
        return type;
    }

    public void setType(MessageStatus type) {
        this.type = type;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

//    public Long getRecieve() {
//        return recieve;
//    }
//
//    public void setRecieve(Long recieve) {
//        this.recieve = recieve;
//    }

    public int getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(int onlineCount) {
        this.onlineCount = onlineCount;
    }
//
//    public static String jsonStr(MessageStatus type, Long username, String msg, int onlineCount){
//    return JSON.toJSONString(new Chat(type,username,msg,onlineCount));
//}
}
