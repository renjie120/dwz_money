package common.struts2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Clob;

import org.hibernate.Hibernate;

import dwz.framework.core.business.AbstractBusinessObjectManager;

/**
 * 文件操作接口.
 * 
 * @author Administrator
 * 
 */
public class FileManagerImpl extends AbstractBusinessObjectManager implements
		FileManager {
	private FileDao fileDao = null;

	public FileManagerImpl(FileDao fileDao) {
		this.fileDao = fileDao;
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 */
	public void saveFile(MyFile file) {
		FileReader fr;
		try {
			fr = new FileReader(file.getContent());
			BufferedReader br = new BufferedReader(fr);
			Clob info = Hibernate.createClob(br, (int) file.getContent()
					.length());

			fileDao.commonExecuteSqlSaveFile(
					"insert into file_t (fileid,filename,filelen,content) values(?,?,?,?) ",
					new Object[] { file.getFileId(), file.getFileName(),
							file.getFileLen(), info });
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取文件.
	 * 
	 * @param fileid
	 * @return
	 */
	public MyFile getFile(String fileid) {
		MyFile file = this.fileDao
				.commonSqlGetFile(
						"select fileid,filename,filelen,content from  file_t where fileid=? ,",
						new Object[] { fileid });
		return null;
	}
}
