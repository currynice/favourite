package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.Comment;
import com.cxy.favourite.domain.News;
import com.cxy.favourite.domain.enums.EntityType;
import com.cxy.favourite.utils.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SyncScopeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private NewsRepository newsRepository;


    @Test
    public void save() throws Exception {
        for(int i=1;i<11;i++){
            Comment comment = new Comment((long)i,2L, EntityType.News,"评论"+i, DateUtils.getCurrentTime(),0);
            commentRepository.save(comment);
        }

     }


    @Test
    public void updateStatus() throws Exception {
        News news = newsRepository.getByNewsId(2L);
        commentRepository.updateStatus(16L,1);
    }

    @Test
    public void selectByEntity() throws Exception {
        News news = newsRepository.getByNewsId(2L);
        List<Comment> comments =  commentRepository.selectByEntity(news.getId()
       ,EntityType.News);

        for(Comment c:comments){
            System.out.println(c);
        }
    }

    @Test
    public void getCommentCount() throws Exception {
       System.out.println(commentRepository.getCommentCount(2L,EntityType.News));
    }

}