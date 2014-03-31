
package money.filemanage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于文件管理的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class FileManagerManagerImpl extends AbstractBusinessObjectManager implements
		FileManagerManager {

	private FileManagerDao filemanagerdao = null;

	/**
	 * 构造函数.
	 */
	public FileManagerManagerImpl(FileManagerDao filemanagerdao) {
		this.filemanagerdao = filemanagerdao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchFileManagerNum(Map<FileManagerSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.filemanagerdao.countByQuery(hql,
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
	public Collection<FileManager> searchFileManager(Map<FileManagerSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<FileManager> eaList = new ArrayList<FileManager>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<FileManagerVO> voList = this.filemanagerdao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (FileManagerVO po : voList) {
			eaList.add(new  FileManagerImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<FileManagerSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( filemanager) "
						: "select  filemanager ").append("from FileManagerVO as filemanager ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<FileManagerSearchFields, Object> entry : criterias
					.entrySet()) {
				FileManagerSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  filemanager.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FILEID:
						sb.append(count == 0 ? " where" : " and").append(
								"  filemanager.fileId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FILENAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  filemanager.fileName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FILELEN:
						sb.append(count == 0 ? " where" : " and").append(
								"  filemanager.fileLen = ? ");
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

		FileManagerOrderByFields orderBy = FileManagerOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = FileManagerOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by filemanager.sno");
			break;
			case FILEID:
				 sb.append(" order by filemanager.fileId");
			break;
			case FILENAME:
				 sb.append(" order by filemanager.fileName");
			break;
			case FILELEN:
				 sb.append(" order by filemanager.fileLen");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createFileManager(FileManager filemanager) throws ValidateFieldsException {
		FileManagerImpl filemanagerImpl = (FileManagerImpl) filemanager;
		this.filemanagerdao.insert(filemanagerImpl.getFileManagerVO());
	}

	public void removeFileManagers(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			FileManagerVO vo = this.filemanagerdao.findByPrimaryKey(Integer.parseInt(s));
			this.filemanagerdao.delete(vo);
		}
	}

	public void updateFileManager(FileManager filemanager) throws ValidateFieldsException {
		FileManagerImpl filemanagerImpl = (FileManagerImpl) filemanager;

		FileManagerVO po = filemanagerImpl.getFileManagerVO();
		this.filemanagerdao.update(po);
	}

	public FileManager getFileManager(int id) {
		Collection<FileManagerVO> filemanagers = this.filemanagerdao.findRecordById(id);

		if (filemanagers == null || filemanagers.size() < 1)
			return null;

		FileManagerVO filemanager = filemanagers.toArray(new FileManagerVO[filemanagers.size()])[0];

		return new FileManagerImpl(filemanager);
	}

}
