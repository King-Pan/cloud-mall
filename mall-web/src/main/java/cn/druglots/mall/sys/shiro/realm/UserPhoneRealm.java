package cn.druglots.mall.sys.shiro.realm;

import cn.druglots.mall.common.Constanst;
import cn.druglots.mall.sys.shiro.LoginType;
import cn.druglots.mall.sys.shiro.UserToken;
import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.shiro.realm
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-03 23:27
 * @Description: 手机验证码登录
 */
@Slf4j
public class UserPhoneRealm extends AuthorizingRealm {

  @Autowired private IUserService userService;

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
   *
   * @param authToken 用户身份信息
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken)
      throws AuthenticationException {
    log.info("---------------- 手机验证码登录 ----------------------");
    UserToken token = (UserToken) authToken;
    String phone = token.getUsername();
    // 手机验证码
    String validCode = String.valueOf(token.getPassword());

    // 这里假装从redis中获取了验证码为 123456，并对比密码是否正确
    if (!"123456".equals(validCode)) {
      log.debug("验证码错误，手机号为：{}", phone);
      throw new IncorrectCredentialsException();
    }

    User user = userService.findByPhoneNum(phone);
    if (user == null) {
      throw new UnknownAccountException();
    }
    // 用户为禁用状态
    if (!Constanst.OPEN_STATUS.equals(user.getStatus())) {
      throw new DisabledAccountException();
    }

    SimpleAuthenticationInfo authenticationInfo =
        new SimpleAuthenticationInfo(user, validCode, getName());
    return authenticationInfo;
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    if (token instanceof UserToken) {
      return ((UserToken) token).getLoginType() == LoginType.USER_PHONE;
    } else {
      return false;
    }
  }

  @Override
  public String getName() {
    return LoginType.USER_PHONE.getType();
  }
}
