package com.cxy.favourite.domain;

import com.alibaba.fastjson.JSON;
import com.cxy.favourite.domain.enums.MessageStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChatTest {
    @Test
    public void test(){
        Chat chat = new  Chat(MessageStatus.SPEAK,1L,"再见",2);
        String result = JSON.toJSONString(chat);
        System.out.println(result);
    }


}