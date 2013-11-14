package dwz.business.info;

import java.util.Date;

import dwz.business.constants.info.NewsStatus;
import dwz.business.constants.info.NewsType;
import dwz.framework.core.business.BusinessObject;

public interface News extends BusinessObject{
	public Integer getId();

	public String getTitle();

	public void setTitle(String title);

	public NewsStatus getStatus();

	public NewsType getType();

	public void setType(NewsType type);

	public String getSource();

	public void setSource(String source);

	public String getAuthor();

	public void setAuthor(String author);

	public Date getUpdateDate();

	public String getContent();

	public void setContent(String content);

	public String getHref();

	public News getPrev();

	public News getNext();

}
