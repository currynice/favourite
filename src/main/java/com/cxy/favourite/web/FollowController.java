package com.cxy.favourite.web;

//TODO 特别关注

import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.Follow;
import com.cxy.favourite.domain.enums.ExceptionEnums;
import com.cxy.favourite.domain.enums.FollowStatus;
import com.cxy.favourite.domain.result.Response;
import com.cxy.favourite.jpa.FollowRepository;
import com.cxy.favourite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *关注取关
 */
@RestController
@RequestMapping("/follow")
public class FollowController extends BaseController{

    @Autowired
    private FollowRepository followRepository;

    /**
     *
     * @param currentStatus
     * @param userId  关注人id
     * @return
     */
    @RequestMapping(value = "changeFollowStatus")
    @LogManage(description = "关注状态变更")
    public Response change(String currentStatus,Long userId){


        try{
            FollowStatus followStatus =   FollowStatus.FOLLOW;//TODO 未来新增特别关注
            if(!"follow".equals(currentStatus)){
                followStatus = FollowStatus.UNFOLLOW;
            }
            Follow datafollow = this.followRepository.findByUserIdAndFollowId(getUserId(),userId);
            if(datafollow!=null){
                this.followRepository.updateStatusById(followStatus, DateUtils.getCurrentTime(), datafollow.getId());
            }else{
                //事务处理TODO
                Follow follow = new Follow();
                follow.setFollowId(userId);
                follow.setUserId(getUserId());
                follow.setStatus(followStatus);
                follow.setCreateTime(DateUtils.getCurrentTime());
                follow.setLastModifyTime(DateUtils.getCurrentTime());
                this.followRepository.save(follow);
            }
        }catch(Exception e){
            logger.error("关注、取关失败");
            return result(ExceptionEnums.FAILED);
        }
        return result();//状态成功变更
    }



}
