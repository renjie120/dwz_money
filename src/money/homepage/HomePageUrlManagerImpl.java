
package money.homepage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.framework.core.exception.ValidateFieldsException;

/**
 * 关于首页url的业务操作实现类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class HomePageUrlManagerImpl extends AbstractBusinessObjectManager implements
		HomePageUrlManager {

	private HomePageUrlDao homepageurldao = null;

	/**
	 * 构造函数.
	 */
	public HomePageUrlManagerImpl(HomePageUrlDao homepageurldao) {
		this.homepageurldao = homepageurldao;
	}

	/**
	 * 查询总数.
	 * @param criterias 查询条件
	 * @return
	 */
	public Integer searchHomePageUrlNum(Map<HomePageUrlSearchFields, Object> criterias) {
		if (criterias == null) {
			return 0;
		}
		Object[] quertParas = createQuery(true, criterias, null);
		String hql = quertParas[0].toString();
		Number totalCount = this.homepageurldao.countByQuery(hql,
				(Object[]) quertParas[1]);

		return totalCount.intValue();
	}

	/**
	 * 根据条件查询分页信息.
	 * @param criterias 条件
	 * @param orderField 排序列
	 * @param startIndex 开始索引
	 * @param count 总数
	 * @return
	 */
	public Collection<HomePageUrl> searchHomePageUrl(Map<HomePageUrlSearchFields, Object> criterias,
			String orderField, int startIndex, int count) {
		ArrayList<HomePageUrl> eaList = new ArrayList<HomePageUrl>();
		if (criterias == null)
			return eaList;

		Object[] quertParas = createQuery(false, criterias, orderField);
		String hql = quertParas[0].toString();
		Collection<HomePageUrlVO> voList = this.homepageurldao.findByQuery(hql,
				(Object[]) quertParas[1], startIndex, count);

		if (voList == null || voList.size() == 0)
			return eaList;
	
		
		for (HomePageUrlVO po : voList) {
			eaList.add(new  HomePageUrlImpl(po));
		}

		return eaList;
	}

	private Object[] createQuery(boolean useCount,
			Map<HomePageUrlSearchFields, Object> criterias, String orderField) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				useCount ? "select count( homepageurl) "
						: "select  homepageurl ").append("from HomePageUrlVO as homepageurl ");

		int count = 0;
		List argList = new ArrayList();
		if (criterias.size() > 0)
			for (Map.Entry<HomePageUrlSearchFields, Object> entry : criterias
					.entrySet()) {
				HomePageUrlSearchFields fd = entry.getKey();
				switch (fd) {
					case URLID:
						sb.append(count == 0 ? " where" : " and").append(
								"  homepageurl.urlId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case URLDESC:
						sb.append(count == 0 ? " where" : " and").append(
								"  homepageurl.urlDesc = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case URL:
						sb.append(count == 0 ? " where" : " and").append(
								"  homepageurl.url = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case ORDERID:
						sb.append(count == 0 ? " where" : " and").append(
								"  homepageurl.orderId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
					case TYPEID:
						sb.append(count == 0 ? " where" : " and").append(
								"  homepageurl.typeId = ? ");
						argList.add(entry.getValue());
						count++;
					break;
				default:
					break;
				}
			}

		if (useCount) {
			return new Object[] { sb.toString(), argList.toArray() };
		}

		HomePageUrlOrderByFields orderBy = HomePageUrlOrderByFields.URLID_DESC;
		if (orderField != null && orderField.length() > 0) {
			orderBy = HomePageUrlOrderByFields.valueOf(orderField);
		}

		switch (orderBy) {
			case URLID:
				 sb.append(" order by homepageurl.urlId");
			break;
			case URLDESC:
				 sb.append(" order by homepageurl.urlDesc");
			break;
			case URL:
				 sb.append(" order by homepageurl.url");
			break;
			case ORDERID:
				 sb.append(" order by homepageurl.orderId");
			break;
			case TYPEID:
				 sb.append(" order by homepageurl.typeId");
			break;
			default:
				break;
		}
		return new Object[] { sb.toString(), argList.toArray() };
	}

	public void createHomePageUrl(HomePageUrl homepageurl) throws ValidateFieldsException {
		HomePageUrlImpl homepageurlImpl = (HomePageUrlImpl) homepageurl;
		this.homepageurldao.insert(homepageurlImpl.getHomePageUrlVO());
	}

	public void removeHomePageUrls(String ids) {
		String[] idArr = ids.split(",");
		for (String s : idArr) {
			HomePageUrlVO vo = this.homepageurldao.findByPrimaryKey(Integer.parseInt(s));
			this.homepageurldao.delete(vo);
		}
	}

	public void updateHomePageUrl(HomePageUrl homepageurl) throws ValidateFieldsException {
		HomePageUrlImpl homepageurlImpl = (HomePageUrlImpl) homepageurl;

		HomePageUrlVO po = homepageurlImpl.getHomePageUrlVO();
		this.homepageurldao.update(po);
	}

	public HomePageUrl getHomePageUrl(int id) {
		Collection<HomePageUrlVO> homepageurls = this.homepageurldao.findRecordById(id);

		if (homepageurls == null || homepageurls.size() < 1)
			return null;

		HomePageUrlVO homepageurl = homepageurls.toArray(new HomePageUrlVO[homepageurls.size()])[0];

		return new HomePageUrlImpl(homepageurl);
	}

	@Override
	public Collection<HomePageUrl> searchAllHomePageUrl() {
		// TODO Auto-generated method stub
		Collection<HomePageUrlVO> homepageurls  = this.homepageurldao.findAll();
		ArrayList<HomePageUrl> eaList = new ArrayList<HomePageUrl>();
			
		if (homepageurls == null || homepageurls.size() < 1)
			return null;

		
		for (HomePageUrlVO po : homepageurls) {
			eaList.add(new  HomePageUrlImpl(po));
		}

		return eaList;
	}

}
