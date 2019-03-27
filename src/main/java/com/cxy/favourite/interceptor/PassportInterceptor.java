package com.cxy.favourite.interceptor;

import com.cxy.favourite.common.Const;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.LoginTicket;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.enums.ExceptionEnums;
import com.cxy.favourite.domain.result.ResponseData;
import com.cxy.favourite.jpa.LoginTicketRepository;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.utils.DateUtils;
import com.cxy.favourite.utils.FilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录拦截器
 */
@Component
@Slf4j
public class PassportInterceptor implements HandlerInterceptor {
    private static  final  String KICKOUTURL ="/toLogin"; // 退出后重定向的地址

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LoginTicketRepository loginTicketRepository;
    //处理前
    @Override
    public  boolean preHandle(HttpServletRequest servletrequest, HttpServletResponse servletResponse, Object handler) throws Exception {
        String ticket = null;
        //遍历cookies
        if(servletrequest.getCookies()!=null){
            for(Cookie cookie:servletrequest.getCookies()){
            if((Const.LOGIN_TICKET).equals(cookie.getName())){
                ticket = cookie.getValue();
                break;//退出遍历
            }
            }
        }
        //1.判断ticket是否真实，即存在数据库  //TODO 可能会报错
        // 2.判断是否有效(repository已经判断了)，是否过期(在这里判断)
        if(ticket!=null){
            LoginTicket  existTicket = this.loginTicketRepository.findByTicketAndStatus(ticket,0);
            if(existTicket==null || existTicket.getExpired()<=DateUtils.getCurrentTime()){
                return true;
            }
            //真实的详细信息
            User user = this.userRepository.getByUserId(existTicket.getUserId());
            hostHolder.setUser(user);
        }

        return true;
    }
    /*渲染之前 每个页面添加user*/
    @Override
    public  void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
            if(modelAndView!=null && hostHolder.getUser()!=null){
            modelAndView.addObject("user",hostHolder.getUser());
        }

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
                hostHolder.clear();
    }



}
