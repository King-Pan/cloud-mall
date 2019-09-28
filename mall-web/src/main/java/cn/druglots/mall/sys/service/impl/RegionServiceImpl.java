package cn.druglots.mall.sys.service.impl;

import cn.druglots.mall.common.result.Result;
import cn.druglots.mall.common.result.ResultGenerator;
import cn.druglots.mall.sys.dto.RegionDto;
import cn.druglots.mall.sys.entity.Region;
import cn.druglots.mall.sys.mapper.RegionMapper;
import cn.druglots.mall.sys.service.IRegionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public Result getRegionList(RegionDto regionDto) {
        Result result = ResultGenerator.successResult();
        if(regionDto.getProvFlag()){
            result.setData(baseMapper.selectList(Wrappers.<Region>lambdaQuery().eq(Region::getLevel,1)));
        }else{
            result.setData(baseMapper.selectList(Wrappers.<Region>lambdaQuery().eq(Region::getParentId,regionDto.getCityCode())));
        }
        return result;
    }
}
