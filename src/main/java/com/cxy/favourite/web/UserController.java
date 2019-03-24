package com.cxy.favourite.web;


import com.cxy.favourite.common.Const;
import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.LoginTicket;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.dto.PageChunk;
import com.cxy.favourite.domain.enums.ExceptionEnums;
import com.cxy.favourite.domain.result.Response;
import com.cxy.favourite.domain.result.ResponseData;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.service.LoginTicketService;
import com.cxy.favourite.service.NewsService;
import com.cxy.favourite.utils.DateUtils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping ("/user")
public class UserController extends BaseController{
    @Autowired
    private UserRepository userRepository;
    @Value("${favourite.forgetpassword.url}")
    private String forgotpasswordUrl;
    @Autowired
    private LoginTicketService loginTicketService;
    @Autowired
    private NewsService newsService;

    @Autowired
    private HostHolder hostHolder;

    //TODO 记住登录+上次浏览
    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @LogManage(description = "登录")
    public ResponseData login(User user, HttpServletResponse response,@RequestParam(value = "rememberMe",defaultValue ="0")Integer rememberMe){
        try{

            User emailOrUserNameUser  = this.userRepository.findByUserNameOrEmail(user.getUserName(),user.getEmail());

            if(null==emailOrUserNameUser){
                return new ResponseData(ExceptionEnums.LoginNameNotExists);
            }else if(!getPwd(user.getPassword(),emailOrUserNameUser.getSalt()).equals(emailOrUserNameUser.getPassword())){
                return new ResponseData(ExceptionEnums.PassWordError);

            }
               String ticket = loginTicketService.addLoginTicket(emailOrUserNameUser.getId());
            if(ticket!=null){
                Cookie cookie = new Cookie(Const.LOGIN_TICKET,ticket);
                cookie.setPath("/");
                // TODO 记住我
                if(rememberMe.equals(1)){
                    cookie.setMaxAge(Const.COOKIE_TIMEOUT);
                }
                 response.addCookie(cookie);
            }
                String preUrl = "/";//TODO 记住上次浏览
                return new ResponseData(ExceptionEnums.SUCCESS,preUrl);
        }catch (Exception e){
            logger.error("error in login"+e);
            return new ResponseData(ExceptionEnums.FAILED);

        }


    }

    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    @LogManage(description = "用户注册")
    public Response regist(User user,HttpServletResponse response){
        try{

            User emailUser = this.userRepository.findByEmail(user.getEmail());

            if(null!=emailUser){
                return result(ExceptionEnums.EmailUsed);
            }
            User nameUser = this.userRepository.findByUserName(user.getUserName());
            if(null!=nameUser){
                return result(ExceptionEnums.UserNameUsed);
            }
//            String message = super.validatorRequestParam(user);
//            if(message!=null){
//                return result(ExceptionEnums.ParamError.getCode(),message);
//            }
            //oval

            String salt = Const.getSalt();
            user.setSalt(salt);//取6位随机数进行加密
            user.setPassword(getPwd(user.getPassword(),salt));
            user.setCreatedTime(DateUtils.getCurrentTime());
            user.setLastModifyTime(DateUtils.getCurrentTime());
            user.setMyPicture("/img/favicon.png");//默认头像
            this.userRepository.save(user);
            //TODO
            // 添加默认收藏夹
            //Favorites favorites = favoritesService.saveFavorites(user.getId(), "未读列表");
            // 添加默认属性设置
            // configService.saveConfig(user.getId(),String.valueOf(favorites.getId()));
            //getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            String ticket =loginTicketService.addLoginTicket(user.getId());
            if(ticket!=null){//保存t成功
                Cookie cookie = new Cookie(Const.LOGIN_TICKET,ticket);
                cookie.setPath("/");//全站有效
                //cookie.setMaxAge(Const.COOKIE_TIMEOUT);//TODO remember 才设置，默认浏览器关闭失效
                response.addCookie(cookie);
            }
        }catch (Exception e){
            //TODO  异常合理处理
            logger.error("用户注册异常",e);
          return   super.result(ExceptionEnums.FAILED);
        }
        return super.result();
 }


    @RequestMapping(value = "/logout",method = {RequestMethod.GET, RequestMethod.POST})
    @LogManage(description = "用户退出")
    public String  logout(@CookieValue(name="cxy_user")String ticket){
       int result = loginTicketService.overDue(ticket);
       logger.info("result"+result);
        if(result>0){
            return "redirect:/index";
        }
        return null;
    }

//    /**
//     * TODO  TEST
//     * @param userId
//     * @param page
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
//    public String pageable(Model model, @PathVariable(name = "userId")Long userId, @RequestParam(value = "page",defaultValue ="0")Integer page,
//        @RequestParam(value = "size",defaultValue = "10")Integer size ) throws Exception{
//        PageChunk pageChunk = newsService.getLatest(userId,page,size);
//        model.addAttribute("page",pageChunk);
//        return "";
//    }


//    //上传头像
//    @RequestMapping(value = "/uploadMyHead", method = RequestMethod.POST)
//    @LogManage(description = "上传头像")
//    public ResponseData uploadHeadPortrait(String dataUrl){
//        try {
//            String filePath=staticUrl+fileProfilepicturesUrl;
//            String fileName= UUID.randomUUID().toString()+".png";
//            String savePath = fileProfilepicturesUrl+fileName;
//            String image = dataUrl;
//            String header ="data:image";
//            String[] imageArr=image.split(",");
//            if(imageArr[0].contains(header)){
//                image=imageArr[1];
//                Base64.Decoder decoder = Base64.getDecoder();
//                byte[] decodedBytes = decoder.decode(image);
//                FileUtil.uploadFile(decodedBytes, filePath, fileName);
//                User user = getUser();
//                userRepository.setMyPicture(savePath, user.getId());
//                user.setMyPicture(savePath);
////                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
//                hostHolder.setUser(user);//
//            }
//            logger.info("头像地址：" + savePath);
//            return new ResponseData(ExceptionEnums.SUCCESS, savePath);
//        } catch (Exception e) {
//            logger.error("upload head portrait failed, ", e);
//            return new ResponseData(ExceptionEnums.FAILED);
//        }
//    }


    /**
     * @描述：校验请求参数
     * @param obj
     * @param response
     * @return
     */
    protected boolean validatorRequestParam(Object obj, ResponseData response) {
        boolean flag = false;
        Validator validator = new Validator();
        List<ConstraintViolation> ret = validator.validate(obj);
        if (ret.size() > 0) {
            // 校验参数有误,给出具体原因
            response.setRspCode(ExceptionEnums.ParamError.getCode());
            response.setRspMsg(ret.get(0).getMessageTemplate());
        } else {
            flag = true;
        }
        return flag;
    }
}
