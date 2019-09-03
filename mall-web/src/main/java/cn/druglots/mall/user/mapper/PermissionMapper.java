package cn.druglots.mall.user.mapper;

import cn.druglots.mall.user.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限表 Mapper 接口
 *
 * @author King-Pan
 * @since 2019-09-02
 */
public interface PermissionMapper extends BaseMapper<Permission> {
  /**
   * 获取权限信息
   *
   * @param roleId 角色id
   * @return
   */
  List<Permission> findListByRoleId(@Param("roleId") Long roleId);
}
