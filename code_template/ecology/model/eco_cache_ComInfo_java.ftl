<#include "/com.renjie120.codegenerate.common.ftl"> package weaver.crm.Maint;

import java.util.*;
import weaver.conn.*;
import weaver.file.*;
import weaver.general.*;

/**
 * Description: ${model.classDesc}对象缓存类
 * 
   create PROCEDURE  [CRM_${bignm}_SelectAll] (@flag [int] output, @msg
   [varchar](80) output) AS SELECT * FROM ${model.table} order by id asc set
   @flag = 1 set @msg = 'OK!'
 * @author Samurai
 * 
 */
public class ${bignm}ComInfo extends BaseBean { 
	private ArrayList ids = null;
	private ArrayList names = null;
	private ArrayList descs = null;
	private StaticObj staticobj = null;

	private int current_index = -1;
	private int array_size = 0;

	/**
	 * 构造方法，初始化客户类型对象
	 * 
	 * @throws Exception
	 */
	public ${bignm}ComInfo() throws Exception {
		staticobj = StaticObj.getInstance();
		get${bignm}ComInfo();
		array_size = ids.size();
	}

	private void get${bignm}ComInfo() throws Exception {
		if (staticobj.getObject("${bignm}ComInfo") == null)
			set${bignm}ComInfo();
		ids = (ArrayList) (staticobj
				.getRecordFromObj("${bignm}ComInfo", "ids"));
		names = (ArrayList) (staticobj.getRecordFromObj("${bignm}ComInfo",
				"names"));
		descs = (ArrayList) (staticobj.getRecordFromObj("${bignm}ComInfo",
				"descs"));
	}

	private void set${bignm}ComInfo() throws Exception {
		if (ids != null)
			ids.clear();
		else
			ids = new ArrayList();
		if (names != null)
			names.clear();
		else
			names = new ArrayList();
		if (descs != null)
			descs.clear();
		else
			descs = new ArrayList();

		RecordSet rs = new RecordSet();
		rs.executeProc("CRM_${bignm}_SelectAll", "");
		try {
			while (rs.next()) {
				ids.add(rs.getString(1));
				names.add(rs.getString(2));
				descs.add(rs.getString(3));
			}
		} catch (Exception e) {
			writeLog(e);
			throw e;
		}
		staticobj.putRecordToObj("${bignm}ComInfo", "ids", ids);
		staticobj.putRecordToObj("${bignm}ComInfo", "names", names);
		staticobj.putRecordToObj("${bignm}ComInfo", "descs", descs);
	}

	/**
	 * 返回客户类型对象的数量
	 * 
	 * @return
	 */
	public int get${bignm}Num() {
		return array_size;
	}

	/**
	 * 返回是否存在下一个客户类型对象
	 * 
	 * @return
	 */
	public boolean next() {

		if ((current_index + 1) < array_size) {
			current_index++;
			return true;
		} else {
			current_index = -1;
			return false;
		}
	}

	/**
	 * 返回是否存在下一个客户类型对象
	 * 
	 * @return
	 */
	public boolean next(String s) {
		while (((current_index + 1) < array_size)) {
			current_index++;
		}

		if ((current_index + 1) >= array_size) {
			current_index = -1;
			return false;
		}

		else {
			current_index++;
			return true;
		}
	}

	/**
	 * 将指针设置到第一个客户类型对象之前
	 * 
	 */
	public void setTofirstRow() {
		current_index = -1;
	}

	/**
	 * 返回当前客户类型对象的ID
	 * 
	 * @return
	 */
	public String get${bignm}id() {
		return (String) (ids.get(current_index));
	}

	/**
	 * 返回当前客户类型对象的名称
	 * 
	 * @return
	 */
	public String get${bignm}name() {
		return ((String) (names.get(current_index)));
	}

	/**
	 * 返回指定ID客户类型对象的名称
	 * 
	 * @param key
	 *            指定客户类型对象的ID
	 * @return
	 */
	public String get${bignm}name(String key) {
		int index = ids.indexOf(key);
		if (index != -1)
			return ((String) names.get(index));
		else
			return "";
	}

	/**
	 * 返回当前客户类型对象的描述
	 * 
	 * @return
	 */
	public String get${bignm}desc() {
		return ((String) (descs.get(current_index)));
	}

	/**
	 * 返回指定ID客户类型对象的描述
	 * 
	 * @param key
	 *            指定客户类型对象的ID
	 * @return
	 */
	public String get${bignm}desc(String key) {
		int index = ids.indexOf(key);
		if (index != -1)
			return ((String) descs.get(index));
		else
			return "";
	}

	/**
	 * 从缓存中移除客户类型对象
	 * 
	 */
	public void remove${bignm}Cache() {
		staticobj.removeObject("${bignm}ComInfo");
	}
}







/*

<jsp:useBean id="${bignm}ComInfo" class="weaver.crm.Maint.${bignm}ComInfo" scope="page" /> 


<%
String ziliao = RecordSet.getString("ziliao");
String zliaoStr = "";
if(ziliao!=null){
	String[] arsg = ziliao.split(",");
	for(int i=0,j=arsg.length;i<j;i++){
		zliaoStr+=${bignm}ComInfo.getZiliaodesc(arsg[i]);
		if(i<j-1)
			zliaoStr+=",";
	}
}%>
	<%=zliaoStr%>



<INPUT class="wuiBrowser" _displayText="<%=zliaoStr%>" 
	value="<%=Util.toScreen(RecordSet.getString("ziliao"), user.getLanguage())%>" 
	_url="/systeminfo/BrowserMain.jsp?url=/interface/MultiCommonBrowser.jsp?type=browser.ziliao" 
	id=ziliao type=hidden name="ziliao" > 



<INPUT class="wuiBrowser" 
	_url="/systeminfo/BrowserMain.jsp?url=/interface/MultiCommonBrowser.jsp?type=browser.ziliao" 
	id=ziliao type=hidden name="ziliao" > 

*/