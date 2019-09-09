package cn.druglots.mall.sys.service.impl;

import cn.druglots.mall.sys.entity.Message;
import cn.druglots.mall.sys.mapper.MessageMapper;
import cn.druglots.mall.sys.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统消息表 服务实现类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-09
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
