package com.test.springBoot.mybatisPlus.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.test.springBoot.mybatisPlus.entity.MybatisPlus;
import com.test.springBoot.mybatisPlus.entity.MybatisPlusDTO;
import com.test.springBoot.mybatisPlus.service.IMybatisPlusService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/mybatis-plus")
public class MybatisPlusController {
    @Autowired
    private IMybatisPlusService mybatisPlusService;

    @GetMapping("/list")
    public List<MybatisPlus> findList(){
        return mybatisPlusService.list();
    }

    @GetMapping("/add")
    public void add(){
        MybatisPlus mybatisPlus = new MybatisPlus();
        mybatisPlus.setName(LocalDateTime.now().toString());
        mybatisPlus.setAge((int) (Math.random()*100));
        MybatisPlusDTO.Config config = new MybatisPlusDTO.Config();
        config.setAmount(BigDecimal.valueOf(Math.random() * 100).setScale(2, BigDecimal.ROUND_HALF_UP));
        config.setKey("aaa");
        config.setStatus(1);
        mybatisPlus.setConfig(JSONObject.toJSONString(config));
        mybatisPlusService.save(mybatisPlus);
    }

    @PutMapping("/update")
    public void update(@RequestBody MybatisPlus mybatisPlus){
        mybatisPlusService.updateById(mybatisPlus);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam("id")Long id){
        mybatisPlusService.removeById(id);
    }

    @GetMapping("/test")
    public List<MybatisPlus> test(){
        new UpdateWrapper().lambda().set("name", null);
        return null;
//        LambdaQueryWrapper<MybatisPlus> wrapper = new LambdaQueryWrapper<>();
//        wrapper.notBetween(MybatisPlus::getAge, 18, 30);
//        return mybatisPlusService.list(wrapper);
    }

    /**
     * CRUD接口
     */
    public void CRUD(){
        LambdaQueryWrapper<MybatisPlus> wrapper = new LambdaQueryWrapper<>();
        MybatisPlus mybatisPlus = new MybatisPlus();
        //新增
        mybatisPlusService.save(mybatisPlus);
        mybatisPlusService.saveBatch(new ArrayList<>());
        //删除
        mybatisPlusService.remove(wrapper);
        mybatisPlusService.removeById(0L);
        mybatisPlusService.removeByIds(new ArrayList<>());
        //更新
        mybatisPlusService.update(mybatisPlus, wrapper);
        mybatisPlusService.updateById(mybatisPlus);
        mybatisPlusService.updateBatchById(new ArrayList<>());
        //一个
        mybatisPlusService.getById(0L);
        mybatisPlusService.getOne(wrapper);
        mybatisPlusService.getOne(wrapper, false);//不抛异常相当于limit 1
        mybatisPlusService.getOne(wrapper.last("limit 1"));
        //list
        mybatisPlusService.list();
        mybatisPlusService.list(wrapper);
        mybatisPlusService.listByIds(new ArrayList<>());
        //分页
        Page<MybatisPlus> p = new Page<>(0,10);
        Page<MybatisPlus> page = mybatisPlusService.page(p, wrapper);
        long total = page.getTotal();
        List<MybatisPlus> records = page.getRecords();
        //计数
        int count = mybatisPlusService.count(wrapper);
        //chain链式
        //查询条件不重复利用的情况
        mybatisPlusService.lambdaQuery().eq(MybatisPlus::getAge, 18).list();
        mybatisPlusService.lambdaUpdate().eq(MybatisPlus::getAge, 18).update(mybatisPlus);

        //mapper
        mybatisPlusService.getBaseMapper().insert(mybatisPlus);
        mybatisPlusService.getBaseMapper().delete(wrapper);
        mybatisPlusService.getBaseMapper().deleteById(0L);
        mybatisPlusService.getBaseMapper().deleteBatchIds(new ArrayList<>());
        mybatisPlusService.getBaseMapper().update(mybatisPlus, wrapper);
        mybatisPlusService.getBaseMapper().updateById(mybatisPlus);
        mybatisPlusService.getBaseMapper().selectById(0L);
        mybatisPlusService.getBaseMapper().selectBatchIds(new ArrayList<>());
        mybatisPlusService.getBaseMapper().selectOne(wrapper);
        mybatisPlusService.getBaseMapper().selectList(wrapper);
        mybatisPlusService.getBaseMapper().selectPage(p, wrapper);
        mybatisPlusService.getBaseMapper().selectCount(wrapper);
    }

    /**
     * wrapper条件
     */
    public void wrapper(){
        String name = "591";
        LambdaQueryWrapper<MybatisPlus> wrapper = new LambdaQueryWrapper<>();
        //前面都可以加条件
        wrapper.like(StringUtils.isNotBlank(name), MybatisPlus::getName, name);
//        wrapper.allEq();
        wrapper.eq(MybatisPlus::getAge, 18);
        wrapper.ne(MybatisPlus::getAge, 18);
        //gt,ge,lt,le 大于，大于等于，小于，小于等于
        wrapper.ge(MybatisPlus::getAge, 18);//大于等于18
        wrapper.between(MybatisPlus::getAge, 18, 20);
        wrapper.notBetween(MybatisPlus::getAge, 18, 20);
        wrapper.like(MybatisPlus::getName, "阿姨");
        wrapper.and(t -> t.like(MybatisPlus::getName, "阿姨").or().like(MybatisPlus::getAge, 20));
        wrapper.notLike(MybatisPlus::getName, "阿姨");
        wrapper.isNull(MybatisPlus::getAge);
        wrapper.isNotNull(MybatisPlus::getAge);
        wrapper.in(MybatisPlus::getAge, Arrays.asList(1,10,100));
        wrapper.notIn(MybatisPlus::getAge, new ArrayList<>());
        wrapper.inSql(MybatisPlus::getId, "select id from ...");
        wrapper.groupBy(MybatisPlus::getAge,MybatisPlus::getAmount);
        wrapper.orderBy(true, false, MybatisPlus::getAmount, MybatisPlus::getAge);
        wrapper.orderByAsc(MybatisPlus::getName).orderByDesc(MybatisPlus::getAge);
        wrapper.having("sum(age) > 10");
        wrapper.last("limit 10");

        wrapper.select(MybatisPlus::getId, MybatisPlus::getAge);

    }

}
