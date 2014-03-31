package com.renjie120.blobtest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

import common.MyJdbcTool;
import common.base.SpringContextUtil;
import common.struts2.MyFile;

public class FileTest extends AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	public void testSaveFile() {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		jdbcDaoTest.getJdbcTemplate().execute(new ConnectionCallback() {

			@Override
			public Object doInConnection(Connection conn) throws SQLException,
					DataAccessException {
				String sql = "insert into file_t (fileid, filename, filelen,content) values (?, ?, ?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				InputStream in;
				try {
					ps.setString(1, System.currentTimeMillis() + "");
					ps.setString(2, "test");
					// 设置二进制参数
					File file = new File(
							"D:\\123_html_79029dee.png");
					ps.setInt(3, (int) file.length());
					in = new BufferedInputStream(new FileInputStream(file));
					ps.setBinaryStream(4, in, (int) file.length());
					ps.executeUpdate();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}
		});
	}

	public void atestGetFile() {
		MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
				.getBean("jdbcTool");
		MyFile f = jdbcDaoTest.getJdbcTemplate().execute(
				new ConnectionCallback<MyFile>() {
					@Override
					public MyFile doInConnection(Connection conn)
							throws SQLException, DataAccessException {
						String sql = "select fileid, filename, filelen,content from  file_t where  fileid=?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ResultSet rs = null;
						OutputStream out = null;
						File file;
						MyFile result = new MyFile();
						ps.setString(1, "1396231657312");
						// ps.executeUpdate();
						rs = ps.executeQuery();
						System.out.println();
						try {
							if (rs.next()) {
								String fileId = rs.getString(1);
								String filename = rs.getString(2);
								int filelen = rs.getInt(3);
								InputStream in = rs.getBinaryStream(4); 
								file = new File("D:\\" + filename); 
								if(!file.exists())
									file.createNewFile();  
								out = new BufferedOutputStream(
										new FileOutputStream(file)); 
								byte[] buff = new byte[1024];
								for (int i = 0; (i = in.read(buff)) > 0;) {
									out.write(buff, 0, i);
								}
								out.flush();
								out.close();
								in.close();
								result.setContent(file);
								result.setFileId(fileId);
								result.setFileName(filename);
								result.setFileLen(filelen);
							}
							rs.close();
							// stmt.close();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return result;
					}
				}); 
	}
}
