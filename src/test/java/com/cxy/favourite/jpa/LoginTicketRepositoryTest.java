package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.LoginTicket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginTicketRepositoryTest {
    @Autowired
    private LoginTicketRepository loginTicketRepository;
    @Test
    public void addTicket() throws Exception {
        loginTicketRepository.addTicket(1L,2222222L,0,"xxxxx11");
    }

    @Test
    public void find() throws Exception {
      LoginTicket ticket =  loginTicketRepository.findByTicketAndStatus("aa7da2e7f88b4299973128abafb2a7fb",1);
      System.out.println(ticket.toString());
    }



    @Test
    public void update() throws Exception {
       int result = loginTicketRepository.updateStatus(1,"aa7da2e7f88b4299973128abafb2a7fb");

        LoginTicket ticket =  loginTicketRepository.findByTicketAndStatus("aa7da2e7f88b4299973128abafb2a7fb",0);
        System.out.println(ticket.getStatus());
    }
}