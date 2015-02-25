
package ido.ConsumeInfo;
import java.util.Collection;
import java.util.Map; 
import java.io.File;
import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于消费记录的业务操作操作接口.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public interface ConsumeInfoManager  extends BusinessObjectManager {
	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<ConsumeInfo> searchConsumeInfo(Map<ConsumeInfoSearchFields, Object> criterias,
			String orderField, int startIndex, int count);


	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchConsumeInfoNum(Map<ConsumeInfoSearchFields, Object> criterias);

	/**
	 * 保存实体到数据库.
	 * @param consumeinfo
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void createConsumeInfo(ConsumeInfo consumeinfo) throws ValidateFieldsException;

	/**
	 * 更新操作.
	 * @param consumeinfo
	 * @throws ValidateFieldsException
	 */
	@Transactional
	public void updateConsumeInfo(ConsumeInfo consumeinfo) throws ValidateFieldsException;

	/**
	 * 删除操作
	 * @param sno
	 */
	@Transactional
	public void removeConsumeInfos(String sno);

	/**
	 * 根据主键取值.
	 * @param sno
	 * @return
	 */
	public ConsumeInfo getConsumeInfo(int sno);

	/**
         * 从excel中导入数据.
	 */
	@Transactional
	public void importFromExcel(File file);
}
