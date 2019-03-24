package com.cxy.favourite.domain.dto;


import com.cxy.favourite.domain.SysRoleResource;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class RoleResource {
    private SysRoleResource sysRoleResource;
    public RoleResource (){
        this.sysRoleResource = new SysRoleResource();
    }
    public RoleResource(SysRoleResource sysRoleResource){
        this.sysRoleResource = sysRoleResource;
    }

    @JsonIgnore

    public SysRoleResource getSysRoleResource() {
        return sysRoleResource;
    }

    public Long getResourceId() {
        return this.sysRoleResource.getResourceId();
    }

    public void setResourceId(Long resourceId) {
        this.sysRoleResource.setResourceId(resourceId);
    }

    public Long getRoleId() {
        return this.sysRoleResource.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysRoleResource.setRoleId(roleId);
    }
    public Long getId() {
        return this.sysRoleResource.getId();
    }

    public void setId(Long id) {
        this.sysRoleResource.setId(id);
    }
}
