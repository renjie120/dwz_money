
package money.filemanage;

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
 * 关于文件管理的Action操作类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class FileManagerAction extends BaseAction {
	/**
	 *  序列化对象.
	 */
	private static final long serialVersionUID = 1L;
	//业务接口对象.
	FileManagerManager pMgr = bf.getManager(BeanManagerKey.filemanagerManager);
	//业务实体对象
	private FileManager vo;
	//当前页数
	private int page = 1;
	//每页显示数量
	private int pageSize = 50;
	//总页数
	private long count;
	
	public String beforeAdd() {
		return "detail";
	}

	public String doAdd() {
		try {
			FileManagerImpl filemanagerImpl = new FileManagerImpl(fileId ,fileName ,fileLen );
			pMgr.createFileManager(filemanagerImpl);
		} catch (ValidateFieldsException e) {
			log.error(e);
			return ajaxForwardError(e.getLocalizedMessage());
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	}

	public String doDelete() {
		String ids = request.getParameter("ids");
		pMgr.removeFileManagers(ids);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	public String beforeUpdate() {
		vo = pMgr.getFileManager(sno);
		return "editdetail";
	}

	public String doUpdate() {
		try {
			FileManagerImpl filemanagerImpl = new FileManagerImpl( sno , fileId , fileName , fileLen );
			pMgr.updateFileManager(filemanagerImpl);
		} catch (ValidateFieldsException e) {
			e.printStackTrace();
		}
		writeToPage(response,getText("msg.operation.success"));
		return null;
	} 
	
	public enum ExportFiled {
		  SNO("流水号"),  FILEID("文件id"),  FILENAME("文件名"),  FILELEN("文件长度");
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
		response.addHeader("Content-Disposition","attachment;filename=FileManagerList.xls");

		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		Map<FileManagerSearchFields, Object> criterias = getCriterias();

		Collection<FileManager> filemanagerList = pMgr.searchFileManager(criterias, realOrderField(),
				startIndex, numPerPage);

		XlsExport e = new XlsExport();
		int rowIndex = 0;

		e.createRow(rowIndex++);
		for (ExportFiled filed : ExportFiled.values()) {
			e.setCell(filed.ordinal(), filed.getName());
		}

		for (FileManager filemanager : filemanagerList) {
			e.createRow(rowIndex++);

			for (ExportFiled filed : ExportFiled.values()) {
				switch (filed) {
					case SNO:
						 e.setCell(filed.ordinal(), filemanager.getSno()); 
					break;
					case FILEID:
						 e.setCell(filed.ordinal(), filemanager.getFileId()); 
					break;
					case FILENAME:
						 e.setCell(filed.ordinal(), filemanager.getFileName()); 
					break;
					case FILELEN:
						 e.setCell(filed.ordinal(), filemanager.getFileLen()); 
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
		Map<FileManagerSearchFields, Object> criterias = getCriterias();

		Collection<FileManager> moneyList = pMgr.searchFileManager(criterias, realOrderField(),
				startIndex, numPerPage);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("numPerPage", numPerPage);
		int count = pMgr.searchFileManagerNum(criterias);
		request.setAttribute("totalCount", count);
		ActionContext.getContext().put("list", moneyList);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount",count);
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

	private Map<FileManagerSearchFields, Object> getCriterias() {
		Map<FileManagerSearchFields, Object> criterias = new HashMap<FileManagerSearchFields, Object>();
			if (getFileName()!=null&&!"".equals(getFileName()))
			 	criterias.put(FileManagerSearchFields.FILENAME,  getFileName());
		return criterias;
	}

	public FileManager getVo() {
		return vo;
	}

	public void setVo(FileManager vo) {
		this.vo = vo;
	} 
  
	private Integer sno; 
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public Integer getSno(){
 		return sno;
 	}
 	
 	/**
 	 * 设置流水号的属性值.
 	 */
 	public void setSno(Integer sno){
 		this.sno = sno;
 	}
	private String fileId; 
 	/**
 	 * 获取文件id的属性值.
 	 */
 	public String getFileId(){
 		return fileId;
 	}
 	
 	/**
 	 * 设置文件id的属性值.
 	 */
 	public void setFileId(String fileid){
 		this.fileId = fileid;
 	}
	private String fileName; 
 	/**
 	 * 获取文件名的属性值.
 	 */
 	public String getFileName(){
 		return fileName;
 	}
 	
 	/**
 	 * 设置文件名的属性值.
 	 */
 	public void setFileName(String filename){
 		this.fileName = filename;
 	}
	private int fileLen; 
 	/**
 	 * 获取文件长度的属性值.
 	 */
 	public int getFileLen(){
 		return fileLen;
 	}
 	
 	/**
 	 * 设置文件长度的属性值.
 	 */
 	public void setFileLen(int filelen){
 		this.fileLen = filelen;
 	}
}
