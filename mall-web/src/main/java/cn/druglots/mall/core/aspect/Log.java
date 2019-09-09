package cn.druglots.mall.core.aspect;

import java.lang.annotation.*;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.aspect
 * @Author: king-pan
 * @CreateTime: 2019-09-09 11:10
 * @Description: 日志记录注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     * @return
     */
    String module()  default "" ;


    /**
     * 功能点
     * @return
     */
    String fp() default "";

    /**
     * 说明
     * @return
     */
    String description()  default "";

}
