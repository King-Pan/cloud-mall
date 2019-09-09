package cn.druglots.mall.core.shiro.realm;

import cn.druglots.mall.user.entity.Permission;
import cn.druglots.mall.user.entity.Role;
import cn.druglots.mall.user.entity.User;
import cn.druglots.mall.user.service.IPermissionService;
import cn.druglots.mall.user.service.IRoleService;
import cn.druglots.mall.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.shiro.realm
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-03 23:35
 * @Description: 统一角色授权控制策略
 */
@Slf4j
public class AuthorizationRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;


    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("---------------- 执行 Shiro 权限获取 ---------------------");
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principal instanceof User) {
            User userLogin = (User) principal;
            if(userLogin != null){
                List<Role> roleList = roleService.findByUserId(userLogin.getId());
                if(CollectionUtils.isNotEmpty(roleList)){
                    for(Role role : roleList){
                        info.addRole(role.getRoleCode());
                        List<Permission> permissionList = permissionService.findListByRoleId(role.getId());
                        if(CollectionUtils.isNotEmpty(permissionList)){
                            for (Permission permission : permissionList){
                                if(StringUtils.isNoneBlank(permission.getExpression())){
                                    info.addStringPermission(permission.getExpression());
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return null;
    }
}
