package money.question;

import java.util.Date;

import dwz.framework.core.business.BusinessObject;

public interface Question extends BusinessObject {

	public Integer getId();

	public int getQuestionId();

	public int getStatus();

	public String getStatusName();

	public String getSortName();

	public String getQuestionDesc();

	public Date getQuestionDate();

	public Date getConsoleDate();

	public String getAnswer();

	public int getSort();
	
	public String getSubmit();

	public int getOrderId();

}
