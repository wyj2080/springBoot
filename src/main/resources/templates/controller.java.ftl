package ${package.Controller};

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.alibaba.fastjson.JSONObject;
import java.util.List;
import com.google.common.collect.Maps;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.test.springBoot.common.BaseController;
import com.test.springBoot.common.Constants;
import com.test.springBoot.common.R;
import com.test.springBoot.common.WeException;
import com.test.springBoot.common.tools.WeUtil;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Tag(name = "${table.comment!}")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} extends BaseController{
</#if>
    final ${table.serviceName} ${entity?uncap_first}Service;

    /**
    * 获取分页${table.comment!}列表
    *
    * @return
    */
    @Operation(summary = "获取分页${table.comment!}列表", description = "获取分页${table.comment!}列表")
    @GetMapping("/page")
    public R<List<${entity}>> find${entity}List(
    @RequestHeader(value = Constants.PAGE_RANGE_KEY, required = false, defaultValue = Constants.RANGE_DEF) String range,
    @RequestParam(required = false) Map<String, Object> map
    ) {
        Page<${entity}> page = WeUtil.createPage(range);
        List<${entity}> list = ${entity?uncap_first}Service.findPage(page, map);
        setContentRange(range, page.getTotal());
        return R.success(list);
    }

    /**
    * 新增${table.comment!}
    *
    */
    @Operation(summary = "新增${table.comment!}")
    @PostMapping
    public R add${entity}(@RequestBody JSONObject jsonObject){
        ${entity} ${entity?uncap_first} = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), ${entity}.class);
        ${entity?uncap_first}Service.save(${entity?uncap_first});
        return R.success();
    }

    /**
    * 修改${table.comment!}
    *
    */
    @Operation(summary = "修改${table.comment!}")
    @PutMapping("/{id}")
    public R update${entity}(@PathVariable("id") String id, @RequestBody JSONObject jsonObject){
        ${entity} ${entity?uncap_first} = ${entity?uncap_first}Service.getById(id);
        if (${entity?uncap_first} != null) {
            ${entity?uncap_first} = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), ${entity}.class);
            ${entity?uncap_first}Service.updateById(${entity?uncap_first});
        }else {
            throw new WeException("修改出错,数据不存在");
        }
        return R.success();
    }

    /**
    * 删除${table.comment!}
    *
    */
    @Operation(summary = "删除${table.comment!}")
    @DeleteMapping("/{id}")
    public R delete${entity}(@PathVariable("id") Long id) {
        ${entity?uncap_first}Service.removeById(id);
        return R.success();
    }

    /**
    * 获取${table.comment!}详情
    *
    */
    @Operation(summary = "获取${table.comment!}详情")
    @GetMapping("/{id}/info")
    public R<${entity}> get${entity}Info(@PathVariable("id") Long id) {
        ${entity} ${entity?uncap_first} = ${entity?uncap_first}Service.getById(id);
        return R.success(${entity?uncap_first});
    }
}
