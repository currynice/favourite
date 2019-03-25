//package com.cxy.favourite.interceptor;
//
//import com.example.gamemanagement.modules.quesbank.entity.ResponseData;
//import com.example.gamemanagement.modules.sys.entity.Buser;
//import com.example.gamemanagement.modules.sys.service.BuserService;
//import com.example.gamemanagement.utils.FilterUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * 用户信息动态修改
// */
//@Service
//public class InfoModifyInterceptor implements HandlerInterceptor {
//    private static final Logger logger = LoggerFactory.getLogger(InfoModifyInterceptor.class);
//
//    @Autowired
//    private BuserService buserService;
//    @Override
//    public boolean preHandle(HttpServletRequest request,
//                             HttpServletResponse response, Object obj) throws Exception {
//
//        Buser user = (Buser) request.getSession().getAttribute("buser");
//        if (user != null && StringUtils.isNotEmpty(user.getLoginid())
//                && null != user.getVersion()) {
//            // 2. 获取数据库中的用户数据
//            Buser dataUser = this.buserService.getByLoginid(user.getLoginid());
//            // 3. 对比session中用户的version和数据库中的是否一致
//            if (dataUser != null
//                    && null != dataUser.getVersion()
//                    && String.valueOf(user.getVersion()).equals(
//                    String.valueOf(dataUser.getVersion()))) {
//                // 3.1 一样，放行
//                logger.info("放行");
//                return true;
//            }else{
//                logger.info("拦截退出");
//                request.getSession().removeAttribute("buser");
//                isAjaxResponse(request,response);
//                logger.info("成功退出");
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//
//
//    /**
//     * 拦截Ajax请求
//     */
//    private boolean isAjaxResponse(HttpServletRequest request,
//                                   HttpServletResponse response) throws IOException {
//        // ajax请求
//        /**
//         * 判断是否已经踢出
//         * 1.如果是Ajax 访问，那么给予json返回值提示。
//         * 2.如果是普通请求，直接跳转到登录页
//         */
//        //判断是不是Ajax请求
//        ResponseData result = new ResponseData(false,"");
//        if (FilterUtils.isAjax(request) ) { //todo ajax处理提示有点问题
//            logger.debug(getClass().getName()+ "，当前用户的信息或权限已变更，重新登录后生效！");
//            result.setSuccess(false);
//            result.setResMsg("您的信息或权限已变更，重新登录后生效");
//            FilterUtils.out(response, result);
//        }else{
//            // 重定向
//            //response.sendRedirect("/sys/login");
//            //跳出iframe层登入login页面
//            PrintWriter out = response.getWriter();
//            out.println("<html>");
//            out.println("<script>");
//            out.println("window.open ('" + request.getContextPath() + "/sys/login','_top')");
//            out.println("</script>");
//            out.println("</html>");
//
//        }
//        return false;
//    }
//}
