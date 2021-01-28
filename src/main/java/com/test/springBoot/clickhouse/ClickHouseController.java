package com.test.springBoot.clickhouse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.test.springBoot.clickhouse.mapper.RequestLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description: clickHouse常用方法
 * @Author: wangyinjia
 * @Date: 2021/1/27
 */
@Controller
@RequestMapping("/ch")
public class ClickHouseController {
    @Autowired
    private RequestLogMapper requestLogMapper;

    /**
     * test
     */
    @GetMapping("/test")
    public void test(){
        LambdaQueryWrapper<RequestLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(RequestLog::getRequestTime);
        wrapper.last("limit 10");
        List<RequestLog> list = requestLogMapper.selectList(wrapper);
        list.forEach(t -> System.out.println(t.toString()));
    }

}
