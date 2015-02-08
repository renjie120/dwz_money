package common.struts2;

import java.io.File;
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
	 * 从数据库大对象获取文件.
	 * @param fileid
	 * @return
	 */
	public MyFile getFile(String fileid,HttpServletResponse response);
	
	/**
	 * 从文件系统得到文件.
	 * @param fileid 数据库中的文件主键 
	 * @param response
	 * @param rootFile 文件系统中存储文件的根目录
	 */
	public void getFileFromSystem(String fileid,HttpServletResponse response,File rootFile);
}
