package money.question;

import java.util.Date;

import dwz.framework.core.business.BusinessObject;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class QuestionImpl implements Question {
	private QuestionVO questionVO = null;
	private static final long serialVersionUID = 1L;

	public QuestionImpl(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}

	public QuestionImpl(String questionDesc, Date questionDate,
			Date consoleDate, String answer, int sort, int orderId, int status,String submit) {
		this.questionVO = new QuestionVO(questionDesc, questionDate,
				consoleDate, answer, sort, orderId, status,submit);
	}

	public QuestionImpl(int questionId, String questionDesc, Date questionDate,
			Date consoleDate, String answer, int sort, int orderId, int status,String submit) {
		this.questionVO = new QuestionVO(questionId, questionDesc,
				questionDate, consoleDate, answer, sort, orderId, status,submit);
	}

	public QuestionVO getQuestionVO() {
		return this.questionVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	public Integer getId() {
		return this.questionVO.getQuestionId();
	}

	public int getQuestionId() {
		return this.questionVO.getQuestionId();
	}

	public String getQuestionDesc() {
		return this.questionVO.getQuestionDesc();
	}

	public Date getQuestionDate() {
		return this.questionVO.getQuestionDate();
	}

	public Date getConsoleDate() {
		return this.questionVO.getConsoleDate();
	}

	public String getAnswer() {
		return this.questionVO.getAnswer();
	}

	public int getSort() {
		return this.questionVO.getSort();
	}

	public int getOrderId() {
		return this.questionVO.getOrderId();
	}

	public int getStatus() {
		return this.questionVO.getStatus();
	}

	public String getSortName() { 
		return this.questionVO.getSortName();
	}

	public String getStatusName() { 
		return this.questionVO.getStatusName();
	}

	public String getSubmit() {
		return this.questionVO.getSubmit();
	}

}
