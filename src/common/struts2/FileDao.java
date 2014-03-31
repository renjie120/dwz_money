package common.struts2;

import dwz.dal.BaseDao;

public interface FileDao extends BaseDao<MyFile, String> {

	/**
	 * 根据年份进行统计金额数据.
	 * 
	 * @param sql
	 * @param arguments
	 * @return
	 */
	public MyFile commonSqlGetFile(String sql,Object[] arguments);

	/**
	 * 删除文件.
	 * 
	 * @param id
	 */
	public void deleteAllById(String id); 
	
	/**
	 * 保存文件
	 * 
	 * @param id
	 */
	public void commonExecuteSqlSaveFile(String sql,Object[] arguments); 
}
