package common.taglib;
 
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import common.util.CommonUtil;
 
/**
 * 自定义的下拉菜单定义类.
 * @author renjie120 419723443@qq.com
 *
 */
public class SelectTag extends TagSupport { 
    /**
     * 构造函数.
     * @coustructor method
     */
    public SelectTag() {
        super();
    }

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
    private OptionColl selectColl = null;

    /**
     * 显示年份  
     */
    private String selectYear = null;

    /**
     * onchange的响应方法
     */
    private String onchange;

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
     * 样式
     */
    private String css;

    /**
     * 返回null
     */
    private String returnNull;

    /**
     * 返回空.
     * @return
     */
    public String getReturnNull() {
        return returnNull;
    }

    /**
     * 设置返回空.
     * @param returnNull
     */
    public void setReturnNull(String returnNull) {
        this.returnNull = returnNull;
    }

    /**
     * 得到id.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置值. 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 得到css. 
     * @return
     */
    public String getCss() {
        return css;
    }

    /**
     * 设置css. 
     * @param css
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * 标签结束方法.
     * @return
     * @throws JspException
     */
    public int doEndTag() throws JspException {

        try {
            pageContext.getOut().write(getSelectXML());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    /**
     * 标签开始方法.
     * @return
     * @throws JspException
     */
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    /**
     * 标签内容.
     * @return
     */
    private synchronized String getSelectXML() {
        StringBuffer buf = new StringBuffer(); 
        buf.append("<SELECT name=\"" + tagName + "\" id=\"" + tagName + "\"");
        if (onchange != null && !onchange.trim().equals("")) {
            buf.append(" onchange=\"" + onchange + "\"");
        }
        if (id != null && !id.trim().equals("")) {
            buf.append(" id=\"" + id + "\"");
        }
        if (css != null && !css.trim().equals("")) {
            buf.append(" css=\"" + css + "\"");
        }
        if (onfocus != null && !onfocus.trim().equals("")) {
            buf.append(" onfocus=\"" + onfocus + "\"");
        }
        if (onblur != null && !onblur.trim().equals("")) {
            buf.append(" onblur=\"" + onblur + "\"");
        }
        if (onchange != null && !onchange.trim().equals("")) {
            buf.append(" onchange=\"" + onchange + "\"");
        }
        if (disabled != null && !disabled.trim().equals("")
                && disabled.equals("true")) {
            buf.append(" disabled=\"true\"");
        }
        if(CommonUtil.isBlank(selectFlagStr)){
        	selectFlagStr=  "请选择";
        }
        buf.append(">");
        try { 
            if (!CommonUtil.isEmpty(selectFlag) 
                    && selectFlag.equals("true")) { 
                    buf.append("<option value='-1'>"+selectFlagStr+"</option>"); 
            }
            if (!CommonUtil.isEmpty(allSelected) 
                    && allSelected.equals("true")) {
                buf
                        .append("<option value='-2'>全部选择</option>");
            }
            if (selectColl == null) {
                buf.append("<option><value></value></option>");
            }
            else {
            	//如果设置了defaultValue,而且selectedValue属性没有设置,就强制设置selectedValue=defaultValue.
            	 if (!CommonUtil.isEmpty(defaultValue)) {
            		 selectedValue = defaultValue;
				} 
            	 //下面进行循环年份下拉菜单处理
                for (int i = 0; i < selectColl.size(); i++) {
                    Option vo = selectColl.get(i);
                    //设置了selectYear就找到这个年份选择.
                   	if (!CommonUtil.isEmpty(selectedValue)
							&& selectedValue.equals(vo.getId())) {
						buf.append(" <option value=\"" + vo.getId() + "\"");
						buf.append(" selected>");
						buf.append(vo.getName());
					} else {
						buf.append(" <option value=\"" + vo.getId() + "\">");
						buf.append(vo.getName());
					} 
                    buf.append("</option>");
                }
            }
            buf.append("</SELECT>");
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        return buf.toString();
    }

    
    /**
     * 标签名字. 
     * @return
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置名字. 
     * @param tagName
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 得到标示. 
     * @return
     */
    public String getSelectFlag() {
        return selectFlag;
    }

    /**
     * 设置标示. 
     * @param selectFlag
     */
    public void setSelectFlag(String selectFlag) {
        this.selectFlag = selectFlag;
    }

    /**
     * 得到全选. 
     * @return
     */
    public String getAllSelected() {
        return allSelected;
    }

    /**
     * 设置全选. 
     * @param allSelected
     */
    public void setAllSelected(String allSelected) {
        this.allSelected = allSelected;
    } 
    /**
     * 得到选中年份.
     * @return
     */
    public String getSelectYear() {
        return selectYear;
    }

    /**
     * 设置选中年份.
     * @param selectYear
     */
    public void setSelectYear(String selectYear) {
        this.selectYear = selectYear;
    }

    /**
     * 得到改变方法.
     * @return
     */
    public String getOnchange() {
        return onchange;
    }

    /**
     * 设置改变方法.
     * @param onchange
     */
    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    /**
     * 得到不可用.
     * @return
     */
    public String getDisabled() {
        return disabled;
    }

    /**
     * 设置不可用.
     * @param disabled
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    /**
     * 得到foucus方法.
     * @return
     */
    public String getOnfocus() {
        return onfocus;
    }

    /**
     * 设置foucus方法.
     * @param onfocus
     */
    public void setOnfocus(String onfocus) {
        this.onfocus = onfocus;
    }

    /**
     * 得到失去焦点方法.
     * @return
     */
    public String getOnblur() {
        return onblur;
    }

    /**
     * 设置失去焦点方法 .
     * @param onblur
     */
    public void setOnblur(String onblur) {
        this.onblur = onblur;
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

	public OptionColl getSelectColl() {
		return selectColl;
	}

	public void setSelectColl(OptionColl selectColl) {
		this.selectColl = selectColl;
	}

	public String getSelectFlagStr() {
		return selectFlagStr;
	}

	public void setSelectFlagStr(String selectFlagStr) {
		this.selectFlagStr = selectFlagStr;
	} 
}
