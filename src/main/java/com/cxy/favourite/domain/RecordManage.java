package com.cxy.favourite.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 日志记录
 */
@Entity
public class RecordManage  extends Entitys implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String action;
    @Column(nullable = false)
    private Long createTime;
    public RecordManage(){
        super();
    }
    public RecordManage(Long userId, String action, Long createTime) {
        this.userId = userId;
        this.action = action;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
