package cn.druglots.mall.sys.service;

import cn.druglots.mall.common.result.Result;
import cn.druglots.mall.sys.entity.Region;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 区域信息表 服务类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-28
 */
public interface IRegionService extends IService<Region> {
    /**
     * 查询所有区域信息
     * @return Result
     */
    Result getRegionList();
}
