package cn.druglots.mall.sys.shiro;

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
@RestController
public class ShiroController {


    @RequestMapping("/unauthorized")
    public Object unauthorized() {
        return "没有权限";
    }


    @PostMapping("/login")
    public Object login(){
        return "登录界面";
    }

    @GetMapping("/login")
    public Object loginInfo(){
        return "need login";
    }
}
