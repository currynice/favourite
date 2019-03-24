package com.cxy.favourite.utils;

import com.cxy.favourite.domain.result.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器的一些小工具
 */
public class FilterUtils {
    private static final Logger logger = LoggerFactory
            .getLogger(FilterUtils.class);

    private final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 判断请求是否是Ajax
     * @param request  //
     * @return
     */
//    public static boolean isAjax(ServletRequest request){
//        String header = ((HttpServletRequest) request).getHeader("X-Requested-With");
//        if("XMLHttpRequest".equalsIgnoreCase(header)){
//            logger.debug("当前请求,为Ajax请求");
//           return Boolean.TRUE;
//        }
//        logger.debug("当前请求,非Ajax请求");
//        return Boolean.FALSE;
//    }
    public static boolean isAjax(ServletRequest request){
        String header = ((HttpServletRequest)request).getHeader("X-Requested-With");
        if ("XMLHttpRequest".equalsIgnoreCase(header)) {
                logger.debug("FilterUtils --> 当前请求为AjAX请求");
                return true;

        }
        logger.debug("FilterUtils --> 当前请求不是AjAX请求");
        return false;
    }

    /**
     *
     * @描述：response输出json
     * @param response
     * @param result
     */
//    public static void out(HttpServletResponse response, ResponseData result){
//        PrintWriter out = null;
//        try {
//            response.setCharacterEncoding("UTF-8");//设置编码
//            response.setContentType("application/json");//设置返回类型
//            out = response.getWriter();
//            out.println(objectMapper.writeValueAsString(result));//输出
//            logger.error("FilterUtils.out】响应json信息成功");
//        } catch (Exception e) {
//            logger.error("FilterUtils.out】响应json信息出错", e);
//        }finally{
//            if(null != out){
//                out.flush();
//                out.close();
//            }
//        }
//    }
        public static void out(HttpServletResponse response,ResponseData result){
            response.setContentType("application/json");//返回类型json
            response.setCharacterEncoding("UTF-8");
            //把流定义在try()中，try catch结束自动关闭流
            try(PrintWriter out = response.getWriter()){
                    out.println(objectMapper.writeValueAsString(result));
                logger.error("FilterUtils-->响应json信息成功");
            }catch (Exception e){
                logger.error("FilterUtils-->响应json信息出错",e);

            }
        }
}
