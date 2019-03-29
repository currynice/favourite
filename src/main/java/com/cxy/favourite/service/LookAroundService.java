package com.cxy.favourite.service;

import com.cxy.favourite.domain.News;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.ViewObject;
import com.cxy.favourite.domain.view.NewsSummary;
import com.cxy.favourite.domain.view.NewsView;
import com.cxy.favourite.jpa.NewsRepository;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;


/**
 * 随便看看sevice
 */
@Service
public class LookAroundService {
    private static Logger logger = LoggerFactory.getLogger(LookAroundService.class);
    @Autowired
    private NewsRepository newsRepository;

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private UserRepository userRepository ;
    /**
     * 随便看看中 滚动条 随机显示5条资讯  TODO  待定
     * @return
     */
//    public List<ViewObject> scrollFiveNews(){
//        EntityManager em = emf.createEntityManager();
//        String SQL = "";
//    }

    /**
     * 按照用户关注量倒序查询5个用户 、、TODO  待定
     * @param userId
     * @return
     */
//    public List<UserIsFollow> queryFiveUser(Long userId){
//
//    }


    /**
     * 随便看看 查询资讯列表中（同发现中列表，另加类别过滤）TODO  待定
     * @return
     */
//    public List<NewsSummary> queryCollectExplore(Pageable pageable,Long userId,String category){
//
//    }


    /**
     * 最新新闻随便看看,(news + user +.....) 不分类 TODO
     * ATTENTION: spring-data-jpa 返回List<Object[]>,所以要做处理
     * 按 new.id 降序排列 ()
     * @param page
     * @return
     */
    public List<NewsSummary> getLatestNewsAndUser(Integer page, Integer size){

        Pageable pageable = PageRequest.of((page==null?0:page),size, Sort.by(Sort.Direction.DESC,"id"));
        Page<NewsView> result = this.newsRepository.selectByOffset(pageable);
        List<NewsSummary> summaries = new ArrayList<>();

        for(NewsView view:result){
            NewsSummary summary = new NewsSummary(view);
            //TODO 判断当前用户是否赞了,默认没有  TODO
            summary.setPraise(false);
            summary.setCreatedDate(DateUtils.getTimeFormatText(view.getCreatedDate()));
            summaries.add(summary);
        }

        return summaries;
    }
}
