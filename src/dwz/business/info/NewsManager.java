package dwz.business.info;

import java.util.Collection;
import java.util.Map;

import dwz.business.constants.info.NewsSearchFieldsDB;
import dwz.business.constants.info.NewsType;
import dwz.framework.core.business.BusinessObjectManager;

public interface NewsManager extends BusinessObjectManager {

	public void createNews(News news);

	public void createAndPublishNews(News news);

	public void publishNews(Integer id);

	public void disableNews(Integer id);

	public void updateNews(News news);

	public void removeNews(Integer id);

	public News getNews(Integer id);

	public News getNewsOnWeb(Integer id);

	public Collection<News> listNewsOnWeb(NewsType type, int startIndex,
			int count);

	public News newNews();

	public Collection<News> searchNewsFromDB(Map<NewsSearchFieldsDB, String> criterias,
			int startIndex, int count);

	public int searchNewsFromDBNum(Map<NewsSearchFieldsDB, String> criterias);

	public News getPrevNews(News news);

	public News getNextNews(News news);

}
