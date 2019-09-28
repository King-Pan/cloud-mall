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
 * 区域信息表
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_region")
@ApiModel(value="Region对象", description="区域信息表")
public class Region extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域简码-座机前缀")
    private String adcode;

    @ApiModelProperty(value = "城市编码")
    private Integer citycode;

    @ApiModelProperty(value = "中心坐标")
    private String center;

    @ApiModelProperty(value = "级别：0，1，2，3，4")
    private Integer level;

    @ApiModelProperty(value = "详细地址")
    private String mergerName;

    @ApiModelProperty(value = "区域简称")
    private String name;

    @ApiModelProperty(value = "上级ID")
    private Integer parentId;

    @ApiModelProperty(value = "拼音")
    private String pinyin;

    @ApiModelProperty(value = "短名称")
    private String shortName;

    @ApiModelProperty(value = "邮政编码")
    private String zipCode;


}
