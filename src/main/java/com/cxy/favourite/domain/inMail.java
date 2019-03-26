package com.cxy.favourite.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 站内信
 */
@Entity
@Getter
@Setter
public class inMail extends Entitys implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable=false)
    private Long fromId;//发送人id

    @Column(nullable=false)
    private Long toId;//接收人id

    @Column(nullable=false)
    private String content;//内容

    @Column(nullable=false,unique = true)
    private Long conversationId;//对话id(a-b,b-a 只存一份就行了)

    @Column(nullable=false)
    private Date createdDate;//创建时间

    public inMail(){
        super();
    }


    public inMail(Long fromId, Long toId, String content, Long conversationId, Date createdDate) {
        this.fromId = fromId;
        this.toId = toId;
        this.content = content;
        this.conversationId = conversationId;
        this.createdDate = createdDate;
    }
}
