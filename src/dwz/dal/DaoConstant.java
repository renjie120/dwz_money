package dwz.dal;


class DaoConstant {

	public static final String PAGE_BREAK_SUFFIX = "PageBreak";

	public static final String FIND_PREFIX = "find";
	
	/**
	 * 使用普通的sql进行查询.
	 */
	public static final String COMMON_SQL_PREFIX = "commonSql";
	
	/**
	 * 执行原始的sql进行操作.
	 */
	public static final String COMMON_EXE_SQL_PREFIX = "commonExecuteSql";
	
	/**
	 * 自己写hql进行查询.
	 */
	public static final String HIBERNATE_SQL_PREFIX = "hibernateSql";
	
	public static final String EXECUTE_PREFIX = "execute";

	public static final String DELETE_ALL_PREFIX = "deleteAll";

	public static final String UPDATE_ALL_PREFIX = "updateAll";
	
	public static final int INSERT_SIZE = 50;
}
