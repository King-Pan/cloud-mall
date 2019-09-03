package cn.druglots.mall.sys.shiro;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.shiro
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-03 23:02
 * @Description: 登录方式枚举类
 */
public enum LoginType {

    /**
     * 通用
     */
    COMMON("common_realm"),
    /**
     * 用户密码登录
     */
    USER_PASSWORD("user_password_realm"),
    /**
     * 手机验证码登录
     */
    USER_PHONE("user_phone_realm"),
    /**
     * 第三方登录(微信登录)
     */
    WECHAT_LOGIN("wechat_login_realm");

    private String type;

    private LoginType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
