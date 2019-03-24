package com.cxy.favourite.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2019/3/7/007.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConstTest {

    @Test
    public void test(){

        System.out.println(Const.BASE_PATH);
        System.out.println(Const.FORGETURL);
}




}