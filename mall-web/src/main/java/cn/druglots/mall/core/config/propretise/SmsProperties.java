package cn.druglots.mall.core.config.propretise;

import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 00:48
 * @Description: sms短信配置
 */
@Data
public class SmsProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
}
