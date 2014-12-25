<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};

/**
 * 关于${model.classDesc}的查询排序枚举类.
 * @author ${author}
 * ${auth}
 * ${website}
 */
public enum ${model.className?cap_first}OrderByFields { 
 <@allorderfield nm=model.attributes />

}
