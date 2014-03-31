package common.struts2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import common.base.SpringContextUtil;

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
	public boolean saveFile(final MyFile file, final InputStream str) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");
		try { 
			jdbcTemplate.execute(new ConnectionCallback() {
				@Override
				public Object doInConnection(Connection conn)
						throws SQLException, DataAccessException {
					String sql = "insert into file_t (fileid, filename, filelen,content) values (?, ?, ?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					InputStream in;
					ps.setString(1, file.getFileId());
					ps.setString(2, file.getFileName());
					ps.setInt(3, file.getFileLen());
					in = new BufferedInputStream(str);
					ps.setBinaryStream(4, in, file.getFileLen());
					ps.executeUpdate();
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取文件.
	 * 
	 * @param fileid
	 * @return
	 */
	public MyFile getFile(final String fileid,
			final HttpServletResponse response) {
		JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringContextUtil
				.getBean("jdbcTemplate");

		MyFile f = jdbcTemplate.execute(new ConnectionCallback<MyFile>() {
			@Override
			public MyFile doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				String sql = "select fileid, filename, filelen,content from  file_t where  fileid=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = null;
				MyFile result = new MyFile();
				ps.setString(1, fileid); 
				rs = ps.executeQuery();
				ServletOutputStream os = null;

				try {
					if (rs.next()) {
						response.setContentType("application/octet-stream");
						response.addHeader("Content-Disposition",
								"attachment;filename=" + rs.getString(2));
						int filelen = rs.getInt(3);
						InputStream in = rs.getBinaryStream(4);

						os = response.getOutputStream();
						byte[] buff = new byte[1024];
						for (int i = 0; (i = in.read(buff)) > 0;) {
							os.write(buff, 0, i);
						}
						os.flush();
						in.close();
						result.setFileLen(filelen);
					}
					rs.close();
					// stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (os != null)
							os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return result;
			}
		}); 
		return f;
	}
}
