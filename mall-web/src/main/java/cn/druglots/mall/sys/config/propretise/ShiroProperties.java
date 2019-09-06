package cn.druglots.mall.sys.config.propretise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-06 23:21
 * @Description: shiro相关配置文件
 */
@Data
@Component
@ConfigurationProperties(prefix = "shiro")
public class ShiroProperties {
    private int expire = 30;
    PasswordProperties password = new PasswordProperties();
}
