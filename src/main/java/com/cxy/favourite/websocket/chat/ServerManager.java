package com.cxy.favourite.websocket.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *ServerManager 中维护了一个线程安全的集合servers,
 * 用于因为浏览器发起连接请求而创建的BitCoinServer.

 broadCast 方法遍历这个集合，让每个Server向浏览器发消息。
 */

public class ServerManager {
    private static Logger logger = LoggerFactory.getLogger(ServerManager.class);
    private static Collection<TestServer> servers = Collections.synchronizedCollection(new ArrayList<TestServer>());

    /**
     * 让每个Server向浏览器发消息
     * @param msg
     */
    public static void broadCast(String msg) throws IOException{

            for(TestServer server:servers){
                server.sendMessage(msg);
            }

     }

     //当前连接数
     public static int getTotal() {
        return servers.size();
     }


    public static void add(TestServer server){
        servers.add(server);
        logger.info("新建server,剩余连接数:"+servers.size());
    }

     public static void remove(TestServer server){
         servers.remove(server);
         logger.info("移除server,剩余连接数:"+servers.size());
     }


}
