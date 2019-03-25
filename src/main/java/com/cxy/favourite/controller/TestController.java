package com.cxy.favourite.controller;

import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.News;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.annotation.CurrentUser;
import com.cxy.favourite.domain.dto.PageChunk;
import com.cxy.favourite.domain.dto.projection.UserProjection;
import com.cxy.favourite.jpa.NewsRepository;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.jpa.specification.SpecificationFactory;
import com.cxy.favourite.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 测试 JpaResposity
 */
@RestController
@RequestMapping("test")
public class TestController {
@Autowired
private UserRepository userRepository;
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsService newsService;
    /**
     * 查询用户列表方法
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @LogManage(description = "查询列表方法")
    public List<User> list() throws Exception{
        return userRepository.findAll();
    }

    /**
     *增加，更新用户方法,添加后返回List，方便测试
     * @Param entity
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public List<User> save(User entity) throws Exception{
        userRepository.save(new User("张三", "123456", "1234r","66666666@qq.com"));
        userRepository.save(new User("小秘书", "123456", "12ggg","12345@qq.com"));
         //userJPA.save方法可以执行添加也可以执行更新，
        // 如果需要执行持久化的实体存在主键值则更新数据，如果为0则添加数据。
        return userRepository.findAll();
    }
    /**
     * 删除用户方法,删除后返回List，方便测试
     *
     * @return
     */
    @RequestMapping(value = "/{id}/delete",method = RequestMethod.GET)
    public List<User> delete(@PathVariable("id")Long id) throws Exception
    {
        User zhang3 = this.userRepository.getOne(id);
       userRepository.delete(zhang3);
        return userRepository.findAll();
    }

//    @RequestMapping(value = "/{id}/find",method = RequestMethod.GET)
//    public User findUserById(@PathVariable("id")Long id) throws Exception
//    {
//
//        return userRepository.findUser(id);
//    }
//
//    @RequestMapping(value = "/find2",method = RequestMethod.GET)
//    public List<User> findByNameMatch() throws Exception
//    {
//
//        return userRepository.findByNameMatch("张三");
//    }
  

    /**
     * 查询新闻(分页)  Testing,简化
     * @RequestParam 最好是Integer
     *
     * @return
     */
    @RequestMapping(value = "/pageable/{userId}", method = RequestMethod.GET)
    @LogManage(description = "测试查询分页列表方法")
    public PageChunk pageable(@PathVariable(name = "userId")Long userId,@RequestParam(required = false,defaultValue = "0")Integer size,@RequestParam(required = false,defaultValue = "10")Integer page) throws Exception{
         return   newsService.getLatest(userId,size,page);
    }





    /**
     * 查询用户(分页)
     * //TODO wrong way
     * @return
     */
    @RequestMapping(value = "/projections", method = RequestMethod.GET)
    @LogManage(description = "测试投影使用")
    public Map<String,Object> projection() throws Exception{
        Map<String,Object> map = new HashMap<>();
        Collection<UserProjection> projections = userRepository.findAllNameAndEmail();
        System.out.println(projections);
        System.out.println(projections.size());
        for(UserProjection u:projections){
            map.put("userName:",u.getUserName());
            map.put("email:",u.getEmail());
            map.put("information:",u.getInformation());
        }
        return map;
    }

    @RequestMapping(value = "/specification", method = RequestMethod.GET)
    @LogManage(description = "specification测试")
    public void  specification() throws Exception{
//        containsLike
        Specification<User> specification1 = SpecificationFactory.containsLike("userName","cxy");
//        List<User> thisuser = userRepository.findAll(specification1);
//        System.out.println(thisuser);
//        System.out.println(thisuser.size());


        Specification<User> specification2 = SpecificationFactory.isBetween("createdTime",1552461355078L,1552461387898L);
//        List<User> betweenUser =  userRepository.findAll(specification2);
//        System.out.println(betweenUser);
//        System.out.println(betweenUser.size());
        //以上都是单个条件查询的

        //复合查询也很简单,追加在后面就行 where().or()也能用用
        Specification<User> specification3 = Specification.where(specification1).and(specification2);
        List<User> result =userRepository.findAll(specification3);
        System.out.println(result);
        System.out.println(result.size());


    }

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

        /*测试Springmvc参数绑定*/
      //@PathVariable
        @RequestMapping("/testUrlPathParam/{param1}/{param2}")
        public void testUrlPathParam(HttpServletRequest request, @PathVariable("param1") String param1,
                                     @PathVariable("param1")String param2) {
            System.out.println("通过PathVariable获取的参数param1=" + param1);
            System.out.println("通过PathVariable获取的参数param2=" + param2);
        }

    @RequestMapping("/testHeaderParam")
    public void testHeaderParam(HttpServletRequest request, @RequestHeader("param") String param) {
        System.out.println("通过PathVariable获取的参数param=" + param);
    }


    /**
     * 在
     */
    @RequestMapping("/testCookieParam")
    public void testCookieParam(
                                HttpServletResponse response,
                                @CookieValue(value = "cxyId",defaultValue = "cxy") String cxyId,
                                @RequestParam(value = "key", defaultValue = "key") String key,
                                @RequestParam(value = "value", defaultValue = "value") String value
                                ) {

        response.addCookie(new Cookie(key,value));
        System.out.println("通过CookieValue获取的参数" + cxyId);//通过CookieValue获取的参数cxy
    }


    @RequestMapping("/testBodyParam")
    public void testBodyParam(@RequestBody String value) {
        System.out.println("通过RequestBody获取的参数" + value);//通过RequestBody获取的参数cxxas
    }

    /**
     * 自定义参数绑定 annotation:CurrentUser
     */
    @RequestMapping(value = "userInfo",method = RequestMethod.GET)
    public Map<String,String> userInfo(@CurrentUser(required=true)User user){
        Map<String,String> map = new HashMap<>();
        map.put("name",user.getUserName());
        map.put("email",user.getEmail());
        return map;
    }

}
