package cn.druglots.mall.sys.shiro.realm;

import cn.druglots.mall.common.Constanst;
import cn.druglots.mall.core.exception.BusinessException;
import cn.druglots.mall.core.shiro.LoginType;
import cn.druglots.mall.core.shiro.UserToken;
import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.shiro.realm
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-07 16:15
 * @Description: jwt身份认证
 */
@Slf4j
public class JWTShiroRealm extends AuthorizingRealm {

    protected IUserService userService;

    public JWTShiroRealm(IUserService userService){
        this.userService = userService;
        this.setCredentialsMatcher(new cn.druglots.mall.sys.shiro.JWTCredentialsMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        if (token instanceof UserToken) {
            return ((UserToken) token).getLoginType() == LoginType.JWT_LOGIN;
        } else {
            return false;
        }
    }

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

        log.info("---------------- jwt登录 ----------------------");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String username = token.getUsername();
        log.info("jwt登录用户名:{}",username);
        // 从数据库获取对应用户名密码的用户
        User user = userService.findByUserName(username);
        if(null != user){
            if (!Constanst.OPEN_STATUS.equals(user.getStatus())) {
                throw new BusinessException("用户被禁用");
            }
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user,
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                    getName()
            );
            return authenticationInfo;
        }
        throw new BusinessException("jwt登录失败: 用户密码不匹配");

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }
}

