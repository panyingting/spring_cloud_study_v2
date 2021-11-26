package com.common.server.istudy.config;

import com.common.server.istudy.filter.FilterDemo;
import com.common.server.istudy.intercepter.IntercepterDemo;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new IntercepterDemo());
        super.addInterceptors(registry);
    }

    @Bean
    public FilterRegistrationBean getFilterDemo(){
        FilterRegistrationBean<FilterDemo> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(new FilterDemo());
        filterRegistration.addUrlPatterns("/home/*");
        filterRegistration.setName("filterDemo");
        filterRegistration.setOrder(1);
        return filterRegistration;
    }

}
