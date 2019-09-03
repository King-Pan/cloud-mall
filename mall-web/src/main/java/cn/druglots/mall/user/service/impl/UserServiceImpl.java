package cn.druglots.mall.user.service.impl;

import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.entity.UserVo;
import cn.druglots.mall.user.mapper.UserMapper;
import cn.druglots.mall.user.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户表 服务实现类
 *
 * @author King-Pan
 * @since 2019-09-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> getPageList(UserVo userVo, Page<User> page) {
        return userMapper.selectPageVo(page,userVo);
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUserName,userName));
    }

    @Override
    public User findByPhoneNum(String phoneNum) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getPhoneNum,phoneNum));
    }
}
