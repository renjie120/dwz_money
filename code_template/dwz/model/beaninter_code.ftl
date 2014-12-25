<#include "/com.renjie120.codegenerate.common.ftl">
package ${model.packageName};

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于${model.classDesc}的业务类接口
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public interface ${model.className} extends BusinessObject {  
 <#list model.attributes as attr>
 	/**
 	 * 获取${attr.desc}的属性值.
 	 */
 	public  <@datatype nm="${attr.type}" key="${attr.iskey}"/>   get${attr.name?cap_first}();
 </#list>
}
