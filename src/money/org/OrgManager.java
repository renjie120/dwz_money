package money.org;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

public interface OrgManager extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<Org> searchOrg(Map<OrgSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchOrgNum(Map<OrgSearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param org
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createOrg(Org org) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param org
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateOrg(Org org) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param orgId
	 */
	@Transactional
	public void removeOrg(String orgId);

	/**
	 * 根据主键取值.
	 * @param id
	 * @return
	 */
	public Org getOrg(Integer id);
}
