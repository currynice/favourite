package com.cxy.favourite.service;

import com.cxy.favourite.common.Const;
import com.cxy.favourite.domain.LoginTicket;
import com.cxy.favourite.jpa.LoginTicketRepository;
import com.cxy.favourite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * t 票service
 */
@Service
public class LoginTicketService {
    @Autowired
    private LoginTicketRepository loginTicketRepository;
    /**
     * 添加 t票
     * @param userId
     * @return
     */
  public  String addLoginTicket(Long userId){
        Long date = DateUtils.getCurrentTime()+ Const.COOKIE_TIMEOUT;//30天
       String ticket= UUID.randomUUID().toString().replaceAll("-", "");
        this.loginTicketRepository.addTicket(userId,date,0,ticket);
        return ticket;
    }

    /**
     * 过期t票
     * @param ticket
     * @return
     */
   public  int  overDue(String ticket){
      return    this.loginTicketRepository.updateStatus(1,ticket);
    }

    /**
     * 得到 t票
     * @param ticket
     * @return
     */
    public LoginTicket findByTicketAndStatus(String ticket,int status){
      return   this.loginTicketRepository.findByTicketAndStatus(ticket,0);
    }

}
