package com.cxy.favourite.domain;



import javax.persistence.*;
import java.io.Serializable;




@Entity
public class News extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = true)
    private String image;//图片
    @Column(nullable = false)
    private Integer likeCount;//赞数
    @Column(nullable = false)
    private Integer commentCount;//评论数
    @Column(nullable = false)
    private Long createdDate;//发布时间
    @Column(nullable = false)
    private Long userId;

    public News(){
        super();
    }
    public News(String title, String content, String image, Integer likeCount, Integer commentCount, Long createdDate, Long userId) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

