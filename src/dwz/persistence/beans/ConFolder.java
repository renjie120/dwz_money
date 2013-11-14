package dwz.persistence.beans;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ConFolder entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class ConFolder implements java.io.Serializable {

	private static final long serialVersionUID = 0L;
	
	private String id;
	private SysRole role;
	private String name;
	private Integer lft;
	private Integer rgt;
	private String parentId;
	private Date insertDate;
	private String insertBy;
	
	private Set conFiles = new HashSet(0);
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getLft() {
		return lft;
	}
	public void setLft(Integer lft) {
		this.lft = lft;
	}
	public Integer getRgt() {
		return rgt;
	}
	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Set getConFiles() {
		return conFiles;
	}
	public void setConFiles(Set conFiles) {
		this.conFiles = conFiles;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getInsertBy() {
		return insertBy;
	}
	public void setInsertBy(String insertBy) {
		this.insertBy = insertBy;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}

	

}