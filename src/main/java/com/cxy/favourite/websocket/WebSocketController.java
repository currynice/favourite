//package com.cxy.favourite.websocket;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Random;
//
//@Controller
//@RequestMapping("test")
//public class WebSocketController {
//
//    /**
//     * ws
//     */
//    @RequestMapping("ws")
//    public String  test(){
//        int bitPrice = 100000;
//        while(true){
//
//            //每隔1-3秒就产生一个新价格
//            int duration = 1000+new Random().nextInt(2000);
//            try {
//                Thread.sleep(duration);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            //新价格围绕100000左右50%波动
//            float random = 1+(float) (Math.random()-0.5);
//            int newPrice = (int) (bitPrice*random);
//
//            //查看的人越多，价格越高
//            int total = ServerManager.getTotal();
//            newPrice = newPrice*total;
//
//            String messageFormat = "{\"price\":\"%d\",\"total\":%d}";
//            String message = String.format(messageFormat, newPrice,total);
//            //广播出去
////            ServerManager.broadCast(message);
//        }
//    }
//
//}
