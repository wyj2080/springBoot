package com.test.springBoot.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import io.minio.messages.Upload;
import io.minio.policy.PolicyType;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: minio
 * @Author: wangyinjia
 * @Date: 2020/8/13
 * @Version: 1.0
 */
@RestController
@RequestMapping("/minio")
public class MinioController {
    /**服务地址*/
    @Value("${minio.endPoint}")
    String endPoint;

    /**账号*/
    @Value("${minio.accessKey}")
    String accessKey;

    /**密码*/
    @Value("${minio.secretKey}")
    String secretKey;

    /**minio客户端*/
    MinioClient minioClient;

    /**
     * 直接定义一个公共的key，应为putObject和getObject需要同一个key
     */
    SecretKey symKey = new SecretKey() {
        @Override
        public String getAlgorithm() {
            return "AES";
        }

        @Override
        public String getFormat() {
            return "RAW";
        }

        /**
         * SecretKey symKey = symKeyGenerator.generateKey();里的byte[] 有32个byte，一共256位
         * @return
         */
        @Override
        public byte[] getEncoded() {
            Integer[] keyArr = {-3,75,60,11,-116,125,87,-86,-68,108,126,-32,-117,-83,40,27,102,-109,-100,70,-67,-123,96,-107,-61,8,41,-44,-119,-91,64,39};
            byte[] result = {};
            for(int i=0;i<keyArr.length;i++){
                result = ArrayUtils.addAll(result, keyArr[i].byteValue());
            }
            return result;
        }
    };

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
     * 获取密钥
     */
    @RequestMapping(value = "/key", method = RequestMethod.GET)
    public void getKey() throws Exception {
        //获取自己key的方式：生成256位AES key
        KeyGenerator symKeyGenerator = KeyGenerator.getInstance("AES");
        symKeyGenerator.init(256);
        SecretKey symKey = symKeyGenerator.generateKey();
        String keyStr = "";
        for (int i = 0; i < symKey.getEncoded().length; i++) {
            keyStr+=symKey.getEncoded()[i]+",";
        }
        keyStr = keyStr.substring(0, keyStr.length() -1);
        System.out.println(keyStr);
    }

    /**
     * 加密对象
     */
    @RequestMapping(value = "/encode", method = RequestMethod.GET)
    public void encode() throws Exception {
        //流上传成文件
        StringBuilder builder = new StringBuilder();
        builder.append("文字");
        InputStream strStream = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
        minioClient.putObject("store", "test.txt",strStream, strStream.available(), "application/octet-stream", symKey);
        strStream.close();
    }

    /**
     * 解密对象
     */
    @RequestMapping(value = "/decode", method = RequestMethod.GET)
    public void decode() throws Exception {
        //获取流
        InputStream strStream = minioClient.getObject("store", "test.txt", symKey);
        printInputStream(strStream);
        strStream.close();
    }

    /**
     * 生成可下载的url
     * 可以私有桶，无法解密，参数为失效秒数：30秒
     */
    @GetMapping("/get")
    public void get() throws Exception{
        String url = minioClient.presignedGetObject("test", "test.txt", 30);
        System.out.println(url);
    }

    @GetMapping("/test")
    public void test(HttpServletResponse response) throws Exception{
        //列出已存在的桶，如果没有test，创建test桶，往里面写入文件
        List<Bucket> bucketList = minioClient.listBuckets();
        bucketList.forEach(t -> System.out.println(t.name()));
        removeBucket("test");
        minioClient.makeBucket("test");
        //流上传成文件
        StringBuilder builder = new StringBuilder();
        builder.append("文字");
        InputStream outStream = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
        minioClient.putObject("test", "test.txt",outStream, outStream.available(), "application/octet-stream", symKey);
        InputStream inStream = minioClient.getObject("test", "test.txt", symKey);
        outStream.close();

        //下载的文件携带这个名称
        response.setHeader("Content-Disposition", "attachment;filename=" + "test.txt");
        //文件下载类型--二进制文件
        response.setContentType("application/octet-stream");

        try {
            ServletOutputStream sos = response.getOutputStream();
            sos.write(IOUtils.toByteArray(inStream));
            inStream.close();
            sos.flush();
            sos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 删除桶
     */
    private void removeBucket(String bucketName) throws Exception{
        if(!minioClient.bucketExists(bucketName)){
            return;
        }
        //删除桶内文件
        Iterable<Result<Item>> objectList = minioClient.listObjects(bucketName);
        objectList.forEach(t -> {
            try {
                System.out.println("删除文件："+t.get().objectName());
                minioClient.removeObject(bucketName, t.get().objectName());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        });
        //删除桶
        minioClient.removeBucket(bucketName);
        System.out.println("删除桶："+bucketName);
    }

}
