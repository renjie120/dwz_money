package money.diary;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import com.opensymphony.xwork2.ActionContext;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.exception.ValidateFieldsException;
import dwz.framework.utils.excel.XlsExport;
import dwz.present.BaseAction;

/**
 * 关于日志信息表的Action操作类.
 * 
 * @author www(水清) 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名. http://www.iteye.com
 */
public class DiaryAction extends BaseAction {
	/**
	 * 序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	// 业务接口对象.
	DiaryManager pMgr = bf.getManager(BeanManagerKey.diaryManager);
	// 业务实体对象
	private Diary diaryVo;
	// 当前页数
	private int page = 1;
	// 每页显示数量
	private int pageSize = 50;
	// 总页数
	private long count;

	public String beforeAdd() {
		return "detail";
	}

	private Map<DiarySearchFields, Object> getCriterias() {
		Map<DiarySearchFields, Object> criterias = new HashMap<DiarySearchFields, Object>();
		if (getDiaryContent() != null && !"".equals(getDiaryContent()))
			criterias.put(DiarySearchFields.DIARYCONTENT, getDiaryContent());
		if (getDiaryType() != null && !"".equals(getDiaryType())&& !"-2".equals(getDiaryType()))
			criterias.put(DiarySearchFields.DIARYTYPE, getDiaryType());
		return criterias;
	}

	public String doAdd() {
		try {
			DiaryImpl diaryImpl = new DiaryImpl(diaryContent, diaryTime,
					diaryType);
			pMgr.createDiary(diaryImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeDiarys(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		diaryVo = pMgr.getDiary(diaryId);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			DiaryImpl diaryImpl = new DiaryImpl(diaryId, diaryContent,
					diaryTime, diaryType);
			pMgr.updateDiary(diaryImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response, getText("msg.operation.success"));
		return null;
	}

	public enum ExportFiled {
		DIARYID("日志id"), DIARYCONTENT("日志内容"), DIARYTIME("开始时间"), DIARYTYPE(
				"日志类型");
		private String str;

		ExportFiled(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}
	}

	public String beforeQuery() {
		return "query";
	}

	public String export() {
		response.setContentType("Application/excel");
		response.addHeader("Content-Disposition",
				"attachment;filename=DiaryList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<DiarySearchFields, Object> criterias = getCriterias();

		Collection<Diary> diaryList = pMgr.searchDiary(criterias,
				realOrderField(), startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (Diary diary : diaryList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
				case DIARYID:
					e.setCell(filed.ordinal(), diary.getDiaryId());
					break;
				case DIARYCONTENT:
					e.setCell(filed.ordinal(), diary.getDiaryContent());
					break;
				case DIARYTIME:
					e.setCell(filed.ordinal(), diary.getDiaryTime());
					break;
				case DIARYTYPE:
					e.setCell(filed.ordinal(), diary.getDiaryType());
					break;
				default:
					break;
				}

			}
		}

		e.exportXls(response);
		return null;
	}

	public String query() {
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<DiarySearchFields, Object> criterias = getCriterias();

		Collection<Diary> moneyList = pMgr.searchDiary(criterias,
				realOrderField(), startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchDiaryNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		request.setAttribute("diaryType", diaryType);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
		return "list";
	}

	public String reQuery() {
		return "list";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
 
	public Diary getDiaryVo() {
		return diaryVo;
	}

	public void setDiaryVo(Diary diaryVo) {
		this.diaryVo = diaryVo;
	}

	private Integer diaryId;

	/**
	 * 获取日志id的属性值.
	 */
	public Integer getDiaryId() {
		return diaryId;
	}

	/**
	 * 设置日志id的属性值.
	 */
	public void setDiaryId(Integer diaryid) {
		this.diaryId = diaryid;
	}

	private String diaryContent;

	/**
	 * 获取日志内容的属性值.
	 */
	public String getDiaryContent() {
		return diaryContent;
	}

	/**
	 * 设置日志内容的属性值.
	 */
	public void setDiaryContent(String diarycontent) {
		this.diaryContent = diarycontent;
	}

	private Date diaryTime;

	/**
	 * 获取开始时间的属性值.
	 */
	public Date getDiaryTime() {
		return diaryTime;
	}

	/**
	 * 设置开始时间的属性值.
	 */
	public void setDiaryTime(Date diarytime) {
		this.diaryTime = diarytime;
	}

	private String diaryType;

	/**
	 * 获取日志类型的属性值.
	 */
	public String getDiaryType() {
		return diaryType;
	}

	/**
	 * 设置日志类型的属性值.
	 */
	public void setDiaryType(String diarytype) {
		this.diaryType = diarytype;
	}
}
