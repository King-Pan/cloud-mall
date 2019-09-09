package cn.druglots.mall.sys.entity;

import cn.druglots.mall.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 系统操作日志表
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
@ApiModel(value="SysLog对象", description="系统操作日志表")
public class SysLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "访问时间")
    private Date accessTime;

    @ApiModelProperty(value = "响应时长")
    private Long time;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "用户操作：例如：登录、注册、xxx查询等")
    private String operation;

    @ApiModelProperty(value = "方法描述")
    private String description;

    @ApiModelProperty(value = "访问IP")
    private String ip;

    private String params;

    @ApiModelProperty(value = "方法名称")
    private String methodName;

    @ApiModelProperty(value = "方法类型")
    private String methodType;

    @ApiModelProperty(value = "返回状态码")
    private String code;

    @ApiModelProperty(value = "返回结果")
    private String result;

    @ApiModelProperty(value = "错误信息")
    private String errorMsg;


}
