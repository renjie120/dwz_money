
package money.question;

import java.util.Collection;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import dwz.framework.core.business.BusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

//@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,rollbackFor = Exception.class)
public interface QuestionManager extends BusinessObjectManager {  

	public Collection<Question> searchQuestion(Map<QuestionSearchFields, Object> criterias,QuestionQueryVO questionQueryVO,
			String orderField, int startIndex, int count); 
	
	public Integer searchQuestionNum(Map<QuestionSearchFields, Object> criterias,QuestionQueryVO questionQueryVO); 

	@Transactional
	public void createQuestion(Question question)
			throws ValidateFieldsException;

	@Transactional
	public void updateQuestion(Question question) throws ValidateFieldsException;

	@Transactional
	public void removeQuestion(String questionId);
	
	public Question getQuestion(Integer id);
}

