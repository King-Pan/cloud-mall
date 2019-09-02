package cn.druglots.mall.user.controller;


import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.entity.UserVo;
import cn.druglots.mall.user.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author King-Pan
 * @since 2019-09-02
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public Object pageList(){
       return userService.page(new Page<User>(1,5));
    }

    @GetMapping("/2")
    public Object pageListVo(@RequestParam("realName")String realName,@RequestParam("phoneNum")String phoneNum,@RequestParam("companyId") Long companyId){

        UserVo userVo = new UserVo();
        userVo.setCompanyId(companyId);
        userVo.setPhoneNum(phoneNum);
        userVo.setRealName(realName);
        log.info("请求参数:" + userVo);
        return userService.getPageList(userVo,new Page<User>(1,5));
    }
}
