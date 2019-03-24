package com.cxy.favourite.service;

import com.cxy.favourite.domain.News;
import com.cxy.favourite.domain.dto.PageChunk;
import com.cxy.favourite.jpa.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *新闻Service
 */
@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    /**
     * 最新新闻(,个人首页:userId不为null)
     * @param userId
     * @param page
     * @return
     */
    public PageChunk<News> getLatest(Long userId, Integer page,Integer size){

        Pageable pageable = PageRequest.of((page==null?0:page),size, Sort.by(Sort.Direction.DESC,"id"));
        Page<News> result = this.newsRepository.selectByUserIdAndOffset(userId,pageable);
        PageChunk<News> pageChunk  = pageChunk(result);
        return pageChunk;
    }

    //添加新闻
    public void  addNews(News news){
        newsRepository.save(news);
    }

    /**
     * 根据主键查询
     * @param userId
     * @return
     */
     public News getByNewsId(Long userId){
         return this.newsRepository.getByNewsId(userId);
    }

    //评论数+1
    public int updateCommentCount(Long id) {
        return newsRepository.updateCommentCount(id);
    }

    //赞数+1
    public int updateLikeCount(Long id) {
        return newsRepository.updateLikeCount(id);
    }



/*################## service end  ############################*/

    /**
     * 简化Page返回数据
     * @param page
     * @return
     */
    private  PageChunk<News> pageChunk(Page<News> page){
        PageChunk<News> chunk = new PageChunk();
        chunk.setContent(page.getContent());
        chunk.setTotalPages(page.getTotalPages());
        chunk.setTotalElements(page.getTotalElements());
        chunk.setPageNumber(page.getPageable().getPageNumber() + 1);//当前页号
        chunk.setNumberOfElements(page.getNumberOfElements());
        return chunk;
    }

    //上传图片 TODO
}
