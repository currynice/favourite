package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * UserRepository单元测试.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void find(){
       User user = userRepository.getByUserId(22L);
        System.out.println(ToStringBuilder.reflectionToString(user));
    }
    @Test
    public void test(){
        //Assert.assertEquals(2,userRepository.findAll().size());//数量是否是2,通过
        userRepository.setNewPassword("54321","123eee","66666666@qq.com");
        System.out.println(userRepository.findByEmail("66666666@qq.com").getPassword());
    }
    @Test
    public void testIntroduction(){
        //Assert.assertEquals(2,userRepository.findAll().size());//数量是否是2,通过
        userRepository.setBackgroundPicture("程新宇",3L);

    }

//    @Test
//    public void testhints(){
//
//        // Pageable是接口，PageRequest是接口实现
//        //PageRequest的对象构造函数有多个，page是页数，初始值是0，
//        // size是查询结果的条数，后两个参数参考Sort对象的构造方法
//        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"id"));
//     Page<User> page =  userRepository.testhints("程新宇",pageable);
//        //查询结果总行数
//        System.out.println(page.getTotalElements());
//        //按照当前分页大小，总页数
//        System.out.println(page.getTotalPages());
//     for(User user:page.getContent()){
//         System.out.println(user);
//     }
//
//    }

    @Test
    public void testPageable(){

        // Pageable是接口，PageRequest是接口实现
        //PageRequest的对象构造函数有多个，page是页数，初始值是0，
        // size是查询结果的条数，后两个参数参考Sort对象的构造方法
        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"id"));
        Page<User> page =  userRepository.testhints2("程新宇",pageable);
        //查询结果总行数
        System.out.println(page.getTotalElements());
        //按照当前分页大小，总页数
        System.out.println(page.getTotalPages());
        for(User user:page.getContent()){
            System.out.println(user);
        }
    }

//    @Test
//    public void add(){
//        userRepository.save(new User("cxy1","123456"));
//    }


}