package cn.druglots.mall.core.config;


import cn.druglots.mall.core.config.propretise.RedisConfigInfo;
import cn.druglots.mall.core.shiro.LoginLimitHashedCredentialsMatcher;
import cn.druglots.mall.core.shiro.LoginType;
import cn.druglots.mall.core.shiro.SessionManager;
import cn.druglots.mall.core.shiro.realm.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: King-Pan(pwpw1218@gmail.com)
 * @date: 2019-09-03 23:00
 * @Description: shiro配置类
 */
@Configuration
public class ShiroConfig {

    @Autowired
    private RedisConfigInfo redisConfig;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 没有登陆的用户只能访问登陆页面，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", kickoutSessionControlFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 权限控制map.
        // 拦截器路径，坑一，部分路径无法进行拦截，时有时无；因为同学使用的是hashmap, 无序的，应该改为LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap();
        //登录界面，不需要认证
        filterChainDefinitionMap.put("/login", "anon");

        //退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        //未认证界面，不需要认证
        filterChainDefinitionMap.put("/unauthorized", "anon");


        // SWAGGER2过滤【START】
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/security", "anon");
        filterChainDefinitionMap.put("/swagger-resources/configuration/ui", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/sys/region/**", "anon");
        // SWAGGER2过滤【END】
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());

        List<Realm> realms = new ArrayList<>();
        // 统一角色权限控制realm
        realms.add(authorizingRealm());
        // 用户密码登录realm
        realms.add(userPasswordRealm());
        // 用户手机号验证码登录realm
        realms.add(userPhoneRealm());


        // 自定义session管理 使用redis
        // 如果不是前后端分离，则不必设置下面的sessionManager
        securityManager.setSessionManager(sessionManager());

        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());

        //设置realm（推荐放到最后，不然某些情况会不生效）
        securityManager.setRealms(realms);
        return securityManager;
    }

    /**
     * 自定义的Realm管理，主要针对多realm
     */
    @Bean("myModularRealmAuthenticator")
    public MyModularRealmAuthenticator modularRealmAuthenticator() {
        MyModularRealmAuthenticator customizedModularRealmAuthenticator = new MyModularRealmAuthenticator();
        // 设置realm判断条件
        customizedModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());

        return customizedModularRealmAuthenticator;
    }

    @Bean
    public AuthorizingRealm authorizingRealm() {
        AuthorizationRealm authorizationRealm = new AuthorizationRealm();
        authorizationRealm.setName(LoginType.COMMON.getType());

        return authorizationRealm;
    }

    /**
     * 密码登录realm
     *
     * @return
     */
    @Bean
    public UserPasswordRealm userPasswordRealm() {
        UserPasswordRealm userPasswordRealm = new UserPasswordRealm();
        userPasswordRealm.setName(LoginType.USER_PASSWORD.getType());
        // 自定义的密码校验器
        userPasswordRealm.setCredentialsMatcher(credentialsMatcher());
        return userPasswordRealm;
    }

    /**
     * 手机号验证码登录realm
     *
     * @return
     */
    @Bean
    public UserPhoneRealm userPhoneRealm() {
        UserPhoneRealm userPhoneRealm = new UserPhoneRealm();
        userPhoneRealm.setName(LoginType.USER_PHONE.getType());

        return userPhoneRealm;
    }


    @Bean
    public LoginLimitHashedCredentialsMatcher credentialsMatcher() {
        LoginLimitHashedCredentialsMatcher loginLimitHashedCredentialsMatcher = new LoginLimitHashedCredentialsMatcher();
        loginLimitHashedCredentialsMatcher.setRedisManager(redisManager());
        return loginLimitHashedCredentialsMatcher;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //设置前缀
        redisCacheManager.setKeyPrefix("SPRINGBOOT_CACHE:");
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setKeyPrefix("SPRINGBOOT_SESSION:");
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public SessionManager sessionManager() {
        SimpleCookie simpleCookie = new SimpleCookie("Token");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(false);
        SessionManager sessionManager = new SessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(false);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(simpleCookie);
        return sessionManager;
    }


    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisConfig.getHost() + ":" + redisConfig.getPort());
        //设置过期时间
        redisManager.setTimeout(redisConfig.getTimeOut());
        if (StringUtils.isNotBlank(redisConfig.getPassword())) {
            redisManager.setPassword(redisConfig.getPassword());
        }
        return redisManager;
    }

    /**
     * 限制同一账号登录同时登录人数控制
     *
     * @return
     */
    @Bean
    public SessionControlFilter kickoutSessionControlFilter() {
        SessionControlFilter kickoutSessionControlFilter = new SessionControlFilter();
        kickoutSessionControlFilter.setCache(cacheManager());
        kickoutSessionControlFilter.setSessionManager(sessionManager());
        kickoutSessionControlFilter.setKickoutAfter(false);
        kickoutSessionControlFilter.setMaxSession(1);
        kickoutSessionControlFilter.setKickoutUrl("/common/kickout");
        return kickoutSessionControlFilter;
    }


    /***
     * 授权所用配置
     *
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /***
     * 使授权注解起作用不如不想配置可以在pom文件中加入
     * <dependency>
     *<groupId>org.springframework.boot</groupId>
     *<artifactId>spring-boot-starter-aop</artifactId>
     *</dependency>
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * Shiro生命周期处理器
     * 此方法需要用static作为修饰词，否则无法通过@Value()注解的方式获取配置文件的值
     */
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

}
