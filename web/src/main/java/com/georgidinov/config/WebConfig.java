package com.georgidinov.config;

import com.georgidinov.interceptor.RequestInterceptor;
import com.georgidinov.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    //== bean methods ==
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }//end of bean method localeResolver


    //== public methods ==
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(ViewNames.HOME);
    }//end of method addViewControllers

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());

//        LocaleChangeInterceptor localeChangeInterceptor =
//                new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(new LocaleChangeInterceptor());
    }//end of method addInterceptors

}//end of class WebConfig
