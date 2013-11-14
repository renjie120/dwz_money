
package money.question;

import java.util.Collection;

import dwz.dal.BaseDao;

public interface QuestionDao extends BaseDao<QuestionVO, Integer> {

	public Collection<QuestionVO> findRecordById(int moneySno); 
	
	public Collection<QuestionVO> findAll(); 

	public void deleteAllById(String id);

	public void updateAllaccessQuestionVO(QuestionVO vo, String id);
}

