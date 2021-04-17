//package com.course.file.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author 田付成
// * @date 2021/3/20 23:51
// */
//
//@Configuration
//public class SpringMvcConfig implements WebMvcConfigurer {
//
//    @Value("${file.path}")
//    private String FILE_PATH;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/f/**").addResourceLocations("file:" + FILE_PATH);
//    }
//}
