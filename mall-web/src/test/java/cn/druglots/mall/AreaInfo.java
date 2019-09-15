package cn.druglots.mall;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 23:32
 * @Description:
 */
@Data
public class AreaInfo {
    private String  citycode;
    private String  adcode;
    private String  name;
    private String  center;
    private String  level;
    private List<AreaInfo> districts = new ArrayList<>();
}
