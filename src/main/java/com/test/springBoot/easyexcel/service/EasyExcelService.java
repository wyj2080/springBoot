package com.test.springBoot.easyexcel.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.test.springBoot.easyexcel.entity.DemoData;
import com.test.springBoot.easyexcel.entity.ExcelUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> list = EasyExcel.read(fileName).sheet().doReadSync();
        list.forEach(t ->System.out.println(t));
    }

    public void simpleWrite() {
        // 写法1
        String fileName = "d://test.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        List<ExcelUser> list = new ArrayList<>();
        ExcelUser u = new ExcelUser("aaa",18);
        list.add(u);
        EasyExcel.write(fileName).sheet().doWrite(list);

    }
}
