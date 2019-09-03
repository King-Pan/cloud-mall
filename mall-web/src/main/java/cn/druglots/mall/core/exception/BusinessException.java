package cn.druglots.mall.core.exception;

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

    private String code;
    private String msg;


    public BusinessException(String msg){
        this.code = "500";
        this.msg = msg;
    }

    public BusinessException(Exception e){
        this.code = "500";
        this.msg = e.getMessage();
    }

    public BusinessException(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
