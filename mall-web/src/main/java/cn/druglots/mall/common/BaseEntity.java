package cn.druglots.mall.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common
 * @Author: king-pan
 * @CreateTime: 2019-08-29 23:12
 * @Description: 实体类基类
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 主键字段
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    protected Long id;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    protected Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    protected Date updateTime;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    protected String status;
}
