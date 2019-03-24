package com.cxy.favourite.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户角色表
 */
@Entity
public class SysUserRole extends Entitys implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**用户id**/
    @Column(nullable = false)
    private Long userId;
    /**角色id**/
    @Column(nullable = false)
    private Long roleId;

    public SysUserRole() {

    }

    public SysUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
