package cn.druglots.mall.core.aliyun;

import cn.druglots.mall.core.config.propretise.AliyunProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.aliyun
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-15 00:45
 * @Description: 阿里云OSS存储工具类
 */
@Component
public class OssUtils {

    @Autowired
    private  AliyunProperties aliyunProperties;

    public static final String BUCKET_NAME = "druglots-mall";

    private  OSS getOSS() {
        return new OSSClientBuilder().build(aliyunProperties.getOss().getEndPoint(), aliyunProperties.getOss().getAccessKeyId(), aliyunProperties.getOss().getAccessKeySecret());
    }

    public  Boolean createBucketName(String bucketName) {
        Boolean result = false;
        // 创建OSSClient实例。
        OSS ossClient = getOSS();
        boolean exists = ossClient.doesBucketExist(bucketName);
        if (!exists) {
            // 新建存储空间默认为标准存储类型，私有权限。
            Bucket bucket = ossClient.createBucket(bucketName);
            result = true;
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return result;
    }


    /**
     * 上传文件
     *
     * @param bucketName  bucket名称
     * @param fileName    oss存储文件名称:   'a.png'或者 'img/a.png' 可以自动创建目录
     * @param inputStream 输入流
     */
    public  Boolean updateFile(String bucketName, String fileName, InputStream inputStream) {
        boolean flag = false;
        // 创建OSSClient实例。
        OSS ossClient = null;
        try {
            ossClient = getOSS();
            // 上传文件流。
            ossClient.putObject(bucketName, fileName, inputStream);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return flag;
    }

    /**
     * 上传文件
     *
     * @param fileName    oss存储文件名称:   'a.png'或者 'img/a.png' 可以自动创建目录
     * @param inputStream 输入流
     */
    public  Boolean updateFile(String fileName, InputStream inputStream) {
      return updateFile(BUCKET_NAME,fileName,inputStream);
    }

    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param fileName   oss存储文件名称:   'a.png'或者 'img/a.png' 可以自动创建目录
     * @param file       文件对象
     */
    public Boolean updateFile(String bucketName, String fileName, File file) {
        boolean flag = false;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return updateFile(bucketName, fileName, inputStream);
    }

    /**
     * 上传文件
     *
     * @param fileName   oss存储文件名称:   'a.png'或者 'img/a.png' 可以自动创建目录
     * @param file       文件对象
     */
    public Boolean updateFile( String fileName, File file) {
       return updateFile(BUCKET_NAME,fileName,file);
    }

    /**
     * 上传文件
     *
     * @param fileName   oss存储文件名称:   'a.png'或者 'img/a.png' 可以自动创建目录
     * @param filePath   文件全路径
     */
    public Boolean updateFile( String fileName, String filePath) {
        return updateFile(BUCKET_NAME, fileName, filePath);
    }


    /**
     * 上传文件
     *
     * @param bucketName bucket名称
     * @param fileName   oss存储文件名称:   'a.png'或者 'img/a.png' 可以自动创建目录
     * @param filePath   文件全路径
     */
    public Boolean updateFile(String bucketName, String fileName, String filePath) {
        File file = new File(filePath);
        return updateFile(bucketName, fileName, file);
    }



    public void setAliyunProperties(AliyunProperties aliyunProperties) {
        this.aliyunProperties = aliyunProperties;
    }
}
