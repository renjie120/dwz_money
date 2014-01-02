
package money.paramtype;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于参数类型的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ParamTypeManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<ParamType> searchParamType(Map<ParamTypeSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchParamTypeNum(Map<ParamTypeSearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param paramtype
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createParamType(ParamType paramtype) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param paramtype
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateParamType(ParamType paramtype) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param paramTypeId
	 */
	@Transactional
	public void removeParamTypes(String paramTypeId);

	/**
	 * 根据主键取值.
	 * @param paramTypeId
	 * @return
	 */
	public ParamType getParamType(int paramTypeId);
}
