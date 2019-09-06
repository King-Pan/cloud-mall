package cn.druglots.mall.sys.shiro;

import cn.druglots.mall.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.config
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-06 00:08
 * @Description: shiro相关控制器
 */
@Slf4j
@RestController
public class ShiroController {


    @RequestMapping("/unauthorized")
    public Object unauthorized() {
        return "没有权限";
    }


    @PostMapping("/login")
    public Object login(User user) {
        String userName = user.getUserName();
        if (userName != null && user.getPassword() != null) {
            UserToken token = new UserToken(LoginType.USER_PASSWORD, userName, user.getPassword());
            Subject currentUser = SecurityUtils.getSubject();
            log.info("用户[" + userName + "]进行登录验证..验证开始");
            try {
                currentUser.login(token);
                //验证是否登录成功
                if (currentUser.isAuthenticated()) {
                    log.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
                    log.info("用户[" + userName + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");

                } else {
                    token.clear();
                    log.info("用户[{}]登录认证失败,重新登陆", userName);
                    return "redirect:/login";
                }
            } catch (UnknownAccountException uae) {
                log.info("对用户[{}]进行登录验证..验证失败-username wasn't in the system", userName);
            } catch (IncorrectCredentialsException ice) {
                log.info("对用户[{}]进行登录验证..验证失败-password didn't match", userName);
            } catch (LockedAccountException lae) {
                log.info("对用户[{}]进行登录验证..验证失败-account is locked in the system", userName);
            } catch (AuthenticationException ae) {
                log.error(ae.getMessage());
            }
        }
        return "post login cz";
    }

    @GetMapping("/login")
    public Object loginInfo() {
        return "need login";
    }
}
