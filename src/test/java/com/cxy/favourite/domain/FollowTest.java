package com.cxy.favourite.domain;

import com.cxy.favourite.domain.enums.FollowStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/3/5/005.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FollowTest {
    @Test
    public void setFollowId() throws Exception {
        Follow follow = new Follow();
        follow.setStatus(FollowStatus.FOLLOW);
        System.out.println(follow.getStatus());
    }

}