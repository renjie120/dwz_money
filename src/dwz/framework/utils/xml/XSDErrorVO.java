/**
 * 
 */
package dwz.framework.utils.xml;

/**
 * @author peng
 *
 */
public class XSDErrorVO {
	
	private String errorMsg;
	private int column;
	private int line;
	
	public XSDErrorVO(String errorMsg,int column,int line){
		this.errorMsg = errorMsg;
		this.column = column;
		this.line = line;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}
	
}
