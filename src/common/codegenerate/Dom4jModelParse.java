package common.codegenerate;

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

import common.util.DomUtil;
import common.util.IDatamodelXmlReader;

/**
 * 解析数据说明文件.
 * 
 * @author lisq
 * 
 */
public class Dom4jModelParse implements IXmlParse {
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

	private String getNodeAttr(Element e,String nodeName){
		String s =  e.attributeValue(nodeName);
		if(s==null)
			return "";
		return s;
	}
	/**
	 * 设置大的类属性.
	 * 
	 * @param config
	 * @param e
	 */
	private void setModelAttrs(Dom4jClassModel config, Element e) {
		String importFile = getNodeAttr(e,"importFile");
		String exportFile = getNodeAttr(e,"exportFile");
		String complexQuery = getNodeAttr(e,"complexQuery");
		String add = getNodeAttr(e,"add");
		String update = getNodeAttr(e,"update");
		String delete = getNodeAttr(e,"delete");
		String cacheNameColumn = getNodeAttr(e,"cacheNameColumn");
		String cacheIdColumn = getNodeAttr(e,"cacheIdColumn");
		String cacheName = getNodeAttr(e,"cacheName");
		String packageName = getNodeAttr(e,"package");
		String className = getNodeAttr(e,"name");
		String classDesc = getNodeAttr(e,"desc");
		String table = getNodeAttr(e,"table"); 
		config.setCacheIdColumn(cacheIdColumn);
		config.setCacheName(cacheName);
		config.setCacheNameColumn(cacheNameColumn);
		if (isEmpty(cacheName)) {
			config.setAddToCache("false");
		} else
			config.setAddToCache("true");

		config.setTable(table);
		config.setClassName(className);
		config.setPackageName(packageName);
		config.setClassDesc(classDesc);
		// 设置是否有导入按钮，以及导入的权限id.
		config.setCanImport(importFile);
		if (parseString(importFile) > 0)
			config.setImportRole(importFile);
		else
			config.setImportRole("");

		config.setCanComplexQuery(complexQuery);

		config.setCanExport(exportFile);
		if (parseString(exportFile) > 0)
			config.setExportRole(exportFile);
		else
			config.setExportRole("");

		config.setCanAdd(add);
		if (parseString(add) > 0)
			config.setAddRole(add);
		else
			config.setAddRole("");

		config.setCanUpdate(update);
		if (parseString(update) > 0)
			config.setUpdateRole(update);
		else
			config.setUpdateRole("");

		config.setCanDelete(delete);
		if (parseString(delete) > 0)
			config.setDeleteRole(delete);
		else
			config.setDeleteRole("");

	}

