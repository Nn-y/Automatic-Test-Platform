package com.testplatform.platformbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

//    // 当前跨域请求最大有效时长。我设置的是一天的时间
//    private static final long MAX_AGE = 24 * 60 * 60;
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("http://localhost:8080"); //  设置访问源地址
//        corsConfiguration.addAllowedHeader("*"); //  设置访问源请求头
//        corsConfiguration.addAllowedMethod("*"); //  设置访问源请求方法
//        corsConfiguration.setMaxAge(MAX_AGE);
//        source.registerCorsConfiguration("/**", corsConfiguration); //  对接口配置跨域设置
//        return new CorsFilter(source);
//    }
@Bean
public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            // spring boot跨域设置
            registry.addMapping("/**")
                    //设置允许跨域请求的域名
                    .allowedOriginPatterns("*")
                    //是否允许证书 不再默认开启（在跨域情况下使用cookie时开启，需要axios开启该对应的选项）
                    .allowCredentials(true)
                    //设置允许的方法
                    .allowedMethods("*")
                    //跨域允许时间
                    .maxAge(3600);
        }
    };
}
}
