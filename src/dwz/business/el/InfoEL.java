package dwz.business.el;

import java.util.ArrayList;
import java.util.Collection;

import dwz.business.constants.info.NewsType;
import dwz.business.constants.website.PageTarget;
import dwz.business.info.News;
import dwz.business.info.NewsManager;
import dwz.business.inv.Category;
import dwz.business.inv.CategoryManager;
import dwz.business.website.Page;
import dwz.business.website.WebsiteManager;
import dwz.constants.BeanManagerKey;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.utils.EnumUtils;

public class InfoEL {

	public static Collection<News> listNews(String type, Integer startIndex,
			Integer count) {
		if (EnumUtils.isDefined(NewsType.values(), type)) {

			NewsManager manager = BusinessFactory.getFactory().getManager(BeanManagerKey.newsManager);
			return manager.listNewsOnWeb(NewsType.valueOf(type), startIndex,
					count);
		}
		return new ArrayList<News>();
	}

	public static Collection<Page> listPages(String target) {
		if (EnumUtils.isDefined(PageTarget.values(), target)) {
			WebsiteManager manager = BusinessFactory.getFactory()
					.getManager(BeanManagerKey.websiteManager);
			return manager.getPages(PageTarget.valueOf(target));
		}
		return new ArrayList<Page>();
	}
	
	public static Collection<Category> listCategorys()
	{
		CategoryManager manager = BusinessFactory.getFactory().getManager(BeanManagerKey.categoryManager);
		return manager.getCategorys();
	}
}
