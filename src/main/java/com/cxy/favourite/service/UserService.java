package com.cxy.favourite.service;

import ch.qos.logback.core.db.dialect.SybaseSqlAnywhereDialect;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.jpa.LoginTicketRepository;
import com.cxy.favourite.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoginTicketRepository loginTicketRepository;


//    /**
//     * 按照id倒序排序
//     */
//    public void fingByName(){
//        //创建Sort对象
//        Sort sort = new Sort(Sort.Direction.DESC,"id");
//        List<User> result  = userRepository.testvoid("程新宇",sort);
//        for(User user:result){
//            System.out.println(user.toString());
//        }
//    }
//
//    /**
//     * 按照id倒序排序
//     *
//     */
//    public void fingByName2(){
//        //创建Sort对象
//        Sort sort = Sort.by(new Sort.Order(Sort.Direction.DESC,"id"));
//        List<User> result  = userRepository.testvoid("程新宇",sort);
//        for(User user:result){
//            System.out.println(user.toString());
//        }
//    }
//    /**
//     * 按照属性LIST
//     */
//    public void fingByName3(){
//        //创建Sort对象
//        List<String> sortProperties  = new ArrayList<>();
//        sortProperties.add("id");
//        sortProperties.add("userName");
//        Sort sort = new Sort(Sort.Direction.DESC,sortProperties);
//        List<User> result  = userRepository.testvoid("程新宇",sort);
//        for(User user:result){
//            System.out.println(user.toString());
//        }
//    }
//
//    /**
//     * Sort.Order对象的list集合创建对象
//     */
//    public void fingByName4(){
//        //创建Sort对象
//        List<Sort.Order> orders  = new ArrayList<>();
//        orders.add(new Sort.Order(Sort.Direction.DESC,"id"));
//        orders.add(new Sort.Order(Sort.Direction.ASC,"userName"));
//
//
//        List<User> result  = userRepository.testvoid("程新宇",Sort.by(orders));
//        for(User user:result){
//            System.out.println(user.toString());
//        }
//    }


}
