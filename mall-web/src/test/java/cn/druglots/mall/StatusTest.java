package cn.druglots.mall;

import cn.druglots.mall.sys.service.IRegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author king-pan
 * @date 2019/10/8 22:28
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StatusTest {


    @Autowired
    private IRegionService regionService;


    @Test
    public void test(){
        System.out.println(regionService.getRegionList());
    }
}
