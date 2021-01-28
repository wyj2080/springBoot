package com.test.springBoot.clickhouse.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.test.springBoot.clickhouse.RequestLog;
import org.springframework.stereotype.Repository;

/**
 * @author wangyinjia
 * @description
 * @date 2021/1/27
 */
@Repository
public interface RequestLogMapper extends BaseMapper<RequestLog> {
}
