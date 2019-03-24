package com.cxy.favourite.domain;



import com.cxy.favourite.domain.enums.IsDelete;
import com.cxy.favourite.domain.enums.ResourceType;

import javax.persistence.*;
import java.io.Serializable;

/**资源数据对象类
 **/
@Entity
public class SysResource extends Entitys implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    /**资源名称**/
    @Column(nullable =false,unique = true)
    private String resourceName;

    /**权限描述**/
    @Column(nullable =true)
    private String description;

    /**权限访问路径**/
    @Column(nullable =true)
    private String url;

    @Column(nullable =true)
    /**权限标识**/
    private String permission;
    /**父编号**/
    @Column(nullable =true)
    private Integer parentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable =false)
    /**资源类型:目录directory 菜单menu 按钮button**/
    private ResourceType type;
    /**显示顺序**/
    @Column(nullable = true)
    private Integer priority;
    /**图标**/
    @Column
    private String icon;

    /**是否可用:1有效2删除**/
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsDelete status;
    /**父name**/
    @Transient//添加该注解的字段不会作为表字段使用
    private String  parentname;

    @Column(nullable = false)
    private Long createdTime;
    @Column(nullable = false)
    private Long lastModifyTime;//最后修改时间

    public SysResource(){
        super();
    }

    public SysResource(String description, String url, String permission, Integer parentId, ResourceType type, Integer priority, String icon, IsDelete status, String parentname, Long createdTime, Long lastModifyTime) {
        this.description = description;
        this.url = url;
        this.permission = permission;
        this.parentId = parentId;
        this.type = type;
        this.priority = priority;
        this.icon = icon;
        this.status = status;
        this.parentname = parentname;
        this.createdTime = createdTime;
        this.lastModifyTime = lastModifyTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public IsDelete getStatus() {
        return status;
    }

    public void setStatus(IsDelete status) {
        this.status = status;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
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