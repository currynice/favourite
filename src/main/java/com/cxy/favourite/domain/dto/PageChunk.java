package com.cxy.favourite.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Page接口简化数据dto
 */
@Getter
@Setter
public class PageChunk<T> {
    //查询数据 page.getContent
    private List<T> content = new ArrayList<>();
    //搜索结果总数(总行数)
    private long totalElements;
    //总页数
    private int totalPages;
    //当前页号
    private int pageNumber;
    //当前页包含多少项
    private int numberOfElements;

}
