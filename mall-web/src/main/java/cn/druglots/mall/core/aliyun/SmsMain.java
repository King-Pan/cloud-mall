package cn.druglots.mall.core.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.aliyun
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-14 23:48
 * @Description: https://help.aliyun.com/document_detail/32012.html?spm=a2c4g.11186623.2.14.7e213ba9lnaety
 */
public class SmsMain {

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "T9Kavi2RuoNLW6vA";
    static final String accessKeySecret = "dhLbgKXxkUOsHK6iTfxcEKg6Xa9e1Y";
    static final String endPoint = "http://oss-cn-hangzhou.aliyuncs.com";


    public static void main(String[] args) {
        createDir("mall-aut","aa");
    }


    private static boolean exists(String bucketName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        // 新建存储空间默认为标准存储类型，私有权限。
        boolean exists = ossClient.doesBucketExist(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
        return exists;
    }

    private static Bucket createBucketName(String bucketName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        // 新建存储空间默认为标准存储类型，私有权限。
        Bucket bucket = ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
        return bucket;
    }


    /**
     * 已有的文件会直接覆盖
     * @param bucketName
     * @param dir
     */
    private static void createDir(String bucketName,String dir) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("D:\\ea-ddl.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject("mall-auth", "4674142125/ea-ddl2.png", inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
