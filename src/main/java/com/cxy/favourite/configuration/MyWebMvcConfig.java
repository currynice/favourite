package com.cxy.favourite.configuration;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.cxy.favourite.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * TODO 重写addResourceHandlers静态资源路径映射（如果必要的话）
 * 添加登录拦截器（验证token真实性，保存user至threadlocal）.
 */
@Component//初始化
public class MyWebMvcConfig  implements WebMvcConfigurer {
    /**
     * 不用  @Autowired
     * 在Spring添加拦截器之前先创建拦截器对象，
     * 这样就能在Spring映射这个拦截器前，把拦截器中的依赖注入的对象给初始化完成了
     * 避免对象为null
     */
    @Autowired
    PassportInterceptor passportInterceptor;


//        @Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
//		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
//		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/");
//        registry.addResourceHandler("/index/**").addResourceLocations("classpath:/index/");
//        registry.addResourceHandler("/media/**").addResourceLocations("classpath:/media/");
//        registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/vendor/");
//
////		super.addResourceHandlers(registry);
//	}

    @Override
    public  void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/css/**","/img/**","/js/**","/login","/regist","/index","/media/**","/vendor/**");

    }

    /**
     *  转换器 使用阿里 FastJson 作为JSON MessageConverter
     * @param converters  消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //FastJson消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //配置
        FastJsonConfig config = new FastJsonConfig();
        //修改配置 返回的过滤内容
        /**
         * WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
         * WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
         * DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
         * WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
         * WriteMapNullValue：是否输出值为null的字段,默认为false。

         */
        config.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue, // 保留输出null的字段
                SerializerFeature.WriteNullStringAsEmpty, // String null -> ""
                SerializerFeature.WriteNullNumberAsZero  // Number null -> 0

        );
       // config.setDateFormat("");
        // config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);//配置生效
//        converters.add(fastJsonHttpMessageConverter);//添加到转换器list 验证后再加



//        List<MediaType> list = new ArrayList<>();
//        list.add(MediaType.APPLICATION_JSON_UTF8);
//        list.add(MediaType.APPLICATION_XML);
//        list.add(MediaType.TEXT_XML);
//        list.add(MediaType.TEXT_PLAIN);
//        list.add(MediaType.TEXT_EVENT_STREAM);
//        converter.setSupportedMediaTypes(list);
       converter.setDefaultCharset(Charset.forName("UTF-8"));
        for(int i=0;i<converters.size();i++){
            if(converters.get(i) instanceof MappingJackson2HttpMessageConverter){
                converters.set(i,converter);//及时生效
            }
        }
    }


    /**
     * View - Controller 映射配置
     */
//    @Override
//    public  void addViewControllers(ViewControllerRegistry registry) {
//
//
//        registry.addViewController("/").setViewName("/index");
//        registry.addViewController("/index").setViewName("/index");
//        registry.addViewController("/about").setViewName("/about");
//        registry.addViewController("/error/403").setViewName("/error/403");
//        registry.addViewController("/error/500").setViewName("/error/500");
//        super.addViewControllers(registry);
//    }

    /**
     * 重写 addCorsMappings方法:
     addMapping：配置可以被跨域的路径，可以任意配置，可以具体到直接请求路径。
     allowedMethods：允许所有的请求方法访问该跨域资源服务器，如：POST、GET、PUT、DELETE等。
     allowedOrigins：允许所有的请求域名访问我们的跨域资源，可以固定单条或者多条内容，
        如："http://www.baidu.com"，只有百度可以访问我们的跨域资源。
     allowedHeaders：允许所有的请求header访问，可以自定义设置任意请求头信息，如："X-TOKEN"
     */
//    @Override
//    public void  addCorsMappings(CorsRegistry registry) {
//
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*");
//        super.addCorsMappings(registry);
//    }








}
