package com.test.springBoot.easyexcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.test.springBoot.easyexcel.entity.DemoData;
import com.test.springBoot.easyexcel.entity.ExcelUser;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wangyinjia
 * @description
 * @date 2020/8/12
 */
@Service
public class EasyExcelService {

    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "d://test.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
        List<Map<String, Object>> list = EasyExcel.read(fileName).sheet().doReadSync();
        list.forEach(t -> System.out.println(t.toString()));
//        // 写法2：
//        fileName = "d://test.xlsx";
//        ExcelReader excelReader = null;
//        try {
//            excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            excelReader.read(readSheet);
//        } finally {
//            if (excelReader != null) {
//                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//                excelReader.finish();
//            }
//        }
    }

    public void simpleWrite() throws Exception {
        //----------------------------本地写入
        String fileName = "d://test2.xlsx";
//        List<ExcelUser> list = new ArrayList<>();
        List<ExcelUser> list = new ArrayList<>();

        // 这里 需要指定写用哪个class去读 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExcelUser.class).sheet().doWrite(list);

        //--------------------------模板在minio上，并写入miniol里
        //桶
        String bucketName = "excel";
        //头部信息
        Object head = new Object();
        //明细
        List<String> data = new ArrayList<>();
        //查出模板
        MinioClient minioClient = new MinioClient("http://192.168.0.100:9000", "admin", "zt123456");
        boolean isExist = minioClient.bucketExists(bucketName);
        if(!isExist) {
            minioClient.makeBucket(bucketName);
        }
        InputStream inputStream = minioClient.getObject(bucketName, "template/" + "模板.xlsx");
        File excelFile = File.createTempFile("temp", ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(excelFile).withTemplate(inputStream).registerConverter(new LocalDateTimeConverter()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        if(head != null){
            excelWriter.fill(describe(head), writeSheet);
        }
        excelWriter.fill(data, writeSheet);
        excelWriter.finish();
        minioClient.putObject(bucketName,"target/" + "目标文件名称" + fileName, excelFile.getAbsolutePath());
        String url = minioClient.getObjectUrl(bucketName, "target/" + "目标文件名称" + fileName);	//文件访问路径
        excelFile.deleteOnExit();
        inputStream.close();

    }

    /**
     * bean转map
     */
    public Map<String, Object> describe(Object bean) throws Exception{
        Map<String, Object> map = new HashMap<>();
        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(bean);
                //时间给Converterch处理，其他变字符串
                if(value instanceof LocalDateTime){
                    map.put(key, value);
                }else{
                    map.put(key, value == null ? "" : String.valueOf(value));
                }

            }
        }
        return map;
    }
}
