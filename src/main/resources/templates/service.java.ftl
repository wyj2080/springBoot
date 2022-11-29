package ${package.Service};

import java.io.Serializable;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;
import java.util.Map;

/**
* <p>
* ${table.comment!} 服务类
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    /**
    * 分页查询单据
    */
    List<${entity}> findPage(Page<${entity}> page, Map<String, Object> map);
}
</#if>