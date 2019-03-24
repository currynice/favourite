package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.Comment;
import com.cxy.favourite.domain.enums.EntityType;
import com.cxy.favourite.jpa.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Comment jpa
 */

public interface CommentRepository extends BaseRepository<Comment,Long>{
    /**
     * 添加
     * @param comment
     * @return
     */
    @Override
    Comment save(Comment comment);

    /**
     * 删除
     * @param id
     * @param status
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update Comment set status=:status where id=:id ")
   int updateStatus(@Param("id")Long id,@Param("status")Integer status);

    /**
     * 没有分页
     */

    @Query(value = "select c from Comment c where  entityId=:id and entityType=:type")
    List<Comment> selectByEntity(@Param("id")Long entityId,@Param("type")EntityType entityType);

    /**
     * 评论数
     * @param entityId
     * @param entityType
     * @return  status=0 and
     */
    @Query(value = "select count(c) from Comment c where  entityId =:id  and entityType =:type and status =0")
    int getCommentCount(@Param("id")Long entityId,@Param("type")EntityType entityType);


}
