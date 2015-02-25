
package ido.Dict_Drugtype;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于药品大类字典表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class DrugTypeSelectImpl implements DrugTypeSelect {
	private DrugTypeSelectVO drugtypeselectVO = null;
	private static final long serialVersionUID = 1L;

	public DrugTypeSelectImpl(DrugTypeSelectVO drugtypeselectVO) {
		this.drugtypeselectVO = drugtypeselectVO;
	}

	public DrugTypeSelectImpl( int sno , String drugType , String typeStatus , int createUser , String createTime , int updateUser , String updateTime ) {
		this.drugtypeselectVO = new DrugTypeSelectVO( sno , drugType , typeStatus , createUser , createTime , updateUser , updateTime );
	} 
	
	public DrugTypeSelectImpl(String drugType ,String typeStatus ,int createUser ,String createTime ,int updateUser ,String updateTime ) {
		this.drugtypeselectVO = new DrugTypeSelectVO(drugType ,typeStatus ,createUser ,createTime ,updateUser ,updateTime );
	} 

	public DrugTypeSelectVO getDrugTypeSelectVO() {
		return this.drugtypeselectVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.drugtypeselectVO.getSno();
	} 
	
 	/**
 	 * 获取流水号的属性值.
 	 */
 	public  Integer   getSno(){
 		return this.drugtypeselectVO.getSno();
 	}
 	/**
 	 * 获取药品类别名称的属性值.
 	 */
 	public  String   getDrugType(){
 		return this.drugtypeselectVO.getDrugType();
 	}
 	/**
 	 * 获取是否有效的属性值.
 	 */
 	public  String   getTypeStatus(){
 		return this.drugtypeselectVO.getTypeStatus();
 	}
 	/**
 	 * 获取创建用户的属性值.
 	 */
 	public  int   getCreateUser(){
 		return this.drugtypeselectVO.getCreateUser();
 	}
 	/**
 	 * 获取创建时间的属性值.
 	 */
 	public  String   getCreateTime(){
 		return this.drugtypeselectVO.getCreateTime();
 	}
 	/**
 	 * 获取更新用户的属性值.
 	 */
 	public  int   getUpdateUser(){
 		return this.drugtypeselectVO.getUpdateUser();
 	}
 	/**
 	 * 获取更新时间的属性值.
 	 */
 	public  String   getUpdateTime(){
 		return this.drugtypeselectVO.getUpdateTime();
 	}
 
}