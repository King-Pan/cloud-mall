package cn.druglots.mall.entity.good;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.entity.good
 * @Author: king-pan
 * @CreateTime: 2019-08-27 13:40
 * @Description: 商品信息
 */
@TableName("name")
public class Goods {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;
}
