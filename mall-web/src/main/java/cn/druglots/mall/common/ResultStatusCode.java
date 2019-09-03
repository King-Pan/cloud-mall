package cn.druglots.mall.common;

import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-04 00:16
 * @Description: 返回状态码
 */
public enum ResultStatusCode {

    SIGN_ERROR(120, "签名错误"),
    TIME_OUT(130, "访问超时"),
    OK(200, "OK"),
    IS_LOGIN(300,"您已经在其他地方登录，请重新登录"),
    BAD_REQUEST(400, "参数解析失败"),
    INVALID_TOKEN(401, "无效的授权码"),
    INVALID_CLIENTID(402, "无效的密钥"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    SYSTEM_ERR(500, "服务器运行异常"),
    NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
    LOGINED_IN(10001, "该用户已登录"),
    NOT_EXIST_BUSINESS(10002, "该商家不存在"),
    SHIRO_ERROR(10003, "登录异常"),
    UNAUTHO_ERROR(10004, "您没有该权限"),
    BIND_PHONE(10010, "请绑定手机号"),
    UPLOAD_ERROR(20000, "上传文件异常"),
    INVALID_CAPTCHA(30005, "无效的验证码"),
    USER_FROZEN(40000, "该用户已被冻结");

    /**
     * 返回状态码
     */
    private int code;
    /**
     * 状态码信息
     */
    private String msg;

    private ResultStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}