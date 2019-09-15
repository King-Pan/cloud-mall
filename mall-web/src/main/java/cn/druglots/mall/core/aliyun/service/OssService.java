package cn.druglots.mall.core.aliyun.service;

import cn.druglots.mall.core.aliyun.OssUtils;
import cn.druglots.mall.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.aliyun.service
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 21:59
 * @Description: oss对象存储服务类
 */
@Service
public class OssService {

    @Autowired
    private OssUtils ossUtils;

    /**
     * 创建bucket
     *
     * @param bucketName
     */
    private Boolean createBucket(String bucketName) {
        return ossUtils.createBucketName(bucketName);
    }


    /**
     * 上传商品默认图片,默认为jpg格式
     *
     * @param inputStream
     */
    public Boolean updateGoodsDefaultImg(InputStream inputStream) {
        return ossUtils.updateFile("default/default_goods.jpg", inputStream);
    }

    /**
     * 上传商品图片
     *
     * @param fileName
     * @param inputStream
     */
    public Boolean updateGoodsImg(String fileName, InputStream inputStream) {
        Long companyId = getCompanyId();
        String path = "goods/" + companyId + "/" + fileName;
        return ossUtils.updateFile(path, inputStream);
    }

    private Long getCompanyId() {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user.getCompanyId();
    }

    /**
     * 上传认证信息
     *
     * @param fileName    文件名
     * @param inputStream 输入流
     */
    public Boolean updateAuthImg(String fileName, InputStream inputStream) {
        Long companyId = getCompanyId();
        String path = "auth/" + companyId + "/" + fileName;
        return ossUtils.updateFile(fileName, inputStream);
    }

    /**
     * 上传logo信息
     *
     * @param fileName
     * @param inputStream
     */
    public Boolean updateLogo(String fileName, InputStream inputStream) {
        Long companyId = getCompanyId();
        String path = "logo/" + companyId + "/" + fileName;
        return  ossUtils.updateFile(fileName, inputStream);
    }
}
