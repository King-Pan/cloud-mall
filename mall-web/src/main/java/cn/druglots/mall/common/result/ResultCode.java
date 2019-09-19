package cn.druglots.mall.common.result;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.result
 * @Author: king-pan
 * @CreateTime: 2019-08-28 10:09
 * @Description: 自定义返回状态码,http部分状态码请参考HttpStatus
 */
public enum ResultCode {
    /**
     * 用户名密码错误
     */
    USER_PASSWORD_ERROR(100001,"用户名密码错误"),

    ACCOUNT_LOCKED(100001,"账号被锁定"),

    AUTHORIZED_FAIL(100002,"授权失败"),
    /**
     * 参数解析失败
     */
    FAIL(400,"参数解析失败"),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401,"UNAUTHORIZED"),
    /**
     * 接口不存在
     */
    NOT_FOUND(404,"NOT_FOUND"),
    /**
     * 不支持的方法
     */
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500,"服务器运行异常");

    /**
     * 返回状态码
     */
    private final int code;
    /**
     * 状态码信息
     */
    private final String msg;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg(){
        return msg;
    }
}
