package com.cxy.favourite.interceptor;

import com.cxy.favourite.domain.HostHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跳转登录拦截器
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    private static  final  String KICKOUTURL ="/toLogin"; // 退出后重定向的地址



    @Autowired
    private HostHolder hostHolder;

    //处理前
    @Override
    public  boolean preHandle(HttpServletRequest servletrequest, HttpServletResponse servletResponse, Object handler) throws Exception {
        if(hostHolder.getUser()==null){
            servletResponse.sendRedirect("relogin?next="+servletrequest.getRequestURI());
        }

        return true;
    }

    @Override
    public  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }



}
