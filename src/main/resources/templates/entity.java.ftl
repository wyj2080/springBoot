package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if swagger>
import io.swagger.v3.oas.annotations.media.Schema;
</#if>
import lombok.Data;
<#if entityLombokModel>
import lombok.Getter;
import lombok.Setter;
<#if chainModel>
import lombok.experimental.Accessors;
</#if>
</#if>

/**
* <p>
* ${table.comment!}
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
<#if entityLombokModel>
@Getter
@Setter
<#if chainModel>
@Accessors(chain = true)
</#if>
</#if>
<#if table.convert>
@TableName("${schemaName}${table.name}")
</#if>
<#if swagger>
@Schema(name = "${entity}", description = "$!{table.comment}")
</#if>
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity} implements Serializable {
<#else>
public class ${entity} {
</#if>
<#if entitySerialVersionUID>

    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>
    <#if field.keyFlag>
    <#assign keyPropertyName="${field.propertyName}"/>
    </#if>

    <#if field.comment!?length gt 0>
    /**
    * ${field.comment}
    */
    <#if swagger>
    @Schema(description = "${field.comment}")
    </#if>
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
    <#if field.keyIdentityFlag>
    @TableId(value = "${field.annotationColumnName}", type = IdType.AUTO)
    <#elseif idType??>
    @TableId(value = "${field.annotationColumnName}", type = IdType.${idType})
    <#elseif field.convert>
    @TableId("${field.annotationColumnName}")
    </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
    <#if field.convert>
    @TableField(value = "${field.annotationColumnName}", fill = FieldFill.${field.fill})
    <#else>
    @TableField(fill = FieldFill.${field.fill})
    </#if>
    <#elseif field.convert>
    @TableField("${field.annotationColumnName}")
    </#if>
    <#-- 乐观锁注解 -->
    <#if field.versionField>
    @Version
    </#if>
    <#-- 逻辑删除注解 -->
    <#if field.logicDeleteField>
    @TableLogic
    </#if>
    private ${field.propertyType} ${field.propertyName};
    </#list>
<#------------  END 字段循环遍历  ---------->
}