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
 * 系统数据字典表
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_data_dict")
@ApiModel(value="DataDict对象", description="系统数据字典表")
public class DataDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典值")
    private String dictValue;

    @ApiModelProperty(value = "字典类型")
    private String type;

    private Long parentId;

    @ApiModelProperty(value = "排序值")
    private Integer orderNum;


}
