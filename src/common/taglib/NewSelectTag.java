package common.taglib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.util.LinkedCaseInsensitiveMap;

import common.MyJdbcTool;
import common.base.AllSelect;
import common.base.ParamSelect;
import common.base.SelectAble;
import common.base.SimpleOption;
import common.base.SpringContextUtil;
import common.util.CommonUtil;

import dwz.constants.BeanManagerKey;

/**
 * 自定义的下拉菜单定义类.
 * 
 * @author renjie120 419723443@qq.com
 * 
 */
public class NewSelectTag extends TagSupport {
	/**
	 * 构造函数.
	 * 
	 * @coustructor method
	 */
	public NewSelectTag() {
		super();
	}

	/**
	 * 缓存ID
	 */
	private String paraType = null;
	/**
	 * 下拉表的id
	 */
	private String id = null;

	/**
	 * 请选择字样.
	 */
	private String selectFlagStr = null;

	/**
	 * 变量描述信息.
	 */
	private String tagName = null;

	/**
	 * 是否有”请选择“一项，对应值为-1
	 */
	private String selectFlag = null;

	/**
	 * 是否有”全部选择“一项，对应值为-2
	 */
	private String allSelected = null;

	/**
	 * 选中值。
	 */
	private String selectedValue = null;

	/**
	 * 默认值
	 */
	private String defaultValue = null;
	/**
	 * 选择字段
	 */
	private Collection selections = null;
	/**
	 * 宽度
	 */
	private String width;
	/**
	 * 拆分符合，默认为,
	 */
	private String splitchar;

	/**
	 * onchange的响应方法
	 */
	private String onchange;

	private String listvalues;
	private String listtexts;
	private String tableName;
	private String idColumn;
	private String nameColumn;
	private String where;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	/**
	 * 不能改变
	 */
	private String disabled;

	/**
	 * 得到焦点
	 */
	private String onfocus;

	/**
	 * 失去焦点
	 */
	private String onblur;

