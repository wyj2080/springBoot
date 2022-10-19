package com.test.springBoot.clickhouse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.test.springBoot.clickhouse.mapper.RequestLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: clickHouse常用方法
 * @Author: wangyinjia
 * @Date: 2021/1/27
 */
@Controller
@RequestMapping("/ch")
@Slf4j
public class ClickHouseController {
    @Autowired
    private RequestLogMapper requestLogMapper;

    /**
     * test
     */
    @GetMapping("/test")
    public void test(){
        log.info("清空表开始");
        requestLogMapper.truncateTable();
        log.info("清空表结束");
        requestLogMapper.insertData();
        log.info("插入表完成");
        log.info(requestLogMapper.count()+"");
        LambdaQueryWrapper<RequestLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RequestLog::getRequestTime);
        wrapper.last("limit 10");
        List<RequestLog> list = requestLogMapper.selectList(wrapper);
        list.forEach(t -> System.out.println(t.toString()));
    }

    @GetMapping("/2")
    public void test2(){
        log.info("清空表开始");
        requestLogMapper.truncateTable();
        log.info("清空表结束");
        List<RequestLog> ilist = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            RequestLog requestLog = new RequestLog();
            requestLog.setRequestTime("2019-09-09");
            ilist.add(requestLog);
        }
        ilist.parallelStream().forEach(t -> {
            requestLogMapper.insert(t);
        });
        log.info("插入表完成");
        log.info(requestLogMapper.count()+"");
        LambdaQueryWrapper<RequestLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RequestLog::getRequestTime);
        wrapper.last("limit 10");
        List<RequestLog> list = requestLogMapper.selectList(wrapper);
        list.forEach(t -> System.out.println(t.toString()));
    }

}
