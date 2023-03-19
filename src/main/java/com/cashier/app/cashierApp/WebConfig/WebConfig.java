package com.cashier.app.cashierApp.WebConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cashier.app.cashierApp.Interceptor.MainServiceInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Autowired
    private MainServiceInterceptor mainServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mainServiceInterceptor).addPathPatterns("/api/**");
    }
}
