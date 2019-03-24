package com.cxy.favourite.domain.enums;

/**
 * 关注状态.
 */
public enum FollowStatus {
    FOLLOW("FOLLOW"),
    UNFOLLOW("UNFOLLOW"),
    SPECIAL("SPECIAL");//特别关注(推送每一条更新)
    private String desc;

    FollowStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
