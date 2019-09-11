package cn.druglots.mall.core.rst;

import cn.druglots.mall.common.utils.RequestContextHolderUtil;
import org.springframework.http.HttpStatus;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.rst
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
    public static Result genSuccessResult() {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setSuccess(true)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result genSuccessResult(Object data) {
        return new Result()
                .setCode(ResultCode.SUCCESS)
                .setSuccess(true)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCode.FAIL)
                .setSuccess(false)
                .setMessage(message);
    }

    public static Result genFailResult(ResultCode code, String message) {
        return new Result()
                .setCode(code)
                .setSuccess(false)
                .setMessage(message);
    }

    public static Result genFailResult(HttpStatus httpStatus) {
        RequestContextHolderUtil.getResponse().setStatus(httpStatus.value());
        return new Result()
                .setCode(httpStatus.value())
                .setSuccess(false)
                .setMessage(httpStatus.getReasonPhrase());
    }


    public static Result genFailResult(HttpStatus httpStatus,String msg) {
        RequestContextHolderUtil.getResponse().setStatus(httpStatus.value());
        return new Result()
                .setCode(httpStatus.value())
                .setSuccess(false)
                .setMessage(msg);
    }
}
