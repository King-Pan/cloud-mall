package cn.druglots.mall.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    protected Long id;
    /**
     * 创建时间
     */
    protected Date creatTime;
    /**
     * 更新时间
     */
    protected Date updateTime;
    /**
     * 状态
     */
    protected String status;
}
