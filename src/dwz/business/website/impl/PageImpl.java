package dwz.business.website.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import dwz.business.constants.website.PageTarget;
import dwz.business.website.Page;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.persistence.beans.WebPage;

public class PageImpl extends AbstractBusinessObject implements Page {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8643686602576620656L;

	private WebPage webPage = null;

	public PageImpl(String name) {
		webPage = new WebPage(name, new Date());
	}

	public PageImpl(WebPage webPage) {
		this.webPage = webPage;
	}

	public WebPage getPersistentObject() {
		return webPage;
	}

	public String getTitle() {
		return webPage.getTitle();
	}

	public void setTitle(String title) {
		webPage.setTitle(title);
	}

	public Integer getId() {
		return webPage.getId();
	}

	public Date getInsertDate() {
		return webPage.getInsertDate();
	}

	public Date getUpdateDate() {
		return webPage.getUpdateDate();
	}

	public void setInsertDate(Date insertDate) {
		webPage.setInsertDate(insertDate);
	}

	public void setUpdateDate(Date updateDate) {
		webPage.setUpdateDate(updateDate);
	}

	public String getContent() {
		return webPage.getContent();
	}

	public String getMetaDescription() {
		return webPage.getMetaDescription();
	}

	public String getMetaKeyword() {
		return webPage.getMetaKeywords();
	}

	public void setContent(String content) {
		webPage.setContent(content);
	}

	public void setMetaDescription(String description) {
		webPage.setMetaDescription(description);
	}

	public void setMetaKeyword(String keywords) {
		webPage.setMetaKeywords(keywords);
	}

	public String getName() {
		return webPage.getName();
	}

	public Collection<String> getTarget() {
		if (webPage.getTarget() != null) {
			return Arrays.asList(webPage.getTarget().split(","));
		}
		return new ArrayList<String>();
	}

	public String getTargetStr() {
		return webPage.getTarget();
	}

	public void setName(String name) {
		webPage.setName(name);
	}

	public void setTarget(String[] target) {
		if (target != null) {
			StringBuilder t = new StringBuilder();
			for (int i = 0; i < target.length; i++) {
				t.append(target[i]);
				if (i < target.length - 1) {
					t.append(",");
				}
			}

			webPage.setTarget(t.toString());
		}
	}

	public boolean isOnTarget(PageTarget target) {
		for (String t : this.getTarget()) {
			if (target.toString().equals(t)) {
				return true;
			}
		}
		return false;
	}

	public int getSequence() {
		if (webPage.getSequence() == null) return 0;
		return webPage.getSequence();
	}

	public void setSequence(int sequence) {
		webPage.setSequence(sequence);
	}

}
