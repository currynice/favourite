package com.cxy.favourite.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色资源表
 */
@Entity
public class SysRoleResource extends Entitys implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**角色id**/
    @Column(nullable = false)
    private Long roleId;
    /**资源id**/
    @Column(nullable = false)
    private Long resourceId;

    public SysRoleResource() {

    }

    public SysRoleResource(Long resourceId, Long roleId) {
        this.resourceId = resourceId;
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
