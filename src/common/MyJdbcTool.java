package common;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowCountCallbackHandler;
class haha extends Thread{
	
}
public class MyJdbcTool {
	private JdbcTemplate jdbcTemplate;

	public synchronized static Integer haha(){
		return 1;
	}
	public JdbcTemplate getJdbcTemplate() { 
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	} 
	public static void main(String[] args)   {
		 String str = "123.doc";
		 String ext=str.substring(str.lastIndexOf(".")).toLowerCase();
		 System.out.println(ext);
	}

	/**
	 * 执行一段sql语句 
	 * @param sql
	 * @throws Exception 
	 */
	public void exeSql(String sql) { 
		this.jdbcTemplate.execute(sql); 
	}

	/**
	 * 查询整数.
	 * @param sql
	 * @throws Exception
	 */
	public int queryForInt(String sql) { 
		return this.jdbcTemplate.queryForInt(sql); 
	}
	
	/**
	 * 查询整数.
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public int queryForInt(String sql,Object[] args) { 
		return this.jdbcTemplate.queryForInt(sql,args); 
	}
	
	/**
	 * 查询集合.
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List queryForList(String sql)  { 
		return this.jdbcTemplate.queryForList(sql); 
	} 
 
	/**
	 * 查询集合.
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public List queryForList(String sql,Object[] args)  { 
		return this.jdbcTemplate.queryForList(sql, args); 
	}
	
	/**
	 * 查询对象,并制定返回结果类型.
	 * @param sql
	 * @param requiredType
	 * @return
	 * @throws Exception
	 */
	public Object queryForObject(String sql,Class requiredType) { 
		return this.jdbcTemplate.queryForObject(sql, requiredType); 
	}
	
	/**
	 * 查询对象,并制定返回结果类型.
	 * @param sql
	 * @param args
	 * @param requiredType
	 * @return
	 * @throws Exception
	 */
	public Object queryForObject(String sql,Object[] args,Class requiredType) { 
		return this.jdbcTemplate.queryForObject(sql, args,requiredType); 
	}
	
	public Map query(String sql) {
		final List result = new ArrayList();
		Map ans = new HashMap();
		// 得到结果列的行列信息.
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		jdbcTemplate.query(sql, rcch);
		List columnNames = new ArrayList();
		// 得到列名
		for (String colName : rcch.getColumnNames()) {
			columnNames.add(colName);
		}
		ans.put("columnNames", columnNames);
		
		final List columnTypeNames =new ArrayList();    
		final int columnCount = columnNames.size();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				//如果是第一行，得到列的元数据,例如列的类型.
				if(rs.isFirst()){
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnCount = rsmd.getColumnCount();
					List columnTypes = new ArrayList();
					for (int i = 1; i <= columnCount; i++) { 
						columnTypes.add(rsmd.getColumnTypeName(i));
					}
					columnTypeNames.addAll(columnTypes);
				}
				Object[] ans = new Object[columnCount];
				for (int i = 0; i < columnCount; i++)
					ans[i] = rs.getObject(i + 1);
				result.add(ans);
			}
		});
		ans.put("colType", columnTypeNames);
		ans.put("data", result);
		return ans;
	} 
}
