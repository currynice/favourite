package com.cxy.favourite.web;


import com.cxy.favourite.common.Const;
import com.cxy.favourite.common.aop.LogManage;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.enums.ExceptionEnums;
import com.cxy.favourite.domain.result.Response;
import com.cxy.favourite.domain.result.ResponseData;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.service.LoginTicketService;
import com.cxy.favourite.service.NewsService;
import com.cxy.favourite.utils.DateUtils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping ("/user")
public class UserController extends BaseController{
    @Autowired
    private UserRepository userRepository;

    @Value("${static.url}")
    private String staticUrl;
    @Value("${background.url}")
    private String backgroundUrl;//用户背景图url
    @Value("${my.picture.url}")
    private String myPictureUrl;//用户头像url

    @Value("${favourite.forgetpassword.url}")
    private String forgotpasswordUrl;
    @Autowired
    private LoginTicketService loginTicketService;
    @Autowired
    private NewsService newsService;

    @Autowired
    private HostHolder hostHolder;

    //TODO 上次浏览
    @ResponseBody
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @LogManage(description = "登录")
    public ResponseData login(User user, HttpServletResponse response,@RequestParam(value = "rememberMe",defaultValue ="0")Integer rememberMe,
                              @RequestParam(value = "next",required =false)String next
            ){
        try{
            //userName可能是昵称或者邮箱
            User emailOrUserNameUser  = this.userRepository.findByUserNameOrEmail(user.getUserName(),user.getUserName());

            if(null==emailOrUserNameUser){
                return new ResponseData(ExceptionEnums.LoginNameNotExists);
            }else if(!getPwd(user.getPassword(),emailOrUserNameUser.getSalt()).equals(emailOrUserNameUser.getPassword())){
                return new ResponseData(ExceptionEnums.PassWordError);

            }
               String ticket = loginTicketService.addLoginTicket(emailOrUserNameUser.getId());

            if(ticket!=null){
                Cookie cookie = new Cookie(Const.LOGIN_TICKET,ticket);
                cookie.setPath("/");
                // 记住我remember==1 才设置，默认浏览器关闭失效
                if(rememberMe.equals(1)){
                    cookie.setMaxAge(Const.COOKIE_TIMEOUT);

                }
                 response.addCookie(cookie);
            }
            String preUrl = "/";//记住上次浏览,默认
                if(StringUtils.isNotBlank(next)){
                    preUrl = next;
                }

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

//    /**
//     * 上传背景
//     * @param dataUrl
//     * @return
//     */
//    @RequestMapping(value = "/uploadBackground", method = RequestMethod.POST)
//    @LogManage(description="个人上传背景图")
//    public ResponseData uploadBackground(String dataUrl){
//        try {
//            String filePath=staticUrl+backgroundUrl;
//            String fileName= UUID.randomUUID().toString()+".png";
//            String savePath = backgroundUrl+fileName;
//            String image = dataUrl;
//            String header ="data:image";
//            String[] imageArr=image.split(",");
//            if(imageArr[0].contains(header)){
//                image=imageArr[1];
//                Base64.Decoder decoder = Base64.getDecoder();
//                byte[] decodedBytes = decoder.decode(image);
//                FileUtil.uploadFile(decodedBytes, filePath, fileName);
//                User user = getUser();
//                userRepository.setBackgroundPicture(savePath, user.getId());
//                user.setBackgroundPicture(savePath);
//                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
//            }
//            logger.info("背景地址：" + savePath);
//            return new ResponseData(ExceptionMsg.SUCCESS, savePath);
//        } catch (Exception e) {
//            logger.error("upload background picture failed, ", e);
//            return new ResponseData(ExceptionMsg.FAILED);
//        }
//    }
//
    /**
     * 忘记密码-发送重置邮件
     * @param email
     * @return
     */
//    @RequestMapping(value = "/sendForgotPasswordEmail", method = RequestMethod.POST)
//    @LogManage(description="发送忘记密码邮件")
//    public Response sendForgotPasswordEmail(String email) {
//        try {
//            User registUser = userRepository.findByEmail(email);
//            if (null == registUser) {
//                return result(ExceptionMsg.EmailNotRegister);
//            }
//            String secretKey = UUID.randomUUID().toString(); // 密钥
//            Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);// 30分钟后过期
//            long date = outDate.getTime() / 1000 * 1000;
//            userRepository.setOutDateAndValidataCode(outDate+"", secretKey, email);
//            String key =email + "$" + date + "$" + secretKey;
//            String digitalSignature = MD5Util.encrypt(key);// 数字签名
////            String basePath = this.getRequest().getScheme() + "://" + this.getRequest().getServerName() + ":" + this.getRequest().getServerPort() + this.getRequest().getContextPath() + "/newPassword";
//            String resetPassHref = forgotpasswordUrl + "?sid="
//                    + digitalSignature +"&email="+email;
//            String emailContent = MessageUtil.getMessage(mailContent, resetPassHref);
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//            helper.setFrom(mailFrom);
//            helper.setTo(email);
//            helper.setSubject(mailSubject);
//            helper.setText(emailContent, true);
//            mailSender.send(mimeMessage);
//        } catch (Exception e) {
//            // TODO: handle exception
//            logger.error("sendForgotPasswordEmail failed, ", e);
//            return result(ExceptionMsg.FAILED);
//        }
//        return result();
//    }

//    /**
//     * 忘记密码-设置新密码
//     * @param newpwd
//     * @param email
//     * @param sid
//     * @return
//     */
//    @RequestMapping(value = "/setNewPassword", method = RequestMethod.POST)
//    @LoggerManage(description="设置新密码")
//    public Response setNewPassword(String newpwd, String email, String sid) {
//        try {
//            User user = userRepository.findByEmail(email);
//            Timestamp outDate = Timestamp.valueOf(user.getOutDate());
//            if(outDate.getTime() <= System.currentTimeMillis()){ //表示已经过期
//                return result(ExceptionMsg.LinkOutdated);
//            }
//            String key = user.getEmail()+"$"+outDate.getTime()/1000*1000+"$"+user.getValidataCode();//数字签名
//            String digitalSignature = MD5Util.encrypt(key);
//            if(!digitalSignature.equals(sid)) {
//                return result(ExceptionMsg.LinkOutdated);
//            }
//            userRepository.setNewPassword(getPwd(newpwd), email);
//        } catch (Exception e) {
//            // TODO: handle exception
//            logger.error("setNewPassword failed, ", e);
//            return result(ExceptionMsg.FAILED);
//        }
//        return result();
//    }
//
//    /**
//     * 修改密码
//     * @param oldPassword
//     * @param newPassword
//     * @return
//     */
//    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//    @LoggerManage(description="修改密码")
//    public Response updatePassword(String oldPassword, String newPassword) {
//        try {
//            User user = getUser();
//            String password = user.getPassWord();
//            String newpwd = getPwd(newPassword);
//            if(password.equals(getPwd(oldPassword))){
//                userRepository.setNewPassword(newpwd, user.getEmail());
//                user.setPassWord(newpwd);
//                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
//            }else{
//                return result(ExceptionMsg.PassWordError);
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            logger.error("updatePassword failed, ", e);
//            return result(ExceptionMsg.FAILED);
//        }
//        return result();
//    }
//
//    /**
//     * 修改个人简介
//     * @param introduction
//     * @return
//     */
//    @RequestMapping(value = "/updateIntroduction", method = RequestMethod.POST)
//    @LoggerManage(description="修改个人简介")
//    public ResponseData updateIntroduction(String introduction) {
//        try {
//            User user = getUser();
//            userRepository.setIntroduction(introduction, user.getEmail());
//            user.setIntroduction(introduction);
//            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
//            return new ResponseData(ExceptionMsg.SUCCESS, introduction);
//        } catch (Exception e) {
//            // TODO: handle exception
//            logger.error("updateIntroduction failed, ", e);
//            return new ResponseData(ExceptionMsg.FAILED);
//        }
//    }
//
//
//    /**
//     * 修改昵称
//     * @param userName
//     * @return
//     */
//    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
//    @LoggerManage(description="修改昵称")
//    public ResponseData updateUserName(String userName) {
//        try {
//            User loginUser = getUser();
//            if(userName.equals(loginUser.getUserName())){
//                return new ResponseData(ExceptionMsg.UserNameSame);
//            }
//            User user = userRepository.findByUserName(userName);
//            if(null != user && user.getUserName().equals(userName)){
//                return new ResponseData(ExceptionMsg.UserNameUsed);
//            }
//            if(userName.length()>12){
//                return new ResponseData(ExceptionMsg.UserNameLengthLimit);
//            }
//            userRepository.setUserName(userName, loginUser.getEmail());
//            loginUser.setUserName(userName);
//            getSession().setAttribute(Const.LOGIN_SESSION_KEY, loginUser);
//            return new ResponseData(ExceptionMsg.SUCCESS, userName);
//        } catch (Exception e) {
//            // TODO: handle exception
//            logger.error("updateUserName failed, ", e);
//            return new ResponseData(ExceptionMsg.FAILED);
//        }
//    }
}
