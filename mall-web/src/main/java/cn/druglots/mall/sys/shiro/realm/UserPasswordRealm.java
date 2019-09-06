package cn.druglots.mall.sys.shiro.realm;

import cn.druglots.mall.common.Constanst;
import cn.druglots.mall.core.exception.BusinessException;
import cn.druglots.mall.sys.shiro.LoginType;
import cn.druglots.mall.sys.shiro.UserToken;
import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.shiro.realm
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-03 23:05
 * @Description: 用户密码登录realm
 */
@Slf4j
public class UserPasswordRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * @param authToken 用户身份信息
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

        log.info("---------------- 用户密码登录 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String username = token.getUsername();
        log.info("登录用户名:{}",username);
        // 从数据库获取对应用户名密码的用户
        User user = userService.findByUserName(username);
        if(user != null){
            if (!Constanst.OPEN_STATUS.equals(user.getStatus())) {
                throw new BusinessException("用户被禁用");
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    //用户
                    user,
                    //密码
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                    //realm name
                    getName()
            );
            return authenticationInfo;
        }
        throw new BusinessException("用户密码不匹配");
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof UserToken) {
            return ((UserToken) token).getLoginType() == LoginType.USER_PASSWORD;
        } else {
            return false;
        }
    }

    @Override
    public String getName() {
        return LoginType.USER_PASSWORD.getType();
    }
}
