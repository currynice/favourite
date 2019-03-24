package com.cxy.favourite.domain.dto;

import com.cxy.favourite.domain.SysResource;
import com.cxy.favourite.domain.enums.IsDelete;
import com.cxy.favourite.domain.enums.ResourceType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**资源业务对象类
 **/
public class Resource {
    /**---------------业务对象---------------**/
    private SysResource sysResource;

    public Resource() {
        this.sysResource = new SysResource();
    }

    public Resource(SysResource sysResource) {
        this.sysResource = sysResource;
    }

    /***
     * 封装时还需要将公共实体类的属性封装
     * @return
     */
    @JsonIgnore
    public SysResource getSysResource() {
        return this.sysResource;
    }

    public String getResourceName() {
        return this.getResourceName();
    }

    public void setResourceName(String resourceName) {
       this.setResourceName(resourceName);
    }



    public String getDescription() {
        return this.sysResource.getDescription();
    }

    public void setDescription(String description) {
        this.sysResource.setDescription(description);
    }

    public String getUrl() {
        return this.sysResource.getUrl();
    }

    public void setUrl(String url) {
        this.sysResource.setUrl(url);
    }

    public ResourceType getType() {
        return this.sysResource.getType();
    }

    public void setType(ResourceType type) {
        this.sysResource.setType(type);
    }

    public Integer getPriority() {
        return this.sysResource.getPriority();
    }

    public void setPriority(Integer priority) {
        this.sysResource.setPriority(priority);
    }

    public Integer getParentId() {
        return this.sysResource.getParentId();
    }

    public void setParentId(Integer parentId) {
        this.sysResource.setParentId(parentId);
    }

    public String getPermission() {
        return this.sysResource.getPermission();
    }

    public void setPermission(String permission) {
        this.sysResource.setPermission(permission);
    }

    public String getIcon() {
        return this.sysResource.getIcon();
    }

    public void setIcon(String icon) {
        this.sysResource.setIcon(icon);
    }

    public IsDelete getStatus() {
        return this.sysResource.getStatus();
    }

    public void setStatus(IsDelete status) {
        this.sysResource.setStatus(status);
    }

    public Long getId() {
        return this.sysResource.getId();
    }

    public void setId(Long id) {
        this.sysResource.setId(id);
    }

    public Long getCreatedTime() {
        return this.sysResource.getCreatedTime();
    }

    public void setCreatedTime(Long createdtime) {
        this.sysResource.setCreatedTime(createdtime);
    }

    public Long getLastModifyTime() {
        return this.sysResource.getLastModifyTime();
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.sysResource.setLastModifyTime(lastModifyTime);
    }
}
