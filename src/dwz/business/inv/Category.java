package dwz.business.inv;

import java.util.Date;

import dwz.framework.core.business.BusinessObject;

public interface Category extends BusinessObject {
	
	
	Integer getId();
	
	Integer getPid();
	
	void setPid(Integer pid);

	String getName();

	void setName(String name);
	
	Integer getLft();
	
	void setLft(Integer lft);
	
	Integer getRgt();
	
	void setRgt(Integer rgt);
	
	String getDescription();
	
	void setDescription(String description);
	
	Date getUpdateDate();

	void setUpdateDate(Date updateDate);
	
}
