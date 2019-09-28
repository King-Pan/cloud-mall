package cn.druglots.mall.sys.controller;


import cn.druglots.mall.common.result.Result;
import cn.druglots.mall.sys.dto.RegionDto;
import cn.druglots.mall.sys.service.IRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域信息表 前端控制器
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-28
 */
@Api("区域信息控制器")
@RestController
@RequestMapping("/sys/region")
public class RegionController {

    @Autowired
    private IRegionService regionService;


    @ApiOperation(value = "查询区域信息")
    @GetMapping("/")
    public Result getRegionList(RegionDto regionDto){
        return regionService.getRegionList(regionDto);
    }
}
