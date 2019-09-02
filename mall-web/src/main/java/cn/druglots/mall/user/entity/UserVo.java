package cn.druglots.mall.user.entity;

import lombok.Data;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.user.entity
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-02 23:25
 * @Description: 用户查询参数
 */
@Data
public class UserVo {

    private String phoneNum;
    private Long companyId;
    private String realName;
}
