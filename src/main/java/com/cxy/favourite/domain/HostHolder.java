package com.cxy.favourite.domain;

import org.springframework.stereotype.Component;

/**
 * 获取当前用户(同于request中存取，通过依赖注入或者注册为Bean,service 和 web都可以使用）
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

   public void setUser(User user){
        users.set(user);
   }

    public void clear() {
        users.remove();
    }
}