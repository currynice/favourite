package com.cxy.favourite.web;

import com.cxy.favourite.common.Const;
import com.cxy.favourite.domain.HostHolder;
import com.cxy.favourite.domain.User;
import com.cxy.favourite.domain.enums.ExceptionEnums;
import com.cxy.favourite.domain.result.Response;
import com.cxy.favourite.domain.result.ResponseData;
import com.cxy.favourite.jpa.UserRepository;
import com.cxy.favourite.utils.Md5Utils;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * baseCotroller
 * 父类protected方便重写
 *
 */
@Controller
public class BaseController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HostHolder hostHolder;

    protected Logger logger =  LoggerFactory.getLogger(this.getClass());

    protected Response result(){
        return new Response();
    }

    protected Response result(ExceptionEnums exception){
        return new Response(exception);
    }

    //数据校验专用
    protected Response result(Integer code,String message){
        return new Response(code,message);
    }

    protected  User getUser(){
        return hostHolder.getUser();
    }

    protected Long getUserId(){
        long id = 0L;
        if((getUser().getId())!=null){
          id= getUser().getId();
        }
        return id;
    }
    protected  String getUserName(){
        String userName ="管理员";
        User currentUser = getUser();
        if(null!=currentUser.getUserName()){
            userName = currentUser.getUserName();
        }
        return userName;
    }
    /*login信息不存在Session里了,直接存在Cookie中*/
//    protected User getUser(){
//        return (User) getSession().getAttribute(Const.LOGIN_SESSION_KEY);
//    }
//
//    protected Long getUserId(){
//        long id = 0L;
//        User currentUser = getUser();
//        if(null!=currentUser.getId()){
//            id =  currentUser.getId();
//         }
//        return  id;
//    }
//
//    protected  String getUserName(){
//        String name = "管理员";
//        User currentUser = getUser();
//        if(name!=currentUser.getUserName()){
//            name =  currentUser.getUserName();
//        }
//        return name;
//    }

    /**
     * 获取reuqwst
     */
    protected HttpServletRequest getRequest(){

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取Session,基于getRequest()
     * @return
     */
    protected HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     * 获取真实ip
     * @return
     */
    protected String getUserIp() {
        String value = getRequest().getHeader("x-forwarded-for");
        if (value == null) {
            return getRequest().getRemoteAddr();
            //return getRequest().getRemoteHost();
        }
        return getRequest().getHeader("x-forwarded-for");
    }


    //TODO  两种方法，等待测试
    protected String getUserIp2() {
        String value = getRequest().getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(value) && !"unknown".equalsIgnoreCase(value)) {
            return value;
        } else {
            return getRequest().getRemoteAddr();
        }
    }

    //加密
    protected String getPwd(String password,String salt){
        try {
            String pwd = Md5Utils.MD5(password+salt);
            return pwd;
        } catch (Exception e) {
            logger.error("密码加密异常：",e);
        }
        return null;
    }
    //TODO cookie验证
//    protected String cookieSign(String value){
//        try{
//            value = value + Const.PASSWORD_KEY;
//            String sign = Des3EncryptionUtil.encode(Const.DES3_KEY,value);
//            return sign;
//        }catch (Exception e){
//            logger.error("cookie签名异常：",e);
//        }
//        return null;
//    }



    /**
     * 校验检查,输出错误信息
     */
    protected  String validatorRequestParam(Object obj){

        Validator validator = new Validator();
        List<ConstraintViolation> vls = validator.validate(obj);
        if(vls.size()>0){
            //出错
            return vls.get(0).getMessageTemplate();
        }else{
            return  null;
        }
    }

}
