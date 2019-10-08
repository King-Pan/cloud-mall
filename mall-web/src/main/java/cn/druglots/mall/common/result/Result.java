package cn.druglots.mall.common.result;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author king-pan
 * @date 2019/10/8 22:28
 */
@ToString
public class Result<T extends Serializable> implements Serializable {

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
    private T data;

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

    public Result setData(T data) {
        this.data = data;
        return this;
    }

}
