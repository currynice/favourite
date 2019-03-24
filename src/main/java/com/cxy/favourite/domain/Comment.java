package com.cxy.favourite.domain;

import com.cxy.favourite.domain.enums.EntityType;
import com.cxy.favourite.domain.enums.IsDelete;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论
*/
@Entity
@Getter
@Setter
public class Comment extends Entitys implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long entityId;

    @Enumerated(value=EnumType.STRING)
    @Column(nullable = false)
    private EntityType entityType;

    @Column(nullable = false)
    private String content; //TODO 支持表情

    @Column(nullable = false)
    private Long createdDate;

    @Column(columnDefinition = "INT default 0")
    private Integer status;//0有效(default)，1删除，2违规或者为负()

    public Comment(){
        super();
    }

    public Comment(Long userId, Long entityId, EntityType entityType, String content, Long createdDate,Integer status) {
        this.userId = userId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.content = content;
        this.createdDate = createdDate;
        this.status = status;
    }
}
