package testGridTree;

/**
 * 演示bean.注意其中的属性isLeaf.
 * 是为了演示懒加载树添加了一个属性,不一定要在数据库中存在这个属性!
 * connect me:419723443@qq.com
 */
public class DisBean {
	private String disid;
	private String disname;
	private String disparentId;
	private String isLeaf;
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getDisid() {
		return disid;
	}
	public void setDisid(String disid) {
		this.disid = disid;
	}
	public String getDisname() {
		return disname;
	}
	public void setDisname(String disname) {
		this.disname = disname;
	}
	public String getDisparentId() {
		return disparentId;
	}
	public void setDisparentId(String disparentId) {
		this.disparentId = disparentId;
	}
}
