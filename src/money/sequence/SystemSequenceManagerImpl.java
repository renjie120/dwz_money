
package money.sequence;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.util.NPOIReader;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于系统序列号的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class SystemSequenceManagerImpl extends AbstractBusinessObjectManager implements
		SystemSequenceManager {

	private SystemSequenceDao systemsequencedao = null;

	/**
	 * 构造函数.
	 */
	public SystemSequenceManagerImpl(SystemSequenceDao systemsequencedao) {
		this.systemsequencedao = systemsequencedao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchSystemSequenceNum(Map<SystemSequenceSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.systemsequencedao.countByQuery(hql,
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
				SystemSequenceVO vo = new SystemSequenceVO();
				this.systemsequencedao.insert(vo); 
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
	public Collection<SystemSequence> searchSystemSequence(Map<SystemSequenceSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<SystemSequence> eaList = new ArrayList<SystemSequence>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<SystemSequenceVO> voList = this.systemsequencedao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (SystemSequenceVO po : voList) {
			eaList.add(new  SystemSequenceImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<SystemSequenceSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( systemsequence) "
						: "select  systemsequence ").append("from SystemSequenceVO as systemsequence ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<SystemSequenceSearchFields, Object> entry : criterias
					.entrySet()) {
				SystemSequenceSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SEQUENCECODE:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceCode = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SEQUENCENAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case SEQUENCECONTENT:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceContent = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case SEQUENCECODE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceCode  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SEQUENCECODE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceCode =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SEQUENCENAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SEQUENCENAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case SEQUENCECONTENT_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceContent  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case SEQUENCECONTENT_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  systemsequence.sequenceContent =  ? "); 
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

		SystemSequenceOrderByFields orderBy = SystemSequenceOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = SystemSequenceOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by systemsequence.sno");
			break;
			case SEQUENCECODE:
				 sb.append(" order by systemsequence.sequenceCode");
			break;
			case SEQUENCENAME:
				 sb.append(" order by systemsequence.sequenceName");
			break;
			case SEQUENCECONTENT:
				 sb.append(" order by systemsequence.sequenceContent");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createSystemSequence(SystemSequence systemsequence) throws ValidateFieldsException {
		SystemSequenceImpl systemsequenceImpl = (SystemSequenceImpl) systemsequence;
		this.systemsequencedao.insert(systemsequenceImpl.getSystemSequenceVO());
	}

	public void removeSystemSequences(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			SystemSequenceVO vo = this.systemsequencedao.findByPrimaryKey(Integer.parseInt(s));
			this.systemsequencedao.delete(vo);
		}
	}

	public void updateSystemSequence(SystemSequence systemsequence) throws ValidateFieldsException {
		SystemSequenceImpl systemsequenceImpl = (SystemSequenceImpl) systemsequence;

		SystemSequenceVO po = systemsequenceImpl.getSystemSequenceVO();
		this.systemsequencedao.update(po);
	}

	public SystemSequence getSystemSequence(int id) {
		Collection<SystemSequenceVO> systemsequences = this.systemsequencedao.findRecordById(id);

		if (systemsequences == null || systemsequences.size() < 1)
			return null;

		SystemSequenceVO systemsequence = systemsequences.toArray(new SystemSequenceVO[systemsequences.size()])[0];

		return new SystemSequenceImpl(systemsequence);
	}

	@Override
	public Integer getSequence(String sequenceCode, boolean autoAdd)
			throws ValidateFieldsException {
		Map<SystemSequenceSearchFields, Object> criterias = new HashMap<SystemSequenceSearchFields, Object>();
		criterias.put(SystemSequenceSearchFields.SEQUENCECODE, sequenceCode);
		
		Collection<SystemSequence> result = searchSystemSequence(criterias,null,0,10);
		if(result!=null&&result.size()>0){
			SystemSequence seq =result.toArray(new SystemSequence[result.size()])[0];
			if(autoAdd){  
				SystemSequenceImpl systemsequenceImpl = new SystemSequenceImpl( seq.getSno() , sequenceCode , seq.getSequenceName() , seq.getSequenceContent()+1 );
				updateSystemSequence(systemsequenceImpl);
			}
			return seq.getSequenceContent();
		}
		else{
			return -1;
		}
	}

}
