package com.berru.app.elearningmanagementsystem.config;

import com.berru.app.elearningmanagementsystem.config.interceptor.HttpRequestLoggerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private HttpRequestLoggerInterceptor httpRequestLoggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(httpRequestLoggerInterceptor);
    }
}