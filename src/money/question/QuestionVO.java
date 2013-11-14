package money.question;

import java.io.Serializable;
import java.util.Date;

/**
 * 理财管理类.
 * 
 * @author lsq
 * 
 */
public class QuestionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	public QuestionVO() {

	}

	public QuestionVO(int questionId, String questionDesc, Date questionDate,
			Date consoleDate, String answer, int sort, int orderId,int status,String submit) {
		this.questionId = questionId;
		this.questionDesc = questionDesc;
		this.questionDate = questionDate;
		this.consoleDate = consoleDate;
		this.answer = answer;
		this.sort = sort;
		this.status= status;
		this.orderId = orderId;
		this.submit = submit;
	}

	private String submit;
	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public QuestionVO(String questionDesc, Date questionDate, Date consoleDate,
			String answer, int sort, int orderId,int status,String submit) {
		this.questionDesc = questionDesc;
		this.questionDate = questionDate;
		this.submit =submit;
		this.consoleDate = consoleDate;
		this.answer = answer;
		this.sort = sort;
		this.status= status;
		this.orderId = orderId;

	}

	private int status;
	
	private int questionId;

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionId() {
		return questionId;
	}

	private String questionDesc;

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	private Date questionDate;

	public void setQuestionDate(Date questionDate) {
		this.questionDate = questionDate;
	}

	public Date getQuestionDate() {
		return questionDate;
	}

	private Date consoleDate;

	public void setConsoleDate(Date consoleDate) {
		this.consoleDate = consoleDate;
	}

	public Date getConsoleDate() {
		return consoleDate;
	}

	private String answer;

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	private int sort;
	
	private String sortName;

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}

	private int orderId;

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public int getStatus() {
		return status;
	}
	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

}
