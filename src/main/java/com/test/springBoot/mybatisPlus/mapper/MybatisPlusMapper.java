package com.test.springBoot.mybatisPlus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.springBoot.mybatisPlus.entity.MybatisPlus;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2021-07-27
 */
public interface MybatisPlusMapper extends BaseMapper<MybatisPlus> {
    /**xml分页查询*/
    IPage<MybatisPlus> findPage(Page<MybatisPlus> p);
}
