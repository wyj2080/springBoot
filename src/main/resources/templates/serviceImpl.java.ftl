package ${package.ServiceImpl};

import java.io.Serializable;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
* <p>
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**
    * 分页查询单据
    */
    @Override
    public List<${entity}> findPage(Page<${entity}> page, Map<String, Object> map){
        //${entity} query = criteriaQuery.mapToBean(${entity}.class);
        //criteriaQuery.lambda()
        // .likeRight(ObjectUtils.isNotEmpty(query.getName()), ${entity}::getName, query.getName());
        //MybatisUtil.setSearchkey(criteriaQuery, "xxCode", "xxName");
        //if (StrUtil.isNotBlank(search.getKeyword())) {
        //	queryWrapper.and(i -> i
        //		.or().like(SysApi::getCode, search.getKeyword())
        //		.or().like(SysApi::getPath, search.getKeyword()));
        //}

        //criteriaQuery.orderByDesc("create_time");
        return null;
    }
}
