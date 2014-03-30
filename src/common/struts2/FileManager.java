package common.struts2;

import dwz.framework.core.business.BusinessObjectManager;

/**
 * 文件操作接口.
 * @author Administrator
 *
 */
public interface FileManager extends BusinessObjectManager {

	/**
	 * 保存文件
	 * @param file
	 */
	public void saveFile(MyFile file);

	/**
	 * 获取文件.
	 * @param fileid
	 * @return
	 */
	public MyFile getFile(String fileid);
}
