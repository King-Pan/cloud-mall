package cn.druglots.mall.web;

import cn.druglots.mall.core.exception.BusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.web
 * @Author: king-pan
 * @CreateTime: 2019-08-27 23:39
 * @Description: 异常控制器
 */
@Controller
public class ExceptionController {


    @RequestMapping("/api/v1/test_ext")
    public Object index(){
        try {
            int a = 1/0;
        }catch (Exception e){
            throw new BusinessException("401",e.getMessage());
        }
        return "Hello World";
    }

    @RequestMapping("/api/v1/test_ext2")
    @ResponseBody
    public Object json(){
        try {
            int a = 1/0;
        }catch (Exception e){
            throw new BusinessException("401",e.getMessage());
        }
        return "Hello World";
    }

    @RequestMapping("/api/v1/hello")
    @ResponseBody
    public Object hello(){
        return "Hello World";
    }
}
