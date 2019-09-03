package cn.druglots.mall.sys.config.propretise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-04 00:52
 * @Description: redis配置信息
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private String host;

    private int port;

    private String password;
}
