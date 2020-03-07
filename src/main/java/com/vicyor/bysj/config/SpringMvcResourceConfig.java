package com.vicyor.bysj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 作者:姚克威
 * 时间:2020/3/7 21:57
 **/
@Configuration
public class SpringMvcResourceConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/content/**")
                .addResourceLocations("file:///D:/bysj-ui/content/");
        registry.addResourceHandler("/style/**")
                .addResourceLocations("file:///D:/bysj-ui/style/");
    }
}
