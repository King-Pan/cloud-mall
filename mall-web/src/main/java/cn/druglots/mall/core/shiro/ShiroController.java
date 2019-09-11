package cn.druglots.mall.core.shiro;

import cn.druglots.mall.core.rst.ResultGenerator;
import cn.druglots.mall.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
@Api("shiro登录登出控制器")
@RestController
public class ShiroController {


    @ApiOperation("没有权限页面")
    @RequestMapping("/unauthorized")
    public Object unauthorized() {
        return "没有权限";
    }

    @ApiOperation("获取用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="user",name="user",dataType="User",required=true)
    })
    @PostMapping("/login")
    public Object login(@RequestBody User user) {
        String userName = user.getUserName();
        Map<String, Object> result = new HashMap<>(10);
        if (userName != null && user.getPassword() != null) {
            UserToken token = new UserToken(LoginType.USER_PASSWORD, userName, user.getPassword());
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

        return ResultGenerator.genFailResult(HttpStatus.UNAUTHORIZED,"未登录");
    }
}
