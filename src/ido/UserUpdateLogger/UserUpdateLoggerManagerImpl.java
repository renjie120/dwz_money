
package ido.UserUpdateLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.io.File;
import java.util.Map;

import common.util.NPOIReader;
import common.base.ParamSelect;
import common.base.SpringContextUtil;
import common.util.CommonUtil;
import common.util.DateTool;
import common.util.NPOIReader; 
import common.base.AllSelect;
import common.base.AllSelectContants;
import common.cache.Cache;
import common.cache.CacheManager;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于用户状态修改记录的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UserUpdateLoggerManagerImpl extends AbstractBusinessObjectManager implements
		UserUpdateLoggerManager {

	private UserUpdateLoggerDao userupdateloggerdao = null;

	/**
	 * 构造函数.
	 */
	public UserUpdateLoggerManagerImpl(UserUpdateLoggerDao userupdateloggerdao) {
		this.userupdateloggerdao = userupdateloggerdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchUserUpdateLoggerNum(Map<UserUpdateLoggerSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.userupdateloggerdao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}


	public void importFromExcel(File file) {
		NPOIReader excel = null;
		try {
			excel = new NPOIReader(file);
			int index = excel.getSheetNames().indexOf("Sheet0");
			String[][] contents = excel.read(index, true, true);
			for (int i = 1; i < contents.length; i++) {
				UserUpdateLoggerVO vo = new UserUpdateLoggerVO();
				//导入用户 
				String userIdStr = contents[i][0];
				vo.setUserId(userIdStr);
				
				//导入用户状态
				String stateStr = contents[i][1];
				vo.setState(stateStr);
				
				//导入操作原因
				String logDetailStr = contents[i][2];
				vo.setLogDetail(logDetailStr);
				
				this.userupdateloggerdao.insert(vo); 
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<UserUpdateLogger> searchUserUpdateLogger(Map<UserUpdateLoggerSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<UserUpdateLogger> eaList = new ArrayList<UserUpdateLogger>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<UserUpdateLoggerVO> voList = this.userupdateloggerdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_toubaouser_status = allSelect
				.getParamsByType(AllSelectContants.TOUBAOUSER_STATUS.getName());
		
		for (UserUpdateLoggerVO po : voList) {
			po.setState(select_toubaouser_status.getName("" + po.getState())); 
			eaList.add(new  UserUpdateLoggerImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<UserUpdateLoggerSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( userupdatelogger) "
						: "select  userupdatelogger ").append("from UserUpdateLoggerVO as userupdatelogger ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<UserUpdateLoggerSearchFields, Object> entry : criterias
					.entrySet()) {
				UserUpdateLoggerSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case USERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.userId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case STATE:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.state = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case LOGDETAIL:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.logDetail = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ARG1:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.arg1 = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case USERID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.userId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case USERID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.userId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case STATE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.state  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case STATE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.state =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case LOGDETAIL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.logDetail  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case LOGDETAIL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.logDetail =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ARG1_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.arg1  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ARG1_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.arg1 =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  userupdatelogger.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		UserUpdateLoggerOrderByFields orderBy = UserUpdateLoggerOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = UserUpdateLoggerOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by userupdatelogger.sno");
			break;
			case USERID:
				 sb.append(" order by userupdatelogger.userId");
			break;
			case STATE:
				 sb.append(" order by userupdatelogger.state");
			break;
			case LOGDETAIL:
				 sb.append(" order by userupdatelogger.logDetail");
			break;
			case ARG1:
				 sb.append(" order by userupdatelogger.arg1");
			break;
			case CREATEUSER:
				 sb.append(" order by userupdatelogger.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by userupdatelogger.createTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createUserUpdateLogger(UserUpdateLogger userupdatelogger) throws ValidateFieldsException {
		UserUpdateLoggerImpl userupdateloggerImpl = (UserUpdateLoggerImpl) userupdatelogger;
		this.userupdateloggerdao.insert(userupdateloggerImpl.getUserUpdateLoggerVO());
	}

	public void removeUserUpdateLoggers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			UserUpdateLoggerVO vo = this.userupdateloggerdao.findByPrimaryKey(Integer.parseInt(s));
			this.userupdateloggerdao.delete(vo);
		}
	}

	public void updateUserUpdateLogger(UserUpdateLogger userupdatelogger) throws ValidateFieldsException {
		UserUpdateLoggerImpl userupdateloggerImpl = (UserUpdateLoggerImpl) userupdatelogger;

		UserUpdateLoggerVO po = userupdateloggerImpl.getUserUpdateLoggerVO();
		this.userupdateloggerdao.update(po);
	}

	public UserUpdateLogger getUserUpdateLogger(int id) {
		Collection<UserUpdateLoggerVO> userupdateloggers = this.userupdateloggerdao.findRecordById(id);

		if (userupdateloggers == null || userupdateloggers.size() < 1)
			return null;

		UserUpdateLoggerVO userupdatelogger = userupdateloggers.toArray(new UserUpdateLoggerVO[userupdateloggers.size()])[0];

		return new UserUpdateLoggerImpl(userupdatelogger);
	}

}
