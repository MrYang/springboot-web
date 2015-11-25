package com.zz.springbootweb.config;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SitemeshConfiguration {

    @Bean
    public FilterRegistrationBean siteMeshFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        SiteMeshFilter sitemeshFilter = new SiteMeshFilter();
        registrationBean.setFilter(sitemeshFilter);
        registrationBean.setOrder(Integer.MAX_VALUE);
        return registrationBean;
    }
}
