package cn.druglots.mall.user.service;

import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.entity.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
public interface IUserService extends IService<User> {
    /**
     * 自定义分页查询
     * @param userVO
     * @param page
     * @return
     */
    IPage<User> getPageList(UserVo userVo, Page<User> page);


    /**
     * 通过用户名查找用户信息
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 通过手机号查找用户
     * @param phoneNum 用户手机号
     * @return User用户信息
     */
    User findByPhoneNum(String phoneNum);
}
