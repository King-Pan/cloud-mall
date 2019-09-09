package cn.druglots.mall.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import cn.druglots.mall.common.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统消息表
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_message")
@ApiModel(value="Message对象", description="系统消息表")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "消息发送人: 系统、用户")
    private String formUser;

    @ApiModelProperty(value = "消息接收人")
    private String toUser;

    @ApiModelProperty(value = "消息标题")
    private String title;

    @ApiModelProperty(value = "消息内容")
    private String content;

    @ApiModelProperty(value = "消息发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "消息读取时间")
    private LocalDateTime readTime;


}
