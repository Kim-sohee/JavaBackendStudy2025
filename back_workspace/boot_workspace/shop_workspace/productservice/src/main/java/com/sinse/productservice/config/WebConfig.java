package com.sinse.productservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${file.upload-dir}")
    private String uploadDir;

    //정적자원의 접근 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.debug("파일 경로는 {}", uploadDir);
        //웹 클라이언트가 어떤 url로 접근하게 될 지 결정
        //http://localhost:7777/resource/
        registry.addResourceHandler("/productapp/resources/**")
                .addResourceLocations("file:"+uploadDir+"/");
    }
}
