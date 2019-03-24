package com.cxy.favourite.jpa;


import com.cxy.favourite.domain.Follow;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.enums.FollowStatus;
import com.cxy.favourite.jpa.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *FollowJpa
 */
public interface FollowRepository extends BaseRepository<Follow,Long> {
    /**
     * 某用户关注数(FOLLOW)
     * @param userId
     * @param status
     * @return
     */
    Integer countByUserIdAndStatus(Long userId,FollowStatus status);

    /**
     * 某用户粉丝数(FOLLOW)
     * @param followId
     * @param status
     * @return
     */
    Integer countByFollowIdAndStatus(Long followId,FollowStatus status);

    /**
     * 关注数据是否重复  //TODO HomeController isFollow
     * @param userId
     * @param followId
     * @param status
     * @return
     */
    Integer countByUserIdAndFollowIdAndStatus(Long userId,Long followId,FollowStatus status);

    Follow findByUserIdAndFollowId(Long userId,Long followId);

    /**
     *  userId 关注的username
     * @param userId
     * @return
     */
    @Query(value = "select u.userName from Follow f ,User u  where f.userId=?1 and f.followId = u.id and f.status = 'FOLLOW'")
    List<String> findByUserId(@Param("userId")Long userId);

    @Query(value = "select f.followId from Follow f ,User u where u.id = f.userId and f.status = 'FOLLOW' and u.id=?1 ")
    List<Long> findMyFollowIdByUserId(@Param("userId")Long userId);

    @Query(value = "select u.userName , u.introduction  ,u.myPicture ,u.id  from Follow f ,User u where f.userId=?1 and f.followId = u.id and f.status = 'FOLLOW'")
    List<Object[]> findFollowUserByUserId(@Param("userId") Long userId);

    @Query(value = "select u.userName , u.introduction  ,u.myPicture ,u.id  from Follow f ,User u where f.followId=?1 and f.userId = u.id and f.status='FOLLOW'")
    List<Object[]> findFollowedUserByFollowId(@Param("followId") Long followId);


    @Modifying(clearAutomatically = true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update Follow set status=?1, lastModifyTime=?2 where id=?3 ")
    int updateStatusById(FollowStatus status,Long lastModifyTime, Long id);
}
