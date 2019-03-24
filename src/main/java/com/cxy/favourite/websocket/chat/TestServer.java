//package com.cxy.favourite.websocket.chat;
//
//import com.alibaba.fastjson.JSON;
//import org.springframework.stereotype.Component;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//
///**
// *websocket服务器端,
// * @see ServerEndpoint WebSocket服务端 需指定端点的访问路径
// * @see Session   WebSocket会话对象 通过它给客户端发送消息
// */
//@Component
//@ServerEndpoint("/chat")
//public class TestServer {
//    //与某个客户端的连接会话，需要通过它来给客户端发送数据
//    private Session session;
//
//    /**
//     * 当客户端打开连接：1.添加会话对象 2.更新在线人数
//     */
//    @OnOpen
//    public void onOpen(Session session){
//
//        ServerManager.add(this);
//        sendMessageToAll(Message.jsonStr(Message.ENTER,"","",ServerManager.getTotal()));
//
//    }
//
//    @OnClose
//    public void close(){
//        ServerManager.remove(this);
//        sendMessageToAll(Message.jsonStr(Message.QUIT,"","下线了",ServerManager.getTotal()));
//    }
//
//    @OnMessage
//    public void onMessage(String jsonStr, Session session) {
//
//        Message message  = JSON.parseObject(jsonStr,Message.class);
//
//            sendMessageToAll(Message.jsonStr(Message.SPEAK,message.getUserName(),message.getMsg(),ServerManager.getTotal()));
//
//    }
//
//    /**
//     * 发生错误时
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error){
//        System.out.println("发生错误");
//        error.printStackTrace();
//    }
//
//    //发送消息
//    public  void sendMessage(String message) throws IOException{
//       this.session.getBasicRemote().sendText(message);//同步 TODO
//        //this.session.getAsyncRemote().sendText(message);//异步
//    }
//
//    /**
//     * 向所有人发送,让每个Server向浏览器发消息
//     */
//    public void sendMessageToAll(String message){
//        try{
//            ServerManager.broadCast(message);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//}
