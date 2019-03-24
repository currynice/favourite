package com.cxy.favourite.domain;

import com.cxy.favourite.domain.Entitys;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class UrlLibrary extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "INT default 0")
    private int count;

    @Column(nullable=true,columnDefinition ="varchar(300)" )
    private String logoUrl;//标志url

    @Column(nullable=false,columnDefinition = "varchar(600)")
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