	/**
	 * 设置各个节点的对应属性
	 * 
	 * @param config
	 * @param e
	 */
	private void setColumnModelAttrs(ColumnModel config, Element e) {
		config.setNodeType(e.getName());
		// 节点汉字注释
		config.setDesc(getNodeAttr(e,"desc"));
		// 最大长度
		config.setMaxLength(getNodeAttr(e,"maxLength"));
		// 来自业务字典表的表名
		config.setFromTable(getNodeAttr(e,"fromTable"));
		// 来自业务字典表的id列
		config.setIdCoulmn(getNodeAttr(e,"idCoulmn"));
		// 使用缓存id的键.
		config.setUseCacheId(getNodeAttr(e,"useCacheId"));
		// 来自业务字典表的name列
		config.setNameColumn(getNodeAttr(e,"nameColumn"));
		// 设置复杂搜索类型
		config.setComplexQueryType(getNodeAttr(e,"complexQueryType"));
		// 下拉菜单是否含有全选按钮
		config.setAllSelect(getNodeAttr(e,"allSelect"));
		// 最短长度
		config.setMinLength(getNodeAttr(e,"minLength"));
		// 是否可以导入该字段
		config.setCanImport(getNodeAttr(e,"canImport"));
		// 数据库中非空字段
		config.setNotNullInDb(getNodeAttr(e,"notNullInDb"));
		// 是否可以导出
		config.setCanExport(getNodeAttr(e,"canExport"));
		// 列属性名
		config.setCols(getNodeAttr(e,"cols"));
		// 数据的最大长度
		String size = getNodeAttr(e,"size");
		if (isEmpty(size))
			size = "30";
		config.setSize(size);
		// 列属性名
		config.setRows(getNodeAttr(e,"rows"));
		// 下拉菜单对应的文本的值
		config.setValues(getNodeAttr(e,"values"));
		// 下拉菜单的对应的文本的显示值
		config.setNames(getNodeAttr(e,"names"));
		// 浏览框设置的对应的url
		config.setBrowerUrl(getNodeAttr(e,"browerUrl"));
		// 设置为选择下拉框
		config.setBrower(getNodeAttr(e,"brower"));
		// 列属性名
		config.setName(getNodeAttr(e,"name"));
		// 是否要检索
		config.setQuery(getNodeAttr(e,"query"));
		// 列类型--对应数据库
		config.setColumnType(getNodeAttr(e,"columnType"));
		// 列属性名
		config.setName(getNodeAttr(e,"name"));
		// 是否模糊匹配
		config.setQuerylike(getNodeAttr(e,"querylike"));
		// 显示在list.jsp里面的列宽度
		config.setWidth(getNodeAttr(e,"width"));
		// 是否在list.jsp里面列表中显示出来
		config.setVisible(getNodeAttr(e,"visible"));
		// 对应数据库中的列名
		config.setColumn(getNodeAttr(e,"column"));
		// 类型
		String type = getNodeAttr(e,"type");
		config.setType(type);
		// 显示的类型
		String showType = getNodeAttr(e,"showType");
		if (showType == null)
			showType = type;
		config.setShowType(showType);
		// 对应的系统业务字段的id
		config.setSelectCode(getNodeAttr(e,"selectCode"));
		// 是否必填字段
		config.setNotnull(getNodeAttr(e,"notnull"));
		// 是否在edit.jsp中可以编辑
		config.setNoedit(getNodeAttr(e,"noedit"));
		// 是否在添加页面中可以添加
		config.setNoadd(getNodeAttr(e,"noadd"));
		// 使用当前时间
		config.setCurrentTime(getNodeAttr(e,"currentTime"));
		// 使用当前用户
		config.setCurrentUser(getNodeAttr(e,"currentUser"));
		// 对应的样式
		config.setClas(getNodeAttr(e,"class"));
		// 在数据库里面的字段长度.
		config.setLength(getNodeAttr(e,"length"));
	}

	/**
	 * 解析root节点.
	 * 
	 * @param root
	 * @return
	 */
	private IDatamodelXmlReader parseClass(Element root) {
		List<ColumnModel> attributes = new ArrayList<ColumnModel>();
		Dom4jClassModel model = new Dom4jClassModel();
		try { 
			setModelAttrs(model,root);
			/**
			 * 遍历子节点
			 */
			List<Element> list = root.elements();
			for (Element e : list) {
				ColumnModel config = new ColumnModel();
				if ("id".equals(e.getName())) {
					config.setIskey("true");
					model.setKeyDesc(getNodeAttr(e,"desc"));
					model.setKeyName(getNodeAttr(e,"name"));
					model.setKeyColumn(getNodeAttr(e,"column"));
					model.setKeyType(getNodeAttr(e,"type"));
					model.setKeyColumnType(getNodeAttr(e,"columnType"));
				} else {
					config.setIskey("false");
				}
				setColumnModelAttrs(config, e);
				attributes.add(config);
			}
			model.setAttributes(attributes);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return model;
	}

	@Override
	public List<IDatamodelXmlReader> parseClasses() {
		List<IDatamodelXmlReader> ans = new ArrayList<IDatamodelXmlReader>();
		try {
			Document doc = read(this.fileName);
			Element root = getRootElement(doc);
			List<Element> list = root.elements();
			for (Element e : list) {
				IDatamodelXmlReader ir = parseClass(e);
				ans.add(ir);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ans;
	}

	/**
	 * 解析第一个class节点.
	 * 
	 * @return
	 */
	public IDatamodelXmlReader parse() {
		IDatamodelXmlReader model = new Dom4jClassModel();
		try {
			Document doc = read(this.fileName);
			Element root = getRootElement(doc);
			model = parseClass(root);
			System.out.println(model.getKeyType());
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
