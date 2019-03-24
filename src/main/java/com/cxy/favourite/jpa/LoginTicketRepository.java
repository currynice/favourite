package com.cxy.favourite.jpa;

import com.cxy.favourite.domain.LoginTicket;
import com.cxy.favourite.jpa.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * TicketJpa
 */
public interface LoginTicketRepository extends BaseRepository<LoginTicket,Long> {
    /**
     * 添加
     * @param userId
     * @param expired
     * @param status
     * @param ticket
     * @return
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "insert into login_ticket( user_id, expired, status, ticket) values (?1,?2,?3,?4)",nativeQuery = true)
    int addTicket(Long userId,Long expired,int status,String ticket);
    /**
     * 根据ticket得到LoginTicket
     */
    LoginTicket findByTicketAndStatus(String ticket,int status);

    /**
     * 退出时更新
     * @param ticket
     * @param status
     */
    @Modifying(clearAutomatically=true)
    @Transactional(rollbackFor = RuntimeException.class)
    @Query(value = "update LoginTicket set status=?1 where ticket=?2")
    int updateStatus( @Param("status") int status,@Param("ticket") String ticket);


}
