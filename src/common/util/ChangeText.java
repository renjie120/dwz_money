package common.util;


/**
 * 读取一个文本内容,并将其内容进行替换. 具体替换的方式需要子类实现changeString().
 * 
 * @author lsq
 * 
 */
public class ChangeText extends BaseChangeText {

	String changeString(String oldStr) {
		return oldStr;
	}

	String beforeChangeString() {
		return "";
	}

	String afterChangeString() {
		return "";
	} 
}
