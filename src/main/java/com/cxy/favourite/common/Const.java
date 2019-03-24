package com.cxy.favourite.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 定义一些常量（一些session key,rediskey的名称）
 */
@Component
public class Const {

    public static String BASE_PATH;

    @Autowired(required = true )
    public void setBasePath(@Value("${favourite.base.path}") String basePath){
        Const.BASE_PATH = basePath;
    }

   public static  String FORGETURL;//忘记密码路径

    @Autowired
    public void setFORGETURL(@Value("${favourite.forgetpassword.url}")String url){
            Const.FORGETURL = url;
    }

   // public static String LOGIN_SESSION_KEY = "cxy_user";
    public static String LOGIN_TICKET = "cxy_user";//改了UserController的logout记得改
    /**
     * 得到6位随机数作为加密盐
     * @return
     */
    public static String getSalt(){
    return  UUID.randomUUID().toString().substring(0, 6);//不去掉-
    }



//
//    public static String DES3_KEY = "9964DYByKL967c3308imytCB";
//
//    public static String default_logo="img/logo.jpg";
//
//    public static String userAgent="Mozilla";
//
//    public static String default_Profile=BASE_PATH+"/img/logo.jpg";
//
//    public static String LAST_REFERER = "LAST_REFERER";
//
    public static int COOKIE_TIMEOUT= 14*24*60*60;// 14天
}
