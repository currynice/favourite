package com.cxy.favourite.websocket.chat;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {
    public static final String ENTER = "ENTER";
    public static final String SPEAK = "SPEAK";
    public static final String QUIT = "QUIT";

    private String type;//消息类型

    private String userName; //发送人

    private String msg; //发送消息

    private int onlineCount; //在线用户数

    public static String jsonStr(String type, String userName, String msg, int onlineTotal) {
        return JSON.toJSONString(new Message(type, userName, msg, onlineTotal));
    }
}
