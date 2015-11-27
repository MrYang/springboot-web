package com.zz.springbootweb.config;

import com.zz.springbootweb.interceptor.StopWatchInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new StopWatchInterceptor());
    }
}
