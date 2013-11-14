package dwz.business.inv.impl;

import java.util.Date;

import dwz.business.inv.Category;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.persistence.beans.InvCategory;

public class CategoryImpl extends AbstractBusinessObject implements Category {
	
	private InvCategory invCategory;
	
	public CategoryImpl(InvCategory invCategory)
	{
		this.invCategory = invCategory;
	}
	public CategoryImpl()
	{
		this.invCategory =  new InvCategory(0,new Date(),new Date());
	}
	public InvCategory getPersistentObject() {
		return invCategory;
	}
	public Integer getId()
	{
		return invCategory.getId();
	}
	public Integer getPid()
	{
		return invCategory.getPid();
	}
	public void setPid(Integer pid)
	{
		invCategory.setPid(pid);
	}
	public String getName()
	{
		return invCategory.getName();
	}
	public void setName(String name)
	{
		invCategory.setName(name);
	}
	public Integer getLft()
	{
		return invCategory.getLft();
	}
	
	public void setLft(Integer lft)
	{
		invCategory.setLft(lft);
	}
	
	public Integer getRgt()
	{
		return invCategory.getRgt();
	}
	
	public void setRgt(Integer rgt)
	{
		invCategory.setRgt(rgt);
	}
	
	public String getDescription()
	{
		return invCategory.getDescription();
	}
	
	public void setDescription(String description)
	{
		invCategory.setDescription(description);
	}
	public Date getUpdateDate() {
		return invCategory.getUpdateDate();
	}

	public void setUpdateDate(Date updateDate) {
		invCategory.setUpdateDate(updateDate);
	}
	
}
