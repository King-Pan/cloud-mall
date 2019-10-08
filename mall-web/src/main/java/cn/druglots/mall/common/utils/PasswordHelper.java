package cn.druglots.mall.common.utils;

import cn.druglots.mall.core.config.propretise.ShiroProperties;
import cn.druglots.mall.user.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author King-Pan(pwpw1218@gmail.com)
 * @date 2019-09-06 23:24
 *  用户密码工具类
 */
@Lazy
@Component
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Autowired
    private ShiroProperties shiroProperties;


    public String encryptPassword(User user, String password) {
        return new SimpleHash(
                //加密算法
                shiroProperties.getPassword().getAlgorithmName(),
                //密码
                password,
                //salt盐   username + salt
                ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                //迭代次数
                shiroProperties.getPassword().getHashIterations()
        ).toHex();
    }

    public void encryptPassword(User user, Boolean isNew) {
        String password;
        if (isNew) {
            user.setSalt(user.getUserName() + randomNumberGenerator.nextBytes().toHex());
            password = shiroProperties.getPassword().getDefaultPassword();
        } else {
            password = user.getPassword();
        }

        String newPassword = new SimpleHash(
                //加密算法
                shiroProperties.getPassword().getAlgorithmName(),
                //密码
                password,
                //salt盐   username + salt
                ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                //迭代次数
                shiroProperties.getPassword().getHashIterations()
        ).toHex();

        user.setPassword(newPassword);
    }


    public boolean matchPassword(String password, User user) {

        //根据salt和密码生成加密后的密码
        String newPassword = new SimpleHash(
                //加密算法
                shiroProperties.getPassword().getAlgorithmName(),
                //密码
                password,
                //salt盐   username + salt
                ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                //迭代次数
                shiroProperties.getPassword().getHashIterations()
        ).toHex();

        return newPassword.equals(user.getPassword());
    }

}
