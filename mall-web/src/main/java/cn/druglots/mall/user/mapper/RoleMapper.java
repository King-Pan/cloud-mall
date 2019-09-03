package cn.druglots.mall.user.mapper;

import cn.druglots.mall.user.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户ID查找角色信息
     * @param userId 用户ID
     * @return
     */
    List<Role> findRoleByUserId(@Param("userId") Long userId);
}
