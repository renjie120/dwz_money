<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
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
	
	public ${vo}(<@allfieldnotkey nm=model.attributes />) {
		 <#list model.attributes as attr>
		 <#if '${attr.name}'!='${model.keyName}'>
			 this.${attr.name} = ${attr.name};
		 </#if>
		 </#list>
	}
	 
  <@allGetAndSet nm=model.attributes />
}
