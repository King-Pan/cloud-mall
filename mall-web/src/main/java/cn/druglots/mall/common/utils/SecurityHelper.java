package cn.druglots.mall.common.utils;

import cn.druglots.mall.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.utils
 * @Author: king-pan
 * @CreateTime: 2019-10-14 09:24
 * @Description: 权限工具帮助类
 */
public class SecurityHelper {

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    public static User getCurrentUser() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    public static Long getCurrentUserId() {
        User user = getCurrentUser();
        return user.getId();
    }
}
