package cn.druglots.mall.sys.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author king-pan
 * @date 2019/9/28 23:33
 */
@Data
@ApiModel("区域信息查询传输对象")
public class RegionDto {

    @ApiModelProperty("省编码标识")
    private Boolean provFlag = Boolean.FALSE;
    @ApiModelProperty("城市编码")
    private Integer cityCode;
}
