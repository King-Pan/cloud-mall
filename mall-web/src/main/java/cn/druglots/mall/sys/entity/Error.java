package cn.druglots.mall.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import cn.druglots.mall.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统错误日志表
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_error")
@ApiModel(value="Error对象", description="系统错误日志表")
public class Error extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String msg;

    private String stackTrace;


}
