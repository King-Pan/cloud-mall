package cn.druglots.mall.core.config.propretise;

import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config.propretise
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-06 23:21
 * @Description: shiro密码相关配置
 */
@Data
public class PasswordProperties {
    /**
     * 加密次数
     */
    private int hashIterations = 10;
    /**
     * 加密算法
     */
    private String algorithmName = "sha-1";
    /**
     * 默认密码
     */
    private String defaultPassword = "888888";

}
