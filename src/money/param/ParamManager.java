
package money.param;
import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于参数的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ParamManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<Param> searchParam(Map<ParamSearchFields, Object> criterias,
			String orderField, int startIndex, int count);

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchParamNum(Map<ParamSearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param param
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createParam(Param param) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param param
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateParam(Param param) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param paramId
	 */
	@Transactional
	public void removeParams(String paramId);

	/**
	 * 根据主键取值.
	 * @param paramId
	 * @return
	 */
	public Param getParam(int paramId);
}
