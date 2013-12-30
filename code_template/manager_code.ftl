<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};
<#assign beanname = "${model.className?cap_first}"/>
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于${model.classDesc}的业务操作操作接口.
 * @author ${author}
 * ${auth}
 * ${website}
 */ 
public interface ${model.className}Manager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<${beanname}> search${beanname}(Map<${beanname}SearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer search${beanname}Num(Map<${beanname}SearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param <@arg nm="${model.className}"/>
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void create${beanname}(${beanname} <@arg nm="${model.className}"/>) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param <@arg nm="${model.className}"/>
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void update${beanname}(${beanname} <@arg nm="${model.className}"/>) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param ${model.keyName}
	 */
	@Transactional
	public void remove${beanname}s(String ${model.keyName});

	/**
	 * 根据主键取值.
	 * @param ${model.keyName}
	 * @return
	 */
	public ${beanname} get${model.className}(${model.keyType} ${model.keyName});
}
