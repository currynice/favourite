package com.cxy.favourite.service;

import com.cxy.favourite.domain.Comment;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.enums.EntityType;
import com.cxy.favourite.jpa.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private HostHolder hostHolder;

    /**
     * 保存评论
     * @param comment
     * @return
     */
    public void  addComment(Comment comment){

       commentRepository.save(comment);
     }

     public int updateStatus(Long id,Integer status){
        return commentRepository.updateStatus(id,status);
     }

     public List<Comment> selectByEntity(Long entityId,EntityType entityType){
        return commentRepository.selectByEntity(entityId,entityType);
     }

    public int getCommentCount(Long entityId, EntityType entityType){
        return commentRepository.getCommentCount(entityId,entityType);
    }
}
