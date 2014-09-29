package money.baidu;

import dwz.present.BaseAction;

 
public class MapAction extends BaseAction {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 化学公式导入字符串.
	 */
	private String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String initMap() {
		return "detail";
	}
	public String initSumMap() {
		return "sum";
	}
	public String initMarvinjs() {
		return "marvinjs";
	}
	public String saveSource() {
		System.out.println(source);
		return null;
	}
	public String initMarvinjs2() {
		request.setAttribute("id", id);
		return "marvinjs2";
	}
}
