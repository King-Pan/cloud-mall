package cn.druglots.mall.user.entity;

import cn.druglots.mall.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Permission对象", description="权限表")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String permissionName;

    @ApiModelProperty(value = "权限连接")
    private String url;

    private String expression;

    private String type;

    @ApiModelProperty(value = "图标")
    private String icon;

    private String orderNum;

    private Long parentId;


}
