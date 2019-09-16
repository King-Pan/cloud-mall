package cn.druglots.mall.user.controller;

import cn.druglots.mall.core.aspect.Log;
import cn.druglots.mall.core.rst.ResultGenerator;
import cn.druglots.mall.core.shiro.LoginType;
import cn.druglots.mall.core.shiro.UserToken;
import cn.druglots.mall.user.entity.UserLoginVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.config
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-06 00:08
 * @Description: shiro相关控制器
 */
@Slf4j
@Api(value = "用户登录控制器", tags = "用户登录控制器")
@RestController
public class LoginController {


    @ApiOperation("未授权")
    @GetMapping("/unauthorized")
    public Object unauthorized() {
        return ResultGenerator.genFailResult(HttpStatus.UNAUTHORIZED, "未登录");
    }


    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    @Log(module = "用户模块",description = "用户登录处理方法",fp = "用户登录")
    public Object login(@RequestBody  @ApiParam(name="用户登录信息",value="用户登录信息-json",required=true) UserLoginVo userLoginVo) {
        String userName = userLoginVo.getUserName();
        Map<String, Object> result = new HashMap<>(10);
        if (userName != null && userLoginVo.getPassword() != null) {
            LoginType loginType = null;
            if (LoginType.USER_PASSWORD.getType().equals(userLoginVo.getType())) {
                loginType = LoginType.USER_PASSWORD;
            } else if (LoginType.USER_PHONE.getType().equals(userLoginVo.getType())) {
                loginType = LoginType.USER_PHONE;
            } else if (LoginType.JWT_LOGIN.getType().equals(userLoginVo.getType())) {
                loginType = LoginType.JWT_LOGIN;
            } else if (LoginType.WECHAT_LOGIN.getType().equals(userLoginVo.getType())) {
                loginType = LoginType.WECHAT_LOGIN;
            }
            UserToken token = new UserToken(loginType, userName, userLoginVo.getPassword());
            Subject currentUser = SecurityUtils.getSubject();
            log.info("用户[" + userName + "]进行登录验证..验证开始");
            try {
                currentUser.login(token);
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    log.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    result.put("code", 200);
                    result.put("msg", "登录失败");
                } else {
                    token.clear();
                    log.info("用户[{}]登录认证失败,重新登陆", userName);
                    return "redirect:/login";
                }
            } catch (UnknownAccountException uae) {
                log.warn("对用户[{}]进行登录验证..验证失败-username wasn't in the system", userName);
            } catch (IncorrectCredentialsException ice) {
                log.warn("对用户[{}]进行登录验证..验证失败-password didn't match", userName);
            } catch (LockedAccountException lae) {
                log.warn("对用户[{}]进行登录验证..验证失败-account is locked in the system", userName);
            } catch (AuthenticationException ae) {
                log.error(ae.getMessage());
            }
        }

        return result;
    }

    @GetMapping("/login")
    public Object loginInfo() {
        return ResultGenerator.genFailResult(HttpStatus.UNAUTHORIZED, "未登录");
    }
}
