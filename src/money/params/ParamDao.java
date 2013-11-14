package money.params;

import java.util.Collection;

import dwz.dal.BaseDao;

/**
 * 之所以和hbm联系起来，是因为有代理在，DaoIntroductionInterceptor！！
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public interface ParamDao extends BaseDao<ParamVO, Integer> {

	public Collection<ParamVO> findRecordById(int moneySno); 
	
	public Collection<ParamVO> findAll(); 
	
	public Collection<ParamVO> findParmByType(int paramType);  

	public void deleteAllById(String id);

	public void updateAllaccessParamVO(ParamVO vo, String id);
}
