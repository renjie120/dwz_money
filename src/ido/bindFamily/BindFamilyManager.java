
package ido.bindFamily;
import java.util.Collection;
import java.util.Map; 
import java.io.File;
import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于投保用户的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface BindFamilyManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<BindFamily> searchBindFamily(Map<BindFamilySearchFields, Object> criterias,
			String orderField, int startIndex, int count);


	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchBindFamilyNum(Map<BindFamilySearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param bindfamily
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createBindFamily(BindFamily bindfamily) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param bindfamily
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateBindFamily(BindFamily bindfamily) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param sno
	 */
	@Transactional
	public void removeBindFamilys(String sno);

	/**
	 * 根据主键取值.
	 * @param sno
	 * @return
	 */
	public BindFamily getBindFamily(int sno);

	/**
         * 从excel中导入数据.
	 */
	@Transactional
	public void importFromExcel(File file);
}
