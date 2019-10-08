package cn.druglots.mall.sys.mapper;

import cn.druglots.mall.sys.dto.RegionVO;
import cn.druglots.mall.sys.entity.Region;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 区域信息表 Mapper 接口
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-28
 */
public interface RegionMapper extends BaseMapper<Region> {
    /**
     * 查询所有区域信息
     *
     * @return List<RegionVO>
     */
    List<RegionVO> getRegionList();
}
