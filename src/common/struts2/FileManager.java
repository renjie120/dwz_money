package common.struts2;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

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
	public boolean saveFile(MyFile file,InputStream str);

	/**
	 * 获取文件.
	 * @param fileid
	 * @return
	 */
	public MyFile getFile(String fileid,HttpServletResponse response);
}
