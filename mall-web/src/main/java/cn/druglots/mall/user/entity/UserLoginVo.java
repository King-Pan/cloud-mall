package cn.druglots.mall.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.user.entity
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-13 23:20
 * @Description: 用户登录信息封装
 */
@Data
@ApiModel(value = "用户登录信息封装")
public class UserLoginVo {
    @ApiModelProperty(value = "账号密码登录-用户名")
    private String userName;
    @ApiModelProperty(value = "账号密码登录-密码")
    private String password;
    @ApiModelProperty(value = "手机验证码登录-手机号码")
    private String phoneNum;
    @ApiModelProperty(value = "手机验证码登录-手机验证码")
    private String smsCode;
    @ApiModelProperty(value = "账号密码登录-验证码")
    private String verificationCode;
    @ApiModelProperty(value = "登录类型,user_password_realm:账号密码登录;user_phone_realm:手机验证码登录;jwt_login_real:jwt登录")
    private String type;
}
