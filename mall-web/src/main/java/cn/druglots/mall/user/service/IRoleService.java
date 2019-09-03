package cn.druglots.mall.user.service;

import cn.druglots.mall.user.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
public interface IRoleService extends IService<Role> {
    /**
     * 通过用户ID查找角色信息
     * @param userId 用户ID
     * @return
     */
    List<Role> findByUserId(Long userId);
}
