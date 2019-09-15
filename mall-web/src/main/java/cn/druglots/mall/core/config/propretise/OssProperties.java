package cn.druglots.mall.core.config.propretise;

import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 00:47
 * @Description: oss相关配置信息
 */
@Data
public class OssProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String endPoint;
}
