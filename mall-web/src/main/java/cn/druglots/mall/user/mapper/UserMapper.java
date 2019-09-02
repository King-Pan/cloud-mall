package cn.druglots.mall.user.mapper;

import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.entity.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义分页查询
     * @param page
     * @param userVo
     * @return
     */
    IPage<User> selectPageVo(Page page, @Param("info") UserVo userVo);
}
