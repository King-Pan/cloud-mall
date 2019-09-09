package cn.druglots.mall.core.config.propretise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-04 00:52
 * @Description: redis配置信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    /**
     * ip
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * redis密码
     */
    private String password;

    /**
     * 过期时间
     */
    private int timeOut = 1800;
}
