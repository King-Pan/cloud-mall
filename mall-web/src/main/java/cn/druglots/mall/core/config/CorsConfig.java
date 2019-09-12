package cn.druglots.mall.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config
 * @Author: king-pan
 * @CreateTime: 2019-09-09 16:12
 * @Description: CORS跨域配置,使用spring web模块自带的corsFilter过滤器
 */
//@Configuration
public class CorsConfig implements WebMvcConfigurer{

    /**
     * 添加到容器中管理
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        List<String> allowedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> exposedHeaders = Arrays.asList("x-auth-token", "content-type", "X-Requested-With", "XMLHttpRequest");
        List<String> allowedMethods = Arrays.asList("POST", "GET", "DELETE", "PUT", "OPTIONS");
        List<String> allowedOrigins = Arrays.asList("*");
        // 放行哪些原始域(头部信息)
        corsConfig.setAllowedHeaders(allowedHeaders);
        // 放行哪些原始域(请求方式)
        corsConfig.setAllowedMethods(allowedMethods);
        // 放行哪些原始域
        corsConfig.setAllowedOrigins(allowedOrigins);
        // 暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
        corsConfig.setExposedHeaders(exposedHeaders);
        corsConfig.setMaxAge(36000L);
        corsConfig.setAllowCredentials(true);
        // 配置是否允许发送Cookie，用于 凭证请求， 默认不发送cookie
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(configSource);
    }
}
