<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};

import dwz.framework.core.business.BusinessObject;

/**
 * 关于${model.classDesc}的实体bean.
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public class ${vo} implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ${vo}() {

	}
	
	public ${vo}(<@allfield nm=model.attributes />) {
		 <#list model.attributes as attr>
		 this.${attr.name} = ${attr.name};
		 </#list>
	}
	 
 <#list model.attributes as attr>
 	//${attr.desc}
 	private ${attr.type} ${attr.name};
 	
 	/**
 	 * 获取${attr.desc}的属性值.
 	 */
 	public <@datatype nm="${attr.type}" key="${attr.iskey}"/> get${attr.name?cap_first}(){
 		return ${attr.name};
 	}
 	
 	/**
 	 * 设置${attr.desc}的属性值.
 	 */
 	public void set${attr.name?cap_first}(<@datatype nm="${attr.type}" key="${attr.iskey}"/> <@arg nm="${attr.name}"/>){
 		this.${attr.name} = <@arg nm="${attr.name}"/>;
 	}
 </#list>
}
