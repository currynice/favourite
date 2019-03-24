package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.dto.projection.UserProjection;
import com.cxy.favourite.jpa.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;


/**
 *UserRepository(用email和数据进行关联)
 * 继承了JpaRepository接口（SpringDataJPA提供的简单数据操作接口）、
* JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口，未选择）
 * Serializable（序列化接口）。
 */
public interface UserRepository extends BaseRepository<User,Long> {

    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);

    User findByEmail(String email);



    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update User set outDate=:outDate, validataCode=:validataCode where email=:email")
    int setOutDateAndValidataCode(@Param("outDate") String outDate, @Param("validataCode") String validataCode, @Param("email") String email);
    /**
     * 设置新密码
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update user set password=?1 ,salt=?2 where email=?3",nativeQuery=true)
    int setNewPassword(@Param("password")String password,@Param("salt")String salt,@Param("email")String email);

    /**
     * 设置简介
     * @param introduction
     * @param email
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update user set introduction=?1 where email=?2",nativeQuery=true)
    int setIntroduction(@Param("introduction") String introduction, @Param("email") String email);

    /**
     * 更改用户名
     * @param userName
     * @param email
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update user set user_name=?1 where email=?2",nativeQuery=true)
    int setUserName(@Param("userName") String userName, @Param("email") String email);

    /**
     * 设置头像
     * @param myPicture
     * @param userId
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update user set my_picture=?1 where =id?2",nativeQuery=true)
    int setMyPicture(@Param("myPicture") String myPicture, @Param("id")Long userId);

    /**
     * 设置背景图
     * @param backgroundPicture
     * @param id
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query("update User set backgroundPicture=?1 where id=?2")
    int setBackgroundPicture(@Param("backgroundPicture") String backgroundPicture, @Param("id") Long id);

    /**
     * 自带的按主键查找有些坑,不折腾了
     * @param userId
     * @return
     */
    @Query("select c from User c where c.id =:userId")
    User getByUserId(@Param("userId")Long userId);

    @Query("select c from User c where c.userName=:name ")
    List<User> testvoid(@Param("name") String name2,Sort sort);


    /**这里增加了@QueryHints注解，是给查询添加一些额外的提示
     * 比如当前的name值为HINT_COMMENT是在查询的时候带上一些备注信息
     */
    @QueryHints(value = { @QueryHint(name = HINT_COMMENT, value = "查询提示")})
    @Query("select c from User c where c.userName=:name ")
    Page<User> testhints2(@Param("name") String name2,Pageable pageable);

    @QueryHints(value = { @QueryHint(name = HINT_COMMENT, value = "查询提示")})
    @Query("select c from User c  ")
    Page<User> testhints(Pageable pageable);

    //TODO PROJECTION投影
    @Query("select u.userName as userName ,u.email as email from User u")
    Collection<UserProjection> findAllNameAndEmail();
}
