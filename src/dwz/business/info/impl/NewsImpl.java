package dwz.business.info.impl;

import java.util.Date;

import dwz.business.constants.info.NewsStatus;
import dwz.business.constants.info.NewsType;
import dwz.business.info.News;
import dwz.business.info.NewsManager;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.business.AbstractBusinessObject;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.utils.EnumUtils;
import dwz.persistence.beans.InfNews;

public class NewsImpl extends AbstractBusinessObject implements News {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7228528303697108518L;
	private InfNews infNews;

	NewsImpl(InfNews infNews) {
		this.infNews = infNews;
	}

	NewsImpl() {
		Date now = new Date();
		this.infNews = new InfNews(NewsType.NEWS.toString(), NewsStatus.PENDING.toString(), now,
				now);
	}

	InfNews getInfNews() {
		return this.infNews;
	}

	public Integer getId() {
		return infNews.getId();
	}

	public String getAuthor() {
		return infNews.getAuthor();
	}

	public String getContent() {
		return infNews.getContent();
	}

	public String getHref() {
		return "/index!news.do?newsId=" + this.getId();
	}

	public Date getInsertDate() {
		return infNews.getInsertDate();
	}

	public News getNext() {
		NewsManager manager = BusinessFactory.getFactory().getManager(BeanManagerKey.newsManager);
		return manager.getNextNews(this);
	}

	public News getPrev() {
		NewsManager manager = BusinessFactory.getFactory().getManager(BeanManagerKey.newsManager);
		return manager.getPrevNews(this);
	}

	public String getSource() {
		return infNews.getSource();
	}

	public NewsStatus getStatus() {
		if (EnumUtils.isDefined(NewsStatus.values(), infNews.getStatus()))
			return NewsStatus.valueOf(infNews.getStatus());
		return NewsStatus.PENDING;
	}

	void setStatus(NewsStatus status) {
		this.infNews.setStatus(status.toString());
	}

	public String getTitle() {
		return infNews.getTitle();
	}

	public NewsType getType() {
		if (EnumUtils.isDefined(NewsType.values(), infNews.getType()))
			return NewsType.valueOf(infNews.getType());
		return null;
	}

	public Date getUpdateDate() {
		return infNews.getUpdateDate();
	}

	public void setAuthor(String author) {
		infNews.setAuthor(author);
	}

	public void setContent(String content) {
		infNews.setContent(content);
	}

	public void setSource(String source) {
		infNews.setSource(source);
	}

	public void setTitle(String title) {
		infNews.setTitle(title);
	}

	public void setType(NewsType type) {
//		if (EnumUtils.isDefined(NewsType.values(), type))
			infNews.setType(type.toString());
	}

}
