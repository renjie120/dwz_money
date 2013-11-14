package common.taglib;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的下拉菜单集合.
 * @author renjie120 419723443@qq.com
 *
 */
public class OptionColl {
	private List list;

	public OptionColl() {
		list = new ArrayList();
	}

	public OptionColl(List<Object[]> otherList) throws Exception{
		if(otherList!=null){
			list = new ArrayList();
			int size = otherList.size();
			for(int i=0;i<size;i++){
				Object[] item = otherList.get(i);
				if(item.length>=2)
					list.add(new Option(item[0].toString(),item[1].toString()));
				else{
					throw new Exception("转换出错！不符合option转换格式");
				}
			}
		}
	}
	
	public void add(Option option) {
		list.add(option);
	}

	public void clear() {
		list.clear();
	}

	public int size() {
		if (list != null)
			return list.size();
		else
			return 0;
	}
	
	public Option get(int index){
		if(list!=null&&list.get(index)!=null){
			return (Option)(list.get(index));
		}else{
			return null;
		}
	}
	
	/**
	 * 根据id查找对应的第一个名字
	 * @param id
	 * @return
	 */
	public String findName(String id){
		if(list!=null&&list.size()>0)
			for(Object o:list){
				Option option = (Option)o;
				if(id.equals(option.getId())){
					return option.getName();
				}
			}
		return null;
	}
	
	/**
	 * 根据名称查找对应的第一个id
	 * @param name
	 * @return
	 */
	public String findId(String name){
		if(list!=null&&list.size()>0)
			for(Object o:list){
				Option option = (Option)o;
				if(name.equals(option.getName())){
					return option.getId();
				}
			}
		return null;
	}
}
