package cn.druglots.mall.core.init;

import cn.druglots.mall.sys.service.IRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author king-pan
 * @date 2019/10/8 23:15
 */
@Slf4j
@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("初始化资源-begin");
        IRegionService service = contextRefreshedEvent.getApplicationContext().getBean(IRegionService.class);
        service.getRegionList();
        log.info("初始化资源-end");
    }
}