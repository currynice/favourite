package com.cxy.favourite.common.config;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.MultipartConfigElement;

import static org.springframework.util.unit.DataSize.ofBytes;

/**
 * 文件上传配置
 */
@Configuration
public class MultipartConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(ofBytes(52428800L));//50m
        factory.setMaxRequestSize(ofBytes(52428800L));//50m
        return factory.createMultipartConfig();
    }
}
