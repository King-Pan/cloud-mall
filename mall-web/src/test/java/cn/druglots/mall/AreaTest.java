package cn.druglots.mall;

import cn.druglots.mall.common.utils.FastJsonUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 22:51
 * @Description:
 */
public class AreaTest {


    @Test
    public void test() {
        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(new File("C:\\Users\\King-Pan\\Desktop\\cloud-mall\\mall-web\\src\\main\\resources\\city.json")), "utf-8");
            StringBuffer sb = new StringBuffer();
            for (String line : lines) {
                sb.append(line.trim());
            }
            //List<Map<String, Object>> data = FastJsonUtils.getJsonToListMap(sb.toString());
            JSONObject object = JSON.parseObject(sb.toString());

            JSONArray array = object.getJSONArray("districts");
            JSONObject o1 = (JSONObject) array.get(0);
            String citycode = o1.get("adcode").toString();
            JSONArray pro = o1.getJSONArray("districts");
            pro.toArray();

            System.out.println(citycode);
            System.out.println(o1);
            System.out.println(pro);


            AreaInfo areaInfo = FastJsonUtils.getJsonToBean(sb.toString(), AreaInfo.class);
            System.out.println(areaInfo.getAdcode());
            System.out.println(areaInfo.getName());
            System.out.println(areaInfo.getLevel());
            System.out.println(areaInfo.getDistricts());
            List<AreaInfo> l1 = areaInfo.getDistricts();
            AreaInfo country = l1.get(0);
            System.out.println(country.getAdcode());
            System.out.println(country.getName());
            System.out.println(country.getCenter());
            System.out.println(country.getCitycode());
            System.out.println(l1.size());
            List<AreaInfo> prods = country.getDistricts();
            for (AreaInfo prod : prods) {
                System.out.println(prod);
            }
            System.out.println(prods.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
