package cn.druglots.mall.sys.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author king-pan
 * @date 2019/9/30 22:48
 */
@Data
@ApiModel("区域VO")
public class RegionVO implements Serializable {
    /**
     * 值
     */
    private String value;
    /**
     * label
     */
    private String label;
    /**
     * 子节点
     */
    private List<RegionVO> children = new ArrayList<>();
}
