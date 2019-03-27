package com.cxy.favourite.domain.view;


import com.cxy.favourite.domain.User;

/**
 首页
 **/
public class IndexCollectorView {
    //话题之王(发布最多)
    private User mostNewsUser;
    //被关注最多的用户
    private User mostFollowedUser;
    //文章被赞最多的用户
    private User mostLikedUser;
    //文章被评论最多的用户
    private User mostCommentedUser;
    //最受欢迎的用户
    private User mostPopularUser;
    //近一个月最活跃用户
    private User mostActiveUser;

    public User getMostNewsUser() {
        return mostNewsUser;
    }

    public void setMostNewsUser(User mostNewsUser) {
        this.mostNewsUser = mostNewsUser;
    }

    public User getMostFollowedUser() {
        return mostFollowedUser;
    }

    public void setMostFollowedUser(User mostFollowedUser) {
        this.mostFollowedUser = mostFollowedUser;
    }

    public User getMostLikedUser() {
        return mostLikedUser;
    }

    public void setMostLikedUser(User mostLikedUser) {
        this.mostLikedUser = mostLikedUser;
    }

    public User getMostCommentedUser() {
        return mostCommentedUser;
    }

    public void setMostCommentedUser(User mostCommentedUser) {
        this.mostCommentedUser = mostCommentedUser;
    }

    public User getMostPopularUser() {
        return mostPopularUser;
    }

    public void setMostPopularUser(User mostPopularUser) {
        this.mostPopularUser = mostPopularUser;
    }

    public User getMostActiveUser() {
        return mostActiveUser;
    }

    public void setMostActiveUser(User mostActiveUser) {
        this.mostActiveUser = mostActiveUser;
    }
}
