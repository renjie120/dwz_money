
package money.diary;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于日志信息表的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class DiaryManagerImpl extends AbstractBusinessObjectManager implements
		DiaryManager {

	private DiaryDao diarydao = null;

	/**
	 * 构造函数.
	 */
	public DiaryManagerImpl(DiaryDao diarydao) {
		this.diarydao = diarydao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchDiaryNum(Map<DiarySearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = this.createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.diarydao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<Diary> searchDiary(Map<DiarySearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<Diary> eaList = new ArrayList<Diary>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = this.createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<DiaryVO> voList = this.diarydao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;

		for (DiaryVO po : voList) {
			eaList.add(new  DiaryImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<DiarySearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count(distinct diary) "
						: "select distinct diary ").append("from DiaryVO as diary ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<DiarySearchFields, Object> entry : criterias
					.entrySet()) {
				DiarySearchFields fd = entry.getKey();
				switch (fd) {
					case DIARYID:
						sb.append(count == 0 ? " where" : " and").append(
								"  diary.diaryId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DIARYCONTENT:
						sb.append(count == 0 ? " where" : " and").append(
								"  diary.diaryContent like ? ");
						argList.add("%"+entry.getValue()+"%");
						count++;
					break;
					case DIARYTIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  diary.diaryTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case DIARYTYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  diary.diaryType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		DiaryOrderByFields orderBy = DiaryOrderByFields.DIARYID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = DiaryOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case DIARYID:
				 sb.append(" order by diary.diaryId");
			break;
			case DIARYCONTENT:
				 sb.append(" order by diary.diaryContent");
			break;
			case DIARYTIME:
				 sb.append(" order by diary.diaryTime");
			break;
			case DIARYTYPE:
				 sb.append(" order by diary.diaryType");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createDiary(Diary diary) throws ValidateFieldsException {
		DiaryImpl diaryImpl = (DiaryImpl) diary;
		this.diarydao.insert(diaryImpl.getDiaryVO());
	}

	public void removeDiarys(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			DiaryVO vo = this.diarydao.findByPrimaryKey(Integer.parseInt(s));
			this.diarydao.delete(vo);
		}
	}

	public void updateDiary(Diary diary) throws ValidateFieldsException {
		DiaryImpl diaryImpl = (DiaryImpl) diary;

		DiaryVO po = diaryImpl.getDiaryVO();
		this.diarydao.update(po);
	}

	public Diary getDiary(int id) {
		Collection<DiaryVO> diarys = this.diarydao.findRecordById(id);

		if (diarys == null || diarys.size() < 1)
			return null;

		DiaryVO diary = diarys.toArray(new DiaryVO[diarys.size()])[0];

		return new DiaryImpl(diary);
	}

}
