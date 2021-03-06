package cn.druglots.mall.core.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.shiro
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-03 23:04
 * @Description: 自定义的登录身份UserToken
 */
@Data
public class UserToken extends UsernamePasswordToken {

    /**
     * 登录方式
     */
    private LoginType loginType;
    /**
     * 微信code
     */
    private String code;

    /**
     * jwt登录的token
     */
    private String token;


    /**
     *
     * @param loginType
     * @param username
     * @param password
     */
    public UserToken(LoginType loginType, final String username, final String password) {
        super(username, password);
        this.loginType = loginType;
    }

    public UserToken(LoginType loginType, String username, String password, String code) {
        super(username, password);
        this.loginType = loginType;
        this.code = code;
    }

}
