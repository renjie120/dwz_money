<#include "/com.renjie120.codegenerate.common.ftl">package ${model.packageName};
<#assign beanname = "${model.className?cap_first}"/>
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public interface <@manager nm="${model.className}"/>  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<${beanname}> searchOrg(Map<${beanname}SearchFields, Object> criterias,
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
	public void remove${beanname}(${model.keyType} ${model.keyName});

	/**
	 * 根据主键取值.
	 * @param ${model.keyName}
	 * @return
	 */
	public ${beanname} getOrg(${model.keyType} ${model.keyName});
}
