package common.base;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 参数查询下拉菜单.
 * 针对参数的专门做的一个下拉菜单查找类！方便快速的根据id查找name，和返回json串等.
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class ParamSelect<T extends SelectAble> {
	List<String> values = new ArrayList(); 
	List<String> names= new ArrayList();
	Collection<T> datas;
	public ParamSelect(Collection<T> collection){
		Iterator<T> it = collection.iterator();
		while(it.hasNext()){
			SelectAble p = it.next(); 
			values.add(p.getOptionId());
			names.add(p.getOptionName());
		}
		this.datas = collection;
	} 
	
	public int getLength(){
		return this.values.size();
	}
	
	public String getValue(int index){
		return this.values.get(index);
	}
	
	public String getName(int index){
		return this.names.get(index);
	}
	 
	public Collection<T> getDatas() {
		return datas;
	} 

	public String getName(String value){
		int index = values.indexOf(value);
		if(index!=-1)
			return (String)names.get(index);
		else
			return null;
	}
	
	public String getValue(String value){
		int index = names.indexOf(value);
		if(index!=-1)
			return (String)values.get(index);
		else
			return null;
	}
	
	public String getJson(){
		StringBuffer buf=  new StringBuffer();
		buf.append("[");
		for(int i=0;i<values.size();i++){
			buf.append("{\"").append("parameterValue\":\"").append(values.get(i))
				.append("\",\"parameterName\":").append(names.get(i)).append("\"}");
			if(i<values.size()-1)
				buf.append(",");
		}
		buf.append("]");
		return buf.toString();
	}
}
