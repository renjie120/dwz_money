
package money.uploadFile;
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
import common.cache.CacheUtil;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于上传文件的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class UploadFileManagerImpl extends AbstractBusinessObjectManager implements
		UploadFileManager {

	private UploadFileDao uploadfiledao = null;

	/**
	 * 构造函数.
	 */
	public UploadFileManagerImpl(UploadFileDao uploadfiledao) {
		this.uploadfiledao = uploadfiledao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchUploadFileNum(Map<UploadFileSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.uploadfiledao.countByQuery(hql,
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
				UploadFileVO vo = new UploadFileVO();
				this.uploadfiledao.insert(vo); 
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
	public Collection<UploadFile> searchUploadFile(Map<UploadFileSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<UploadFile> eaList = new ArrayList<UploadFile>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<UploadFileVO> voList = this.uploadfiledao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		AllSelect allSelect = (AllSelect) SpringContextUtil
				.getBean(BeanManagerKey.allSelectManager.toString());
		ParamSelect select_file_type = allSelect
				.getParamsByType(AllSelectContants.FILE_TYPE.getName()); 
		ParamSelect select_yesorno = allSelect
				.getParamsByType(AllSelectContants.YESORNO.getName());
		
		for (UploadFileVO po : voList) {
			po.setFileType(select_file_type.getName("" + po.getFileType())); 
			po.setIsExist(select_yesorno.getName("" + po.getIsExist())); 
			po.setCreateUserName(CacheUtil.getSystemUserName(""+po.getCreateUser()));
			eaList.add(new  UploadFileImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<UploadFileSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( uploadfile) "
						: "select  uploadfile ").append("from UploadFileVO as uploadfile ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<UploadFileSearchFields, Object> entry : criterias
					.entrySet()) {
				UploadFileSearchFields fd = entry.getKey();
				switch (fd) {
					case SNO:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.sno = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case BUSINESSID:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.businessId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FILETYPE:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileType = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ISEXIST:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.isExist = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FILENAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case REALFILENAME:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.realFileName = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case FILESIZE:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileSize = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATEUSER:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.createUser = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case CREATETIME:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.createTime = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				//下面拼接高级查询条件
					case BUSINESSID_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.businessId  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case BUSINESSID_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.businessId =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case FILETYPE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileType  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case FILETYPE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileType =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case ISEXIST_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.isExist  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case ISEXIST_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.isExist =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case FILENAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case FILENAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case REALFILENAME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.realFileName  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case REALFILENAME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.realFileName =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case FILESIZE_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileSize  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case FILESIZE_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.fileSize =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATEUSER_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.createUser  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATEUSER_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.createUser =  ? "); 
						argList.add( entry.getValue() ); 
						count++;
					break;
					case CREATETIME_COM_NOT_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.createTime  !=  ? "); 
						argList.add(entry.getValue()); 
						count++;
					break;
					case CREATETIME_COM_EQUALS:
						sb.append(count == 0 ? " where" : " and").append(
								"  uploadfile.createTime =  ? "); 
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

		UploadFileOrderByFields orderBy = UploadFileOrderByFields.SNO_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = UploadFileOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case SNO:
				 sb.append(" order by uploadfile.sno");
			break;
			case BUSINESSID:
				 sb.append(" order by uploadfile.businessId");
			break;
			case FILETYPE:
				 sb.append(" order by uploadfile.fileType");
			break;
			case ISEXIST:
				 sb.append(" order by uploadfile.isExist");
			break;
			case FILENAME:
				 sb.append(" order by uploadfile.fileName");
			break;
			case REALFILENAME:
				 sb.append(" order by uploadfile.realFileName");
			break;
			case FILESIZE:
				 sb.append(" order by uploadfile.fileSize");
			break;
			case CREATEUSER:
				 sb.append(" order by uploadfile.createUser");
			break;
			case CREATETIME:
				 sb.append(" order by uploadfile.createTime");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createUploadFile(UploadFile uploadfile) throws ValidateFieldsException {
		UploadFileImpl uploadfileImpl = (UploadFileImpl) uploadfile;
		this.uploadfiledao.insert(uploadfileImpl.getUploadFileVO());
	}

	public void removeUploadFiles(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			UploadFileVO vo = this.uploadfiledao.findByPrimaryKey(Integer.parseInt(s));
			this.uploadfiledao.delete(vo);
		}
	}

	public void updateUploadFile(UploadFile uploadfile) throws ValidateFieldsException {
		UploadFileImpl uploadfileImpl = (UploadFileImpl) uploadfile;

		UploadFileVO po = uploadfileImpl.getUploadFileVO();
		this.uploadfiledao.update(po);
	}

	public UploadFile getUploadFile(int id) {
		Collection<UploadFileVO> uploadfiles = this.uploadfiledao.findRecordById(id);

		if (uploadfiles == null || uploadfiles.size() < 1)
			return null;

		UploadFileVO uploadfile = uploadfiles.toArray(new UploadFileVO[uploadfiles.size()])[0];

		return new UploadFileImpl(uploadfile);
	}

}
