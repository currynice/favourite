package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.News;
import com.cxy.favourite.jpa.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * NewsJpa
 */
public interface NewsRepository extends BaseRepository<News,Long> {

    @Override
    News save(News news);

    @Query("select c from News c where c.id =:newsId")
    News getByNewsId(@Param("newsId") Long id);

    /**
     * 更新评论数
     * @param id
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update news set comment_count =comment_count+1 where id=?1",nativeQuery = true)
    int updateCommentCount(@Param("id") Long id);

    /**
     * 增加点赞数
     * @param id
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update news set like_count =like_count+1 where id=?1",nativeQuery = true)
    int updateLikeCount(@Param("id") Long id);


    /**
     * 分页查询selectByUserIdAndOffset
     * order by id desc
     * @param userId
     * @param pageable
     * @return
     */
    @Query(value = "select n from News  n where userId = :userId ")
    Page<News> selectByUserIdAndOffset(@Param("userId") Long userId, Pageable pageable);
}
