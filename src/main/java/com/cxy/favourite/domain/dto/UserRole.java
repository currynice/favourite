package com.cxy.favourite.domain.dto;

import com.cxy.favourite.domain.SysUserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * 业务对象
 */
public class UserRole {
    private SysUserRole sysUserRole;
    public UserRole(){this.sysUserRole = new SysUserRole();}

    public UserRole(SysUserRole sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */

    @JsonIgnore
    public SysUserRole getSysUserRole() {
        return this.sysUserRole;
    }

    public Long getUserId() {
        return this.sysUserRole.getUserId();
    }

    public void setUserId(Long userId) {
        this.sysUserRole.setUserId(userId);
    }

    public Long getRoleId() {
        return this.sysUserRole.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysUserRole.setRoleId(roleId);
    }
    public Long getId() {
        return this.sysUserRole.getId();
    }

    public void setId(Long id) {
        this.sysUserRole.setId(id);
    }

}
