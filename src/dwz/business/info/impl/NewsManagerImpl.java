package dwz.business.info.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import dwz.business.constants.info.NewsSearchFieldsDB;
import dwz.business.constants.info.NewsStatus;
import dwz.business.constants.info.NewsType;
import dwz.business.info.News;
import dwz.business.info.NewsManager;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.persistence.beans.InfNews;
import dwz.persistence.daos.InfNewsDao;

public class NewsManagerImpl extends AbstractBusinessObjectManager implements
		NewsManager {

	private InfNewsDao newsDao = null;

	public NewsManagerImpl(InfNewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public News newNews() {
		return new NewsImpl();
	}

	public void createAndPublishNews(News news) {
		NewsImpl newsImpl = (NewsImpl) news;
		newsImpl.setStatus(NewsStatus.ACTIVE);
		this.createNews(newsImpl);
	}

	public void createNews(News news) {
		NewsImpl newsImpl = (NewsImpl) news;
		this.newsDao.insert(newsImpl.getInfNews());
	}

	public void disableNews(Integer id) {
		this.newsDao.updateAllStatus(NewsStatus.DISABLED.toString(), id);
	}

	public void publishNews(Integer id) {
		this.newsDao.updateAllStatus(NewsStatus.ACTIVE.toString(), id);
	}

	public News getNews(Integer id) {
		InfNews infNews = this.newsDao.findByPrimaryKey(id);
		if (infNews != null)
			return new NewsImpl(infNews);
		return null;
	}

	public News getNewsOnWeb(Integer id) {
		News news = this.getNews(id);
		if (NewsStatus.ACTIVE.equals(news.getStatus()))
			return news;
		return null;
	}

	public Collection<News> listNewsOnWeb(NewsType type, int startIndex,
			int count) {
		ArrayList<News> newsList = new ArrayList<News>();
		Collection<InfNews> infNewsList = this.newsDao.findByTypePageBreak(type
				.toString(), startIndex, count);
		if (infNewsList != null && infNewsList.size() > 0) {
			for (InfNews infNews : infNewsList) {
				newsList.add(new NewsImpl(infNews));
			}
		}

		return newsList;
	}

	public void updateNews(News news) {
		NewsImpl newsImpl = (NewsImpl) news;
		this.newsDao.update(newsImpl.getInfNews());
	}

	public void removeNews(Integer id) {
		InfNews infNews = this.newsDao.findByPrimaryKey(id);
		this.newsDao.delete(infNews);
	}

	public Collection<News> searchNewsFromDB(Map<NewsSearchFieldsDB, String> criterias,
			int startIndex, int count) {
		ArrayList<News> newsList = new ArrayList<News>();
		if (criterias == null)
			return newsList;

		Collection<InfNews> infNewsList = this.newsDao.findByQuery(this
				.createQueryString(false, criterias), startIndex, count);

		if (infNewsList == null || infNewsList.size() == 0)
			return newsList;

		for (InfNews infNews : infNewsList) {
			newsList.add(new NewsImpl(infNews));
		}

		return newsList;
	}

	public int searchNewsFromDBNum(Map<NewsSearchFieldsDB, String> criterias) {
		if (criterias == null) {
			return 0;
		}

		Number totalCount = this.newsDao.countByQuery(createQueryString(true,
				criterias));

		return totalCount.intValue();
	}

	private String createQueryString(boolean useCount,
			Map<NewsSearchFieldsDB, String> criterias) {
		StringBuilder sb = new StringBuilder();
		int termCount = 0;
		sb.append("select").append(useCount ? " count(model.id)" : " model")
				.append(" from InfNews as model");

		for (NewsSearchFieldsDB field : NewsSearchFieldsDB.values()) {
			switch (field) {

			case COMPANY_ID:
				if (criterias.containsKey(field)) {
					sb.append(termCount == 0 ? " where " : " and ").append(
							"model.companyId='").append(
							criterias.get(field)).append("'");
					termCount++;
				}
				break;

			case STATUS:
				if (criterias.containsKey(field)) {
					sb.append(termCount == 0 ? " where " : " and ").append(
							"model.status='").append(
							criterias.get(field)).append("'");
					termCount++;
				}
				break;

			case TYPE:
				if (criterias.containsKey(field)) {
					sb.append(termCount == 0 ? " where " : " and ").append(
							"model.type = '").append(
							criterias.get(field)).append("'");
					termCount++;
				}
				break;

			case KEYWORDS:
				if (criterias.containsKey(field)) {
					String keywords = criterias.get(field);
					sb.append(termCount == 0 ? " where " : " and ").append(
							"model.title like '%").append(keywords)
							.append("%'");
					termCount++;
				}
				break;
			default:
				break;
			}
		}

		sb.append(" order by model.updateDate desc");

//		System.out.println("sql:"+sb);
		return sb.toString();
	}

	public News getNextNews(News news) {
		if (news == null)
			return null;
		InfNews infNews = ((NewsImpl) news).getInfNews();
		Collection<InfNews> pos = this.newsDao.findNextPageBreak(infNews
				.getType(), infNews.getUpdateDate(), 0, 1);
		if (pos == null || pos.size() < 1)
			return null;
		return new NewsImpl(pos.iterator().next());
	}

	public News getPrevNews(News news) {
		if (news == null)
			return null;
		InfNews infNews = ((NewsImpl) news).getInfNews();
		Collection<InfNews> pos = this.newsDao.findPrevPageBreak(infNews
				.getType(), infNews.getUpdateDate(), 0, 1);
		if (pos == null || pos.size() < 1)
			return null;
		return new NewsImpl(pos.iterator().next());
	}

}
