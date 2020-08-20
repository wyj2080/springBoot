package com.test.springBoot.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.messages.Bucket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    String accessKey = "账户";
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

    @RequestMapping(value = "/bucket", method = RequestMethod.GET)
    public void bucket() throws Exception {
        MinioClient minioClient = new MinioClient(endPoint, accessKey, secretKey);
        if(!minioClient.bucketExists("store")){
            minioClient.makeBucket("store");
        }
        List<Bucket> bucketList = minioClient.listBuckets();
        bucketList.forEach(t -> System.out.println(t.toString()));

    }

}