	/**
	 * 得到id.
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置值.
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 标签结束方法.
	 * 
	 * @return
	 * @throws JspException
	 */
	public int doEndTag() throws JspException {

		try {
			pageContext.getOut().write(getSelectXML());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	/**
	 * 标签开始方法.
	 * 
	 * @return
	 * @throws JspException
	 */
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}

	/**
	 * 标签内容.
	 * 
	 * @return
	 */
	private synchronized String getSelectXML() {
		StringBuffer buf = new StringBuffer();
		String widthStr = "";
		if (!CommonUtil.isBlank(width)) {
			widthStr = " style=\"width:" + width + "\" ";
		}
		if (CommonUtil.isBlank(splitchar)) {
			splitchar = ",";
		}
		buf.append("<SELECT class=\"combox\" name=\"" + tagName + "\" id=\""
				+ tagName + "\" " + widthStr + "");
		if (onchange != null && !onchange.trim().equals("")) {
			buf.append(" onchange=\"" + onchange + "\"");
		}
		if (disabled != null && !disabled.trim().equals("")
				&& disabled.equals("true")) {
			buf.append(" disabled=\"true\"");
		}

		if (CommonUtil.isBlank(selectFlagStr)) {
			selectFlagStr = "请选择";
		}
		buf.append(">");
		try {
			if (!CommonUtil.isEmpty(selectFlag) && selectFlag.equals("true")) {
				buf.append("<option value='-1'>" + selectFlagStr + "</option>");
			}
			if (!CommonUtil.isEmpty(allSelected) && allSelected.equals("true")) {
				buf.append("<option value='-2'>全部选择</option>");
			}
			// 如果设置了liststr，就以这个为准！分解字符串为list放到下拉菜单中.
			if (!CommonUtil.isBlank(listvalues)
					&& !CommonUtil.isBlank(listtexts)) {
				String[] vs = listvalues.split(splitchar);
				String[] ts = listtexts.split(splitchar);
				selections = new ArrayList();
				for (int i = 0; i < vs.length; i++) {
					SimpleOption option = new SimpleOption();
					option.setText(ts[i]);
					option.setValue(vs[i]);
					selections.add(option);
				}
			}
			// 如果设置了查询数据库就以此为准
			else if (!CommonUtil.isBlank(tableName)
					&& !CommonUtil.isBlank(idColumn)
					&& !CommonUtil.isBlank(nameColumn)) {
				selections = new ArrayList();
				MyJdbcTool jdbcDaoTest = (MyJdbcTool) SpringContextUtil
						.getBean("jdbcTool");
				StringBuffer buf2 = new StringBuffer();
				buf2.append("select " + idColumn + "," + nameColumn + " from "
						+ tableName);
				if (!CommonUtil.isBlank(where))
					buf2.append(" where " + where);
				List ans = jdbcDaoTest.queryForList(buf2.toString());
				if (ans != null && ans.size() > 0)
					for (Object objs : ans) {
						LinkedCaseInsensitiveMap obj = (LinkedCaseInsensitiveMap) objs;
						SimpleOption option = new SimpleOption();
						option.setText(obj.get(nameColumn)  + "");
						option.setValue(obj.get(idColumn) + "");
						selections.add(option);
					}
			}
			// 如果设置了paraType就以此为准
			else if (!CommonUtil.isBlank(paraType)) {
				selections = new ArrayList();
				AllSelect allSelect = (AllSelect) SpringContextUtil
						.getBean(BeanManagerKey.allSelectManager.toString());
				ParamSelect params = allSelect.getParamsByType(paraType);
				for (int i = 0, j = params.getLength(); i < j; i++) {
					SimpleOption option = new SimpleOption();
					option.setText(params.getName(i));
					option.setValue(params.getValue(i));
					selections.add(option);
				}
			} else {
				if (selections == null) {
					buf.append("<option><value></value></option>");
				} else {
					// 如果设置了defaultValue,而且selectedValue属性没有设置,就强制设置selectedValue=defaultValue.
					if (!CommonUtil.isEmpty(defaultValue)) {
						selectedValue = defaultValue;
					}
				}
			}
			Iterator it = selections.iterator();
			while (it.hasNext()) {
				SelectAble vo = (SelectAble) it.next();
				// 设置了selectYear就找到这个年份选择.
				if (!CommonUtil.isEmpty(selectedValue)
						&& selectedValue.equals(vo.getOptionId())) {
					buf.append(" <option value=\"" + vo.getOptionId() + "\"");
					buf.append(" selected>");
					buf.append(vo.getOptionName());
				} else {
					buf.append(" <option value=\"" + vo.getOptionId() + "\">");
					buf.append(vo.getOptionName());
				}
				buf.append("</option>");
			}
			buf.append("</SELECT>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public Collection getSelections() {
		return selections;
	}

	public void setSelections(Collection selections) {
		this.selections = selections;
	}

	public String getSelectFlagStr() {
		return selectFlagStr;
	}

	public void setSelectFlagStr(String selectFlagStr) {
		this.selectFlagStr = selectFlagStr;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getAllSelected() {
		return allSelected;
	}

	public void setAllSelected(String allSelected) {
		this.allSelected = allSelected;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getOnchange() {
		return onchange;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getOnfocus() {
		return onfocus;
	}

	public void setOnfocus(String onfocus) {
		this.onfocus = onfocus;
	}

	public String getOnblur() {
		return onblur;
	}

	public void setOnblur(String onblur) {
		this.onblur = onblur;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getSplitchar() {
		return splitchar;
	}

	public void setSplitchar(String splitchar) {
		this.splitchar = splitchar;
	}

	public String getListvalues() {
		return listvalues;
	}

	public void setListvalues(String listvalues) {
		this.listvalues = listvalues;
	}

	public String getListtexts() {
		return listtexts;
	}

	public void setListtexts(String listtexts) {
		this.listtexts = listtexts;
	}

	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

}
