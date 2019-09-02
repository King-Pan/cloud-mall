package cn.druglots.mall.user.service.impl;

import cn.druglots.mall.user.entity.Permission;
import cn.druglots.mall.user.mapper.PermissionMapper;
import cn.druglots.mall.user.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
