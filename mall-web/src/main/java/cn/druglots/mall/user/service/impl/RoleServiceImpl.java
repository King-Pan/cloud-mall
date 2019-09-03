package cn.druglots.mall.user.service.impl;

import cn.druglots.mall.user.entity.Role;
import cn.druglots.mall.user.mapper.RoleMapper;
import cn.druglots.mall.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUserId(Long userId) {
        return roleMapper.findRoleByUserId(userId);
    }
}
