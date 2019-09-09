package cn.druglots.mall.sys.service.impl;

import cn.druglots.mall.sys.entity.Error;
import cn.druglots.mall.sys.mapper.ErrorMapper;
import cn.druglots.mall.sys.service.IErrorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统错误日志表 服务实现类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Service
public class ErrorServiceImpl extends ServiceImpl<ErrorMapper, Error> implements IErrorService {

}
