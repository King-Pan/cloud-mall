package cn.druglots.mall.sys.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.sys.config
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-06 00:08
 * @Description: shiro相关控制器
 */
@RestController
public class ShiroController {


    @RequestMapping("/unauthorized")
    public Object unauthorized() {
        return "没有权限";
    }
}
