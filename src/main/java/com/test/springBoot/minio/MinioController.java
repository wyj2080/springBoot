package com.test.springBoot.minio;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import io.minio.messages.Upload;
import io.minio.policy.PolicyType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.List;

/**
 * @Description: minio
 * @Author: wangyinjia
 * @Date: 2020/8/13
 * @Version: 1.0
 */
@Controller
@RequestMapping("/minio")
public class MinioController {
    String endPoint = "http://ip:9000";
    String accessKey = "账号";
    String secretKey = "密码";

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(){

    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public void upload() throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
        // 检查存储桶是否已经存在
        boolean isExist = minioClient.bucketExists("test");
        if(isExist) {
            System.out.println("Bucket already exists.");
        } else {
            // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
            minioClient.makeBucket("test");
        }

        // 使用putObject上传一个文件到存储桶中。
        minioClient.putObject("test","tt.xlsx", "d://test.xlsx");

    }

    /**
     * 桶
     */
    @RequestMapping(value = "/bucket", method = RequestMethod.GET)
    public void bucket() throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
        if(!minioClient.bucketExists("store")){
            //创建桶
            minioClient.makeBucket("store");
        }
        //桶list
        List<Bucket> bucketList = minioClient.listBuckets();
        //删除桶（需空）
        minioClient.makeBucket("delete");
        minioClient.removeBucket("delete");
        //桶内对象list
        Iterable<Result<Item>> objectList = minioClient.listObjects("store", "文件名前缀搜索");
        objectList.forEach(t -> {
            try {
                System.out.println("最后修改日期：" + t.get().lastModified().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()+
                        ", 名称："+t.get().objectName()+", 大小："+ t.get().objectSize());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        //部分上传的文件
        Iterable<Result<Upload>> errorList = minioClient.listIncompleteUploads("store");
        errorList.forEach(t -> {
            try {
                System.out.println("名称："+t.get().objectName());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        //桶策略
        minioClient.getBucketPolicy("store");
        minioClient.setBucketPolicy("store", "", PolicyType.NONE);
    }

    /**
     * 对象操作
     */
    @RequestMapping(value = "/obj", method = RequestMethod.GET)
    public void obj() throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
        InputStream inputStream = minioClient.getObject("store", "vue.txt");

    }

}
