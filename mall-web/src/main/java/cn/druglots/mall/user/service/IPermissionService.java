package cn.druglots.mall.user.service;

import cn.druglots.mall.user.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 通过角色ID获取权限信息
     * @param roleId 角色ID
     * @return
     */
    List<Permission> findListByRoleId(Long roleId);
}
