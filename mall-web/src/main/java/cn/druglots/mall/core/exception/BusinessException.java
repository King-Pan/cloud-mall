package cn.druglots.mall.core.exception;

import cn.druglots.mall.common.result.ResultCode;
import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.exception
 * @Author: king-pan
 * @CreateTime: 2019-08-27 23:50
 * @Description: 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException {

    private ResultCode resultCode = ResultCode.INTERNAL_SERVER_ERROR;

    private String msg;

    public BusinessException(String msg) {
        this.msg = msg;
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public BusinessException(ResultCode resultCode, String msg) {
        this.resultCode = resultCode;
        this.msg = msg;
    }

    public BusinessException(Exception e) {
        this.msg = e.getMessage();
    }
}
