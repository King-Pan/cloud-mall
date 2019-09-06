package cn.druglots.mall.sys.shiro;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.shiro
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-06 23:01
 * @Description: 登录错误次数限制密码匹配器
 */
@Data
@Slf4j
@Service
public class LoginLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private RedisManager redisManager;

    private static final Integer TRY_LOGIN_TIME = 5;


    public LoginLimitHashedCredentialsMatcher() {
        //设置加密次数
        this.setHashIterations(10);
        //设置加密算法
        this.setHashAlgorithmName("sha-1");
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        System.out.println(token);
        System.out.println(token.getCredentials());
        System.out.println(token.getPrincipal());
        System.out.println(token.getClass().getSimpleName());
        System.out.println(info);
        System.out.println(info.getCredentials());
        System.out.println(info.getPrincipals());
        System.out.println(info.getClass().getSimpleName());


        String username = (String) token.getPrincipal();
        //获取缓存对象
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        Cache<String, AtomicInteger> loginTimeCache = redisCacheManager.getCache("passwordRetryCache");
        AtomicInteger time = loginTimeCache.get(username);
        if(time==null){
            time = new AtomicInteger(0);
            loginTimeCache.put(username,time);
        }
        AtomicInteger retryCount = time;
        if (retryCount.incrementAndGet() > TRY_LOGIN_TIME) {
            //if retry count > 5 throw
            log.error("账号密码错误次数过多: {}次，请稍后重试", retryCount);
            throw new ExcessiveAttemptsException("账号密码错误次数过多，请稍后重试");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            //clear retry count
            loginTimeCache.remove(username);
        }
        return matches;
    }
}
