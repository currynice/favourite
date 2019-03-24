package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.News;
import lombok.AllArgsConstructor;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NewsRepositoryTest {
    @Autowired
    private NewsRepository newsRepository;
//    @Test
//    public void save() throws Exception {
//        for(int i=2;i<10;i++){
//            News news  = new News("new"+i,"www","",0,0,999L,(long)i);
//            newsRepository.save(news);
//        }
//
//    }
    @Test
    public void getByNewsId() throws Exception {
        System.out.println(newsRepository.getByNewsId(1L).toString());
    }

    @Test
    public void updateCommentCount() throws Exception {
        newsRepository.updateCommentCount(1L);
    }

    @Test
    public void updateLikeCount() throws Exception {
        newsRepository.updateLikeCount(1L);
    }

    @Test
    public void selectByUserIdAndOffset()throws  Exception{
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


        Pageable pageable = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"id"));
        Page<News> page = newsRepository.selectByUserIdAndOffset(2L,pageable);
        System.out.println("总行数"+page.getTotalElements());
        System.out.println("总页数"+page.getTotalPages());
        for(News news : page.getContent()){
            System.out.println(news);
        }
    }

}