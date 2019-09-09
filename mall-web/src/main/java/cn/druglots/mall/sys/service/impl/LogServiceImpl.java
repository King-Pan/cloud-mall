package cn.druglots.mall.sys.service.impl;

import cn.druglots.mall.sys.entity.Log;
import cn.druglots.mall.sys.mapper.LogMapper;
import cn.druglots.mall.sys.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
