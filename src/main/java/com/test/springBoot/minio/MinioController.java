package com.test.springBoot.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
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

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.Arrays;
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
    MinioClient minioClient;

    @PostConstruct
    public void init() throws Exception {
        minioClient = new MinioClient(endPoint, accessKey, secretKey);
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(){

    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public void upload() throws Exception {
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
        InputStream inputStream = minioClient.getObject("store", "vue.txt");
        printInputStream(inputStream);
        // 关闭流，此处为示例，流关闭最好放在finally块。
        inputStream.close();
        //检查对象存在，不存在会抛异常(对象信息)
        ObjectStat objectStat = minioClient.statObject("store", "vue.txt");
        //直接下载到本地
        minioClient.getObject("store", "vue.txt", "d://vue.txt");

        //流上传成文件
        StringBuilder builder = new StringBuilder();
        builder.append("文字");
        InputStream strStream = new ByteArrayInputStream(builder.toString().getBytes());
        minioClient.putObject("store", "test.txt",strStream, strStream.available(), "application/octet-stream");
        //本地文件上传
        minioClient.putObject("store", "test.xlsx","d://test.xlsx");
        //复制对象
        minioClient.copyObject("store",  "test.txt", "store", "test2.txt");
        //删除对象
//        minioClient.removeObject("store", "test.txt");
//        minioClient.removeObject("store", "test.xlsx");
//        minioClient.removeObject("store", "test3.xlsx");
        List<String> fileNames = Arrays.asList("test.txt", "test.xlsx");
        minioClient.removeObject("store", fileNames);//还没成功
    }

    /**
     * 打印流
     */
    public void printInputStream(InputStream inputStream) throws IOException {
        // 读取输入流直到EOF并打印到控制台。
        byte[] buf = new byte[16384];
        int bytesRead;
        while ((bytesRead = inputStream.read(buf, 0, buf.length)) >= 0) {
            System.out.println(new String(buf, 0, bytesRead, StandardCharsets.UTF_8));
        }
    }

    /**
     * 加密对象
     */
    @RequestMapping(value = "/secret", method = RequestMethod.GET)
    public void secret() throws Exception {
        //生成256位AES key.
        KeyGenerator symKeyGenerator = KeyGenerator.getInstance("AES");
        symKeyGenerator.init(256);
        SecretKey symKey = symKeyGenerator.generateKey();
        //流上传成文件
        StringBuilder builder = new StringBuilder();
        builder.append("文字");
        InputStream strStream = new ByteArrayInputStream(builder.toString().getBytes());
        minioClient.putObject("store", "test.txt",strStream, strStream.available(), "application/octet-stream", symKey);

        //生成256位AES key。(未成功)
        InputStream skeyStream = minioClient.getObject("store", "vue.txt", symKey);
        printInputStream(skeyStream);
    }

}
