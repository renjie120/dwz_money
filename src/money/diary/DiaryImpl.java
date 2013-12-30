
package money.diary;

import dwz.framework.core.business.BusinessObject;
import java.util.Date;
/**
 * 关于日志信息表的业务实体类.
 * @author www(水清)
 * 任何人和公司可以传播并且修改本程序，但是不得去掉本段声明以及作者署名.
 * http://www.iteye.com
 */ 
public class DiaryImpl implements Diary {
	private DiaryVO diaryVO = null;
	private static final long serialVersionUID = 1L;

	public DiaryImpl(DiaryVO diaryVO) {
		this.diaryVO = diaryVO;
	}

	public DiaryImpl( int diaryId , String diaryContent , Date diaryTime , String diaryType ) {
		this.diaryVO = new DiaryVO( diaryId , diaryContent , diaryTime , diaryType );
	} 
	
	public DiaryImpl(String diaryContent ,Date diaryTime ,String diaryType ) {
		this.diaryVO = new DiaryVO(diaryContent ,diaryTime ,diaryType );
	} 

	public DiaryVO getDiaryVO() {
		return this.diaryVO;
	}

	public void copyProperties(BusinessObject orig) {

	}

	/**
	 * 返回主键.
	 */
	public Integer getId() {
		return this.diaryVO.getDiaryId();
	} 
	
 	/**
 	 * 获取日志id的属性值.
 	 */
 	public  Integer   getDiaryId(){
 		return this.diaryVO.getDiaryId();
 	}
 	/**
 	 * 获取日志内容的属性值.
 	 */
 	public  String   getDiaryContent(){
 		return this.diaryVO.getDiaryContent();
 	}
 	/**
 	 * 获取开始时间的属性值.
 	 */
 	public  Date   getDiaryTime(){
 		return this.diaryVO.getDiaryTime();
 	}
 	/**
 	 * 获取日志类型的属性值.
 	 */
 	public  String   getDiaryType(){
 		return this.diaryVO.getDiaryType();
 	}
 
}