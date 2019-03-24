package com.cxy.favourite.domain.dto;

import com.cxy.favourite.domain.SysRole;
import com.cxy.favourite.domain.enums.IsDelete;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 角色业务对象类
 */
public class Role {
    private SysRole sysRole;
    public Role(){
        this.sysRole = new SysRole();
    }

    public Role(SysRole sysRole){
        this.sysRole = sysRole;
    }
    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysRole getSysRole() {
        return this.sysRole;
    }
    public String getRole() {
        return this.sysRole.getRoleName();
    }

    public void setRole(String roleName) {
        this.sysRole.setRoleName(roleName);
    }

    public String getDescription() {
        return this.sysRole.getDes();
    }

    public void setDescription(String desc) {
        this.sysRole.setDes(desc);
    }

    public IsDelete getStatus() {
        return this.sysRole.getStatus();
    }

    public void setStatus(IsDelete status) {
        this.sysRole.setStatus(status);
    }

    public Long getId() {
        return this.sysRole.getId();
    }

    public void setId(Long id) {
        this.sysRole.setId(id);
    }

    public Integer getSelected() {
        return this.sysRole.getSelected();
    }

    public void setSelected(Integer selected) {
        this.sysRole.setSelected(selected);
    }

    public Long getCreatedTime() {
        return this.sysRole.getCreatedTime();
    }

    public void setCreatedTime(Long createdtime) {
        this.sysRole.setCreatedTime(createdtime);
    }

    public Long getLastModifyTime() {
        return this.sysRole.getLastModifyTime();
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.sysRole.setLastModifyTime(lastModifyTime);
    }
}
