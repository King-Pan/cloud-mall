package cn.druglots.mall.common;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-03 23:18
 * @Description: 常量
 */
public interface Constanst {
     String OPEN_STATUS = "1";
     String CLOST_STATUS = "0";
     String DELETE_STATUS = "2";

     Integer REQUEST_SUCCESS = 200;


     /**
      * 授权请求头
      */
     String AUTHORIZATION = "auth_token";

     /**
      * 无状态请求
      */
     String REFERENCED_SESSION_ID_SOURCE = "Stateless request";
}
