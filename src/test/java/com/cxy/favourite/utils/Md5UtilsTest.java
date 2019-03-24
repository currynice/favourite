package com.cxy.favourite.utils;

import com.cxy.favourite.common.Const;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 测试加密
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Md5UtilsTest {
    @Test
    public void MD5() throws Exception {
        System.out.println(Const.getSalt());
    }

}