package cn.druglots.mall.core.config.propretise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author king-pan
 * @date 2019/10/8 22:28
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigInfo {

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
