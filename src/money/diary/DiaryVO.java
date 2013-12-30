
package money.diary;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
import java.io.Serializable;
/**
 * 关于日志信息表的实体bean.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class DiaryVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DiaryVO() {

	}
	
	public DiaryVO( int diaryId , String diaryContent , Date diaryTime , String diaryType ) {
		 this.diaryId = diaryId;
		 this.diaryContent = diaryContent;
		 this.diaryTime = diaryTime;
		 this.diaryType = diaryType;
	}
	
	public DiaryVO(String diaryContent ,Date diaryTime ,String diaryType ) {
			 this.diaryContent = diaryContent;
			 this.diaryTime = diaryTime;
			 this.diaryType = diaryType;
	}
	 
	private Integer diaryId; 
 	/**
 	 * 获取日志id的属性值.
 	 */
 	public Integer getDiaryId(){
 		return diaryId;
 	}
 	
 	/**
 	 * 设置日志id的属性值.
 	 */
 	public void setDiaryId(Integer diaryid){
 		this.diaryId = diaryid;
 	}
	private String diaryContent; 
 	/**
 	 * 获取日志内容的属性值.
 	 */
 	public String getDiaryContent(){
 		return diaryContent;
 	}
 	
 	/**
 	 * 设置日志内容的属性值.
 	 */
 	public void setDiaryContent(String diarycontent){
 		this.diaryContent = diarycontent;
 	}
	private Date diaryTime; 
 	/**
 	 * 获取开始时间的属性值.
 	 */
 	public Date getDiaryTime(){
 		return diaryTime;
 	}
 	
 	/**
 	 * 设置开始时间的属性值.
 	 */
 	public void setDiaryTime(Date diarytime){
 		this.diaryTime = diarytime;
 	}
	private String diaryType; 
 	/**
 	 * 获取日志类型的属性值.
 	 */
 	public String getDiaryType(){
 		return diaryType;
 	}
 	
 	/**
 	 * 设置日志类型的属性值.
 	 */
 	public void setDiaryType(String diarytype){
 		this.diaryType = diarytype;
 	}
}
