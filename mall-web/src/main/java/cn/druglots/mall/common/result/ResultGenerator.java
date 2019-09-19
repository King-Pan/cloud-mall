package cn.druglots.mall.common.result;

import cn.druglots.mall.common.utils.RequestContextHolderUtil;
import org.springframework.http.HttpStatus;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.result
 * @Author: king-pan
 * @CreateTime: 2019-08-28 10:12
 * @Description: 结果生成器
 */
public class ResultGenerator {

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 生成默认的结果
     *
     * @return Result结果对象
     */
    public static Result successResult() {
        return new Result()
                .setCode(HttpStatus.OK.value())
                .setSuccess(true)
                .setMessage(HttpStatus.OK.getReasonPhrase());
    }

    /**
     * 生成默认的结果
     *
     * @return Result结果对象
     */
    public static Result successResult(String msg) {
        return successResult().setMessage(msg);
    }

    public static Result successResult(Object data) {
        return successResult().setData(data);
    }


    public static Result failResult() {
        return new Result()
                .setCode(HttpStatus.OK.value())
                .setSuccess(false);
    }

    public static Result failResult(String message) {
        return new Result()
                .setCode(HttpStatus.OK.value())
                .setSuccess(false)
                .setMessage(message);
    }

    /**
     * 自定义状态必须通过ResultCode调用
     * @param code
     * @return
     */
    public static Result failResult(ResultCode code) {
        return new Result()
                .setCode(code)
                .setSuccess(false);
    }

    public static Result failResult(HttpStatus httpStatus) {
        RequestContextHolderUtil.getResponse().setStatus(httpStatus.value());
        return new Result()
                .setCode(httpStatus.value())
                .setSuccess(false)
                .setMessage(httpStatus.getReasonPhrase());
    }


    public static Result failResult(HttpStatus httpStatus, String msg) {
        RequestContextHolderUtil.getResponse().setStatus(httpStatus.value());
        return new Result()
                .setCode(httpStatus.value())
                .setSuccess(false)
                .setMessage(msg);
    }
}
