
package money.homepage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于首页url的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class HomePageUrlImpl implements HomePageUrl {
	private HomePageUrlVO homepageurlVO = null;
	private static final long serialVersionUID = 1L;

	public HomePageUrlImpl(HomePageUrlVO homepageurlVO) {
		this.homepageurlVO = homepageurlVO;
	}

	public HomePageUrlImpl( int urlId , String urlDesc , String url , int orderId , int typeId ) {
		this.homepageurlVO = new HomePageUrlVO( urlId , urlDesc , url , orderId , typeId );
	} 
	
	public HomePageUrlImpl(String urlDesc ,String url ,int orderId ,int typeId ) {
		this.homepageurlVO = new HomePageUrlVO(urlDesc ,url ,orderId ,typeId );
	} 

	public HomePageUrlVO getHomePageUrlVO() {
		return this.homepageurlVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.homepageurlVO.getUrlId();
	} 
	
 	/**
 	 * 获取首页流水号id的属性值.
 	 */
 	public  Integer   getUrlId(){
 		return this.homepageurlVO.getUrlId();
 	}
 	/**
 	 * 获取链接描述 的属性值.
 	 */
 	public  String   getUrlDesc(){
 		return this.homepageurlVO.getUrlDesc();
 	}
 	/**
 	 * 获取链接的属性值.
 	 */
 	public  String   getUrl(){
 		return this.homepageurlVO.getUrl();
 	}
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public  int   getOrderId(){
 		return this.homepageurlVO.getOrderId();
 	}
 	/**
 	 * 获取类型的属性值.
 	 */
 	public  int   getTypeId(){
 		return this.homepageurlVO.getTypeId();
 	}
 
}