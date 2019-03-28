package com.cxy.favourite.web;

import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.view.NewsSummary;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.service.LookAroundService;
import com.cxy.favourite.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    NewsService newsService;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private LookAroundService lookAroundService;


    @RequestMapping(value ={"/index","/"},method = RequestMethod.GET)
    @LogManage(description = "首页")
    public String index(Model model){
//       User user = super.getUser();  根据HostHolder取值
//        if(user!=null){
//            model.addAttribute("user",user);
//        }
        return "index";
    }

    @RequestMapping(value="/login",method=RequestMethod.GET)
    @LogManage(description="进入登陆页面")
    public String login() {

        return "login";
    }

    @RequestMapping(value="/regist",method=RequestMethod.GET)
    @LogManage(description="进入注册页面")
    public String regist() {

        return "register";
    }

    @RequestMapping(value="/feedback",method = RequestMethod.GET)
    @LogManage(description = "意见反馈")
    public String feedback(Model model){
//        User currentUser =super.getUser();
//        if(currentUser!=null){
//            model.addAttribute("user",currentUser);
//        }
        return "/favourite/feedback";
    }

    /**
     * 聊天界面
     */
//    @RequestMapping(value = "/chat",method = RequestMethod.GET)
//    @LogManage(description = "聊天")
//    public ModelAndView chat(String userName, HttpServletRequest request)
//            throws UnknownHostException{
//
//        ModelAndView mav = new ModelAndView("favourite/chat");
////        mav.addObject("userName", userName);//hostholder取值
//        mav.addObject("webSocketUrl", "ws://"+ InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort()+request.getContextPath()+"/chat");
//       return mav;
//    }
    @RequestMapping(value = "/chat",method = RequestMethod.GET)
    @LogManage(description = "聊天")
    public String chat() {


        return "/favourite/chat";
    }

    /**
     * 随便看看 标准模式显示(新闻+用户组合输出) //TODO 3.27
     *
     */
    @RequestMapping(value = "/lookAround")
    @LogManage(description = "随便看看")
    public String  lookAroundStandard(Model model, @RequestParam(value = "page",defaultValue = "0")Integer page,
                                     @RequestParam(value = "size",defaultValue = "10")Integer size){
        List<NewsSummary> summaries = this.lookAroundService.getLatestNewsAndUser(page,size);

        model.addAttribute("type", "lookAround");
        model.addAttribute("newsSummaries",summaries);
        model.addAttribute("size",summaries.size());
        return "lookAround/standard";

    }

    @RequestMapping(value = "relogin",method = RequestMethod.GET)
    @LogManage(description = "跳转登录")
    public String relogin(Model model,@RequestParam(value = "next",required = false)String next){
        model.addAttribute("next",next);//login.html 埋进去
         return "login";
    }
}
