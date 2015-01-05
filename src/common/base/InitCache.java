package common.base;


/**
 * 缓存初始化加载！
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class InitCache implements Initializable{

	public void init() {
		//初始化全部的缓存
		AllSelect allselect  = (AllSelect)SpringContextUtil.getBean(AllSelect.BEANNAME);
//		allselect.getAllMoneyType();
		allselect.getAllParamType();
		allselect.getAllParamTypeCode();
		allselect.cacheAllParams();
		
		//初始化金额树.
//		TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
//		tMgr.getMoneyTypeTree(); 
	}

}
