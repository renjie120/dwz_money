
package money.bugList;
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
 * 关于问题清单的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class BugListManagerImpl extends AbstractBusinessObjectManager implements
		BugListManager {

	private BugListDao buglistdao = null;

	/**
	 * 构造函数.
	 */
	public BugListManagerImpl(BugListDao buglistdao) {
		this.buglistdao = buglistdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchBugListNum(Map<BugListSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.buglistdao.countByQuery(hql,
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
				BugListVO vo = new BugListVO();
				//导入问题类型
				String bugTypeStr = contents[i][0];
				vo.setBugType(bugTypeStr);
				
				//导入待解决时间
				String needTimeStr = contents[i][1];
				vo.setNeedTime(needTimeStr);
				
				//导入解决时间
				String consoleTileStr = contents[i][2];
				vo.setConsoleTile(consoleTileStr);
				
				//导入备注
				String remarkStr = contents[i][3];
				vo.setRemark(remarkStr);
				
				this.buglistdao.insert(vo); 
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
	public Collection<BugList> searchBugList(Map<BugListSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<BugList> eaList = new ArrayList<BugList>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<BugListVO> voList = this.buglistdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_bugtype = allSelect
				.getParamsByType(AllSelectContants.BUGTYPE.getName());
		
		for (BugListVO po : voList) {
			po.setBugType(select_bugtype.getName("" + po.getBugType())); 
			eaList.add(new  BugListImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<BugListSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( buglist) "
						: "select  buglist ").append("from BugListVO as buglist ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<BugListSearchFields, Object> entry : criterias
					.entrySet()) {
				BugListSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BUGTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.bugType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BUGDESC:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.bugDesc = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case NEEDTIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.needTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONSOLEPEOPLE:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.consolePeople = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CONSOLETILE:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.consoleTile = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case REMARK:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.remark = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case BUGTYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.bugType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case BUGTYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.bugType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case BUGDESC_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.bugDesc  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case BUGDESC_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.bugDesc =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.createTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case NEEDTIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.needTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case NEEDTIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.needTime =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CONSOLEPEOPLE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.consolePeople  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CONSOLEPEOPLE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.consolePeople =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CONSOLETILE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.consoleTile  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CONSOLETILE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.consoleTile =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case REMARK_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.remark  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case REMARK_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  buglist.remark =  ? "); 
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

		BugListOrderByFields orderBy = BugListOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = BugListOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by buglist.sno");
			break;
			case BUGTYPE:
				 sb.append(" order by buglist.bugType");
			break;
			case BUGDESC:
				 sb.append(" order by buglist.bugDesc");
			break;
			case CREATEUSER:
				 sb.append(" order by buglist.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by buglist.createTime");
			break;
			case NEEDTIME:
				 sb.append(" order by buglist.needTime");
			break;
			case CONSOLEPEOPLE:
				 sb.append(" order by buglist.consolePeople");
			break;
			case CONSOLETILE:
				 sb.append(" order by buglist.consoleTile");
			break;
			case REMARK:
				 sb.append(" order by buglist.remark");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createBugList(BugList buglist) throws ValidateFieldsException {
		BugListImpl buglistImpl = (BugListImpl) buglist;
		this.buglistdao.insert(buglistImpl.getBugListVO());
	}

	public void removeBugLists(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			BugListVO vo = this.buglistdao.findByPrimaryKey(Integer.parseInt(s));
			this.buglistdao.delete(vo);
		}
	}

	public void updateBugList(BugList buglist) throws ValidateFieldsException {
		BugListImpl buglistImpl = (BugListImpl) buglist;

		BugListVO po = buglistImpl.getBugListVO();
		this.buglistdao.update(po);
	}

	public BugList getBugList(int id) {
		Collection<BugListVO> buglists = this.buglistdao.findRecordById(id);

		if (buglists == null || buglists.size() < 1)
			return null;

		BugListVO buglist = buglists.toArray(new BugListVO[buglists.size()])[0];

		return new BugListImpl(buglist);
	}

}
