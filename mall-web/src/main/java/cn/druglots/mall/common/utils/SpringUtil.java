package cn.druglots.mall.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.utils
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-11 23:23
 * @Description: Spring工具类，可以获取spring容器中的实例
 */
@Slf4j
@Component
public class SpringUtil  implements ApplicationContextAware {
    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        log.info("========ApplicationContext配置成功,\n在普通类可以通过调用SpringUtils.getAppContext()获取applicationContext对象,\napplicationContext=" + SpringUtil.applicationContext + "========");
    }

    /**
     * 获取applicationContext
     *
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过bean的name获取Bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过Class获取实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name和class返回指定的bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}