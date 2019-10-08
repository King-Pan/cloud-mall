package cn.druglots.mall.sys.service.impl;

import cn.druglots.mall.common.result.Result;
import cn.druglots.mall.common.result.ResultGenerator;
import cn.druglots.mall.sys.entity.Region;
import cn.druglots.mall.sys.mapper.RegionMapper;
import cn.druglots.mall.sys.service.IRegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 区域信息表 服务实现类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-28
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {


    @Override
    @Cacheable(value = "getRegionList", key = "'allRegion'")
    public Result getRegionList() {
        Result result = ResultGenerator.successResult();
        result.setData(this.baseMapper.getRegionList());
        return result;
    }
}
