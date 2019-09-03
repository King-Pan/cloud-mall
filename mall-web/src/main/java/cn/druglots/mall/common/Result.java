package cn.druglots.mall.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-04 00:20
 * @Description: 接口返回的数据模型
 */
@Data
public class Result implements Serializable {
    /**
     *返回的代码，200表示成功，其他表示失败
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object data;
    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultStatusCode resultStatusCode, Object data){
        this(resultStatusCode.getCode(), resultStatusCode.getMsg(), data);
    }

    public Result(int code, String msg){
        this(code, msg, null);
    }

    public Result(ResultStatusCode resultStatusCode){
        this(resultStatusCode, null);
    }

}
