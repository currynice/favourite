package com.cxy.favourite.web;

import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.Comment;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.News;
import com.cxy.favourite.domain.enums.EntityType;
import com.cxy.favourite.domain.enums.ExceptionEnums;
import com.cxy.favourite.domain.result.Response;
import com.cxy.favourite.service.CommentService;
import com.cxy.favourite.service.NewsService;
import com.cxy.favourite.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;

/**
 *
 */
@Controller
public class NewsController extends BaseController{
    @Autowired
    NewsService newsService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    CommentService commentService;
    /**
     * 发布资讯
     * @param image
     * @param title
     * @param content
     * @return
     */
    @RequestMapping(path = {"/news/addNews"}, method = {RequestMethod.POST})
    @LogManage(description = "发布资讯")
    @ResponseBody
    public Response addNews(@RequestParam("image") String image,
                            @RequestParam("title") String title,
                            @RequestParam("content") String content) {
        try {
            News news = new News();
            news.setCreatedDate(DateUtils.getCurrentTime());
            news.setTitle(title);
            news.setImage(image);
            news.setCommentCount(0);
            news.setLikeCount(0);
            news.setContent(content);
            news.setUserId(super.getUserId());
            newsService.addNews(news);
            //TODO
            return super.result();

        } catch (Exception e) {
            logger.error("添加资讯失败" + e.getMessage());
            return result(ExceptionEnums.FAILED);
        }
    }

    /**
     * 发表评论
     * @param newsId
     * @param content
     * @return
     */
    @RequestMapping(path = {"/news/addComment"}, method = {RequestMethod.POST})
    @LogManage(description = "发布评论")
    @ResponseBody
    public Response addComment(@RequestParam("newsId") Long newsId,
                               @RequestParam("content") String content) {
        try{
            content = HtmlUtils.htmlEscape(content);
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedDate(DateUtils.getCurrentTime());
            comment.setEntityId(newsId);
            comment.setEntityType(EntityType.News);
            comment.setStatus(0);
            comment.setUserId(getUserId());
            commentService.addComment(comment);
            return result();
        }catch (Exception e){
            logger.error("error in addComment",e);
            return result(ExceptionEnums.FAILED);
        }
    }
}
