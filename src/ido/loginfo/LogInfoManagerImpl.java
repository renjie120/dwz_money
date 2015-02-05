
package ido.loginfo;
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
 * 关于操作日志的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class LogInfoManagerImpl extends AbstractBusinessObjectManager implements
		LogInfoManager {

	private LogInfoDao loginfodao = null;

	/**
	 * 构造函数.
	 */
	public LogInfoManagerImpl(LogInfoDao loginfodao) {
		this.loginfodao = loginfodao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchLogInfoNum(Map<LogInfoSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.loginfodao.countByQuery(hql,
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
				LogInfoVO vo = new LogInfoVO();
				this.loginfodao.insert(vo); 
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
	public Collection<LogInfo> searchLogInfo(Map<LogInfoSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<LogInfo> eaList = new ArrayList<LogInfo>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<LogInfoVO> voList = this.loginfodao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (LogInfoVO po : voList) {
			eaList.add(new  LogInfoImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<LogInfoSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( loginfo) "
						: "select  loginfo ").append("from LogInfoVO as loginfo ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<LogInfoSearchFields, Object> entry : criterias
					.entrySet()) {
				LogInfoSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERUSERNAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUserName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERTIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERIP:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operIp = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERURL:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUrl = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERBEFORE:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operBefore = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERAFTER:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operAfter = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case OPERDESC:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operDesc = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case OPERUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERUSERNAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUserName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERUSERNAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUserName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERTIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERTIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERIP_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operIp  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERIP_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operIp =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERURL_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUrl  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERURL_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operUrl =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERBEFORE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operBefore  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERBEFORE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operBefore =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERAFTER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operAfter  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERAFTER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operAfter =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case OPERDESC_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operDesc  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case OPERDESC_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  loginfo.operDesc =  ? "); 
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

		LogInfoOrderByFields orderBy = LogInfoOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = LogInfoOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by loginfo.sno");
			break;
			case OPERUSER:
				 sb.append(" order by loginfo.operUser");
			break;
			case OPERUSERNAME:
				 sb.append(" order by loginfo.operUserName");
			break;
			case OPERTIME:
				 sb.append(" order by loginfo.operTime");
			break;
			case OPERTYPE:
				 sb.append(" order by loginfo.operType");
			break;
			case OPERIP:
				 sb.append(" order by loginfo.operIp");
			break;
			case OPERURL:
				 sb.append(" order by loginfo.operUrl");
			break;
			case OPERBEFORE:
				 sb.append(" order by loginfo.operBefore");
			break;
			case OPERAFTER:
				 sb.append(" order by loginfo.operAfter");
			break;
			case OPERDESC:
				 sb.append(" order by loginfo.operDesc");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createLogInfo(LogInfo loginfo) throws ValidateFieldsException {
		LogInfoImpl loginfoImpl = (LogInfoImpl) loginfo;
		this.loginfodao.insert(loginfoImpl.getLogInfoVO());
	}

	public void removeLogInfos(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			LogInfoVO vo = this.loginfodao.findByPrimaryKey(Integer.parseInt(s));
			this.loginfodao.delete(vo);
		}
	}

	public void updateLogInfo(LogInfo loginfo) throws ValidateFieldsException {
		LogInfoImpl loginfoImpl = (LogInfoImpl) loginfo;

		LogInfoVO po = loginfoImpl.getLogInfoVO();
		this.loginfodao.update(po);
	}

	public LogInfo getLogInfo(int id) {
		Collection<LogInfoVO> loginfos = this.loginfodao.findRecordById(id);

		if (loginfos == null || loginfos.size() < 1)
			return null;

		LogInfoVO loginfo = loginfos.toArray(new LogInfoVO[loginfos.size()])[0];

		return new LogInfoImpl(loginfo);
	}

}
