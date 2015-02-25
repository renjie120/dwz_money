package IXmlParse;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.alibaba.fastjson.JSON;
import common.util.IDatamodelXmlReader;

/**
 * 解析数据说明文件.
 * 
 * @author lisq
 * 
 */
public class Dom4jModelParse {
	protected static Log log = LogFactory.getLog(Dom4jModelParse.class);
	private String fileName;

	public Dom4jModelParse(String fileName) {
		this.fileName = fileName;
	}

	public boolean parseValidate() {
		return true;
	} 
	
	public IDatamodelXmlReader parseClass(Node list) {
		return new Dom4jClassModel();
	}

	public Document read(String fileName) throws MalformedURLException,
			DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File(fileName));
		return document;
	}

	public Element getRootElement(Document doc) {
		return doc.getRootElement();
	} 

	/**
	 * 解析第一个class节点.
	 * 
	 * @return
	 */
	public IDatamodelXmlReader parse() {
		Dom4jClassModel model = new Dom4jClassModel();
		try {
			Document doc = read(this.fileName);
			Element root = getRootElement(doc);
			// 枚举属性
			for (Iterator i = root.attributeIterator(); i.hasNext();) {
				Attribute attribute = (Attribute) i.next();
				BeanUtils.setProperty(model, attribute.getName(),
						attribute.getText());
			}
			List<ColumnModel> attributes = new ArrayList<ColumnModel>();
			/**
			 * 遍历子节点
			 */
			List<Element> list = root.elements();
			for (Element e : list) {
				ColumnModel config = new ColumnModel();
				if ("id".equals(e.getName())) {
					config.setIskey("true");
					model.setKeyDesc(e.attributeValue("desc"));
					model.setKeyName(e.attributeValue("name"));
					model.setKeyColumn(e.attributeValue("column"));
					model.setKeyType(e.attributeValue("type"));
					model.setKeyColumnType(e.attributeValue("columnType"));
				} else {
					config.setIskey("false");
				}
				config.setNodeType(e.getName());
				// 节点汉字注释
				config.setDesc(e.attributeValue("desc"));
				// 最大长度
				config.setMaxLength(e.attributeValue("maxLength"));
				// 来自业务字典表的表名
				config.setFromTable(e.attributeValue("fromTable"));
				// 来自业务字典表的id列
				config.setIdCoulmn(e.attributeValue("idCoulmn"));
				// 使用缓存id的键.
				config.setUseCacheId(e.attributeValue("useCacheId"));
				// 来自业务字典表的name列
				config.setNameColumn(e.attributeValue("nameColumn"));
				// 设置复杂搜索类型
				config.setComplexQueryType(e.attributeValue("complexQueryType"));
				// 下拉菜单是否含有全选按钮
				config.setAllSelect(e.attributeValue("allSelect"));
				// 最短长度
				config.setMinLength(e.attributeValue("minLength"));
				// 是否可以导入该字段
				config.setCanImport(e.attributeValue("canImport"));
				// 数据库中非空字段
				config.setNotNullInDb(e.attributeValue("notNullInDb"));
				// 是否可以导出
				config.setCanExport(e.attributeValue("canExport"));
				// 列属性名
				config.setCols(e.attributeValue("cols"));
				// 数据的最大长度
				String size = e.attributeValue("size");
				if (isEmpty(size))
					size = "30";
				config.setSize(size);
				// 列属性名
				config.setRows(e.attributeValue("rows"));
				// 下拉菜单对应的文本的值
				config.setValues(e.attributeValue("values"));
				// 下拉菜单的对应的文本的显示值
				config.setNames(e.attributeValue("names"));
				// 浏览框设置的对应的url
				config.setBrowerUrl(e.attributeValue("browerUrl"));
				// 设置为选择下拉框
				config.setBrower(e.attributeValue("brower"));
				// 列属性名
				config.setName(e.attributeValue("name"));
				// 是否要检索
				config.setQuery(e.attributeValue("query"));
				// 列类型--对应数据库
				config.setColumnType(e.attributeValue("columnType"));
				// 列属性名
				config.setName(e.attributeValue("name"));
				// 是否模糊匹配
				config.setQuerylike(e.attributeValue("querylike"));
				// 显示在list.jsp里面的列宽度
				config.setWidth(e.attributeValue("width"));
				// 是否在list.jsp里面列表中显示出来
				config.setVisible(e.attributeValue("visible"));
				// 对应数据库中的列名
				config.setColumn(e.attributeValue("column"));
				// 类型
				String type = e.attributeValue("type");
				config.setType(type);
				// 显示的类型
				String showType = e.attributeValue("showType");
				if (showType == null)
					showType = type;
				config.setShowType(showType);
				// 对应的系统业务字段的id
				config.setSelectCode(e.attributeValue("selectCode"));
				// 是否必填字段
				config.setNotnull(e.attributeValue("notnull"));
				// 是否在edit.jsp中可以编辑
				config.setNoedit(e.attributeValue("noedit"));
				// 是否在添加页面中可以添加
				config.setNoadd(e.attributeValue("noadd"));
				// 使用当前时间
				config.setCurrentTime(e.attributeValue("currentTime"));
				// 使用当前用户
				config.setCurrentUser(e.attributeValue("currentUser"));
				// 对应的样式
				config.setClas(e.attributeValue("class"));
				// 在数据库里面的字段长度.
				config.setLength(e.attributeValue("length"));
				attributes.add(config);
			}
			model.setAttributes(attributes);
			System.out.println(JSON.toJSONString(model));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public static void main(String[] args) {
		String fileName = "E:\\dwz_money\\code_template\\datamodle.xml";
		Dom4jModelParse p = new Dom4jModelParse(fileName);
		p.parse();
	}

	private int parseString(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

	private static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}
}
