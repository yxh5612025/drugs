package com.yxh.springboot.config;

import com.yxh.springboot.config.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")//拦截所有请求判断token是否合法来决定是否需要登录
                .excludePathPatterns("/TUser/login","/TUser/register","TUser/password","/**/export","/**/import",
                        "/**/download/**","/**/upload","/alipay/**","/TUser/username/**","swagger-ui/**");
    }
    @Bean
    public JWTInterceptor jwtInterceptor(){
        return new JWTInterceptor();
    }
}
