package cn.druglots.mall.core.config.propretise;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 00:46
 * @Description: 阿里云配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunProperties {
    private SmsProperties sms = new SmsProperties();
    private OssProperties oss = new OssProperties();
}
