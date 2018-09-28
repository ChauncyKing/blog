package com.chauncy.blog.configuration;

import com.chauncy.blog.component.HttpResolverInterceptor;
import com.chauncy.blog.component.SessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器，过滤器和监听器
 */
@Configuration
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpResolverInterceptor()).addPathPatterns("/**");
        log.info("[注册拦截器:HttpResolverInterceptor]");
    }

    /**
     * 注册 Session 监听器
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean registerSessionListener() {
        ServletListenerRegistrationBean slrb = new ServletListenerRegistrationBean();
        slrb.setListener(new SessionListener());
        log.info("[注册监听器:HttpResolverInterceptor]");
        return slrb;
    }

//    /**
//     * 注册过滤器
//     */
//    @Bean
//    public FilterRegistrationBean registerFilter() {
//        FilterRegistrationBean frb = new FilterRegistrationBean();
//        frb.setFilter(new FilterDemo());
//        frb.addUrlPatterns("/*");
//        return frb;
//    }

}
