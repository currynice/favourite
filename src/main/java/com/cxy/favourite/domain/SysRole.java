package com.cxy.favourite.domain;

import com.cxy.favourite.domain.enums.IsDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 角色表  TODO 时间处理
 */
@Entity
public class SysRole extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String roleName;//角色(唯一)
    @Column(nullable = false)
    private String des;//描述

    /**是否可用:1有效2删除**/
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsDelete status;
    @Column(nullable = false)
    private Integer selected;/**是否选中1.是 2.否**/
    @Column(nullable = false)
    private Long createdTime;
    @Column(nullable = false)
    private Long lastModifyTime;//最后修改时间


    public SysRole() {
        super();
    }

    public SysRole(String roleName, String des, IsDelete status, Integer selected, Long createdTime, Long lastModifyTime) {
        this.roleName = roleName;
        this.des = des;
        this.status = status;
        this.selected = selected;
        this.createdTime = createdTime;
        this.lastModifyTime = lastModifyTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public IsDelete getStatus() {
        return status;
    }

    public void setStatus(IsDelete status) {
        this.status = status;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
