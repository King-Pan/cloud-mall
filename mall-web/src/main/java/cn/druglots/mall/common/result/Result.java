package cn.druglots.mall.common.result;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.result
 * @Author: king-pan
 * @CreateTime: 2019-08-28 10:10
 * @Description: 返回结果
 */
public class Result {

    /**
     * 状态响应码
     */
    private int code;

    /**
     * 响应结果 成功/失败
     */
    private boolean success;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private Object data;

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.msg();
        return this;
    }


    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
