
package money.homepage;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于首页url的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class HomePageUrlVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public HomePageUrlVO() {

	}
	
	public HomePageUrlVO( int urlId , String urlDesc , String url , int orderId , int typeId ) {
		 this.urlId = urlId;
		 this.urlDesc = urlDesc;
		 this.url = url;
		 this.orderId = orderId;
		 this.typeId = typeId;
	}
	
	public HomePageUrlVO(String urlDesc ,String url ,int orderId ,int typeId ) {
			 this.urlDesc = urlDesc;
			 this.url = url;
			 this.orderId = orderId;
			 this.typeId = typeId;
	}
	 
	private Integer urlId; 
 	/**
 	 * 获取首页流水号id的属性值.
 	 */
 	public Integer getUrlId(){
 		return urlId;
 	}
 	
 	/**
 	 * 设置首页流水号id的属性值.
 	 */
 	public void setUrlId(Integer urlid){
 		this.urlId = urlid;
 	}
	private String urlDesc; 
 	/**
 	 * 获取链接描述 的属性值.
 	 */
 	public String getUrlDesc(){
 		return urlDesc;
 	}
 	
 	/**
 	 * 设置链接描述 的属性值.
 	 */
 	public void setUrlDesc(String urldesc){
 		this.urlDesc = urldesc;
 	}
	private String url; 
 	/**
 	 * 获取链接的属性值.
 	 */
 	public String getUrl(){
 		return url;
 	}
 	
 	/**
 	 * 设置链接的属性值.
 	 */
 	public void setUrl(String url){
 		this.url = url;
 	}
	private int orderId; 
 	/**
 	 * 获取排序号的属性值.
 	 */
 	public int getOrderId(){
 		return orderId;
 	}
 	
 	/**
 	 * 设置排序号的属性值.
 	 */
 	public void setOrderId(int orderid){
 		this.orderId = orderid;
 	}
	private int typeId; 
 	/**
 	 * 获取类型的属性值.
 	 */
 	public int getTypeId(){
 		return typeId;
 	}
 	
 	/**
 	 * 设置类型的属性值.
 	 */
 	public void setTypeId(int typeid){
 		this.typeId = typeid;
 	}
}
