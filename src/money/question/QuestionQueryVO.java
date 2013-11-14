package money.question;

import java.util.Date;

/**
 * 高级查询question的属性绑定实体类.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class QuestionQueryVO {
	private String questionSortConditionC;
	private int questionSortC;
	private String questionDescConditionC;
	private String questionDescC;
	private Date smallQuestionDateC;
	private Date bigQuestionDateC;
	private String questionStatusConditionC;
	private int questionStatusC;
	private Date smallConsoleDateC;
	private Date bigConsoleDateC;
	private String answerConditionC;
	private String answerC;

	public String getQuestionSortConditionC() {
		return questionSortConditionC;
	}

	public void setQuestionSortConditionC(String questionSortConditionC) {
		this.questionSortConditionC = questionSortConditionC;
	}
 
	public String getQuestionDescConditionC() {
		return questionDescConditionC;
	}

	public void setQuestionDescConditionC(String questionDescConditionC) {
		this.questionDescConditionC = questionDescConditionC;
	}

	public String getQuestionDescC() {
		return questionDescC;
	}

	public void setQuestionDescC(String questionDescC) {
		this.questionDescC = questionDescC;
	}
 

	public String getQuestionStatusConditionC() {
		return questionStatusConditionC;
	}

	public void setQuestionStatusConditionC(String questionStatusConditionC) {
		this.questionStatusConditionC = questionStatusConditionC;
	} 

	public Date getSmallQuestionDateC() {
		return smallQuestionDateC;
	}

	public void setSmallQuestionDateC(Date smallQuestionDateC) {
		this.smallQuestionDateC = smallQuestionDateC;
	}

	public Date getBigQuestionDateC() {
		return bigQuestionDateC;
	}

	public void setBigQuestionDateC(Date bigQuestionDateC) {
		this.bigQuestionDateC = bigQuestionDateC;
	}

	public Date getSmallConsoleDateC() {
		return smallConsoleDateC;
	}

	public void setSmallConsoleDateC(Date smallConsoleDateC) {
		this.smallConsoleDateC = smallConsoleDateC;
	}

	public Date getBigConsoleDateC() {
		return bigConsoleDateC;
	}

	public void setBigConsoleDateC(Date bigConsoleDateC) {
		this.bigConsoleDateC = bigConsoleDateC;
	}

	public String getAnswerConditionC() {
		return answerConditionC;
	}

	public void setAnswerConditionC(String answerConditionC) {
		this.answerConditionC = answerConditionC;
	}

	public String getAnswerC() {
		return answerC;
	}

	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}

	public int getQuestionSortC() {
		return questionSortC;
	}

	public void setQuestionSortC(int questionSortC) {
		this.questionSortC = questionSortC;
	}

	public int getQuestionStatusC() {
		return questionStatusC;
	}

	public void setQuestionStatusC(int questionStatusC) {
		this.questionStatusC = questionStatusC;
	}
}
