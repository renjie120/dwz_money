package common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.util.CommonUtil;
import common.util.DomUtil;

/**
 * 根据模板生成对应的文件!!
 * 
 * @author lsq
 * 
 */
public class BestGetMyModel extends ChangeText {
	private static String srcDir = "params";

	private String getMethods(Config vo) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		String name = vo.getName();
		String name2 = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (type.equals("string")) {
			buf.append("  private String " + name + ";\n");
			buf.append("  public void set" + name2 + "(String " + name
					+ ") { this." + name + " = " + name + "; }\n ");
			buf.append("  public String get" + name2 + "() {  return " + name
					+ "; }\n");
		} else if (type.equals("int")) {
			buf.append("  private int " + name + ";\n");
			buf.append("  public void set" + name2 + "(int " + name
					+ ") { this." + name + " = " + name + "; } \n");
			buf.append("  public int get" + name2 + "() {  return " + name
					+ "; }\n");
		}
		return buf.toString();
	}

	private String getGetsAndSets() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(getMethods(vo));
		}
		return buf.toString();
	}

	private String getGetMethod(Config vo) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		String name = vo.getName();
		String name2 = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (type.equals("string")) {
			buf.append("  public String get" + name2 + "();\n");
		} else if (type.equals("int")) {
			buf.append("  public int get" + name2 + "(); \n");
		}
		return buf.toString();
	}

	private String getGets() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(getGetMethod(vo));
		}
		return buf.toString();
	}

	private String getGetImplMethod(Config vo) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		String name = vo.getName();
		String name2 = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (type.equals("string")) {
			buf.append("  public String get" + name2
					+ "(){ return this.{2}VO.get" + name2 + "();}\n");
		} else if (type.equals("int")) {
			buf.append("  public int get" + name2 + "(){ return this.{2}VO.get"
					+ name2 + "();}\n");
		}
		return buf.toString();
	}

	private String getGetImpls() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(getGetImplMethod(vo));
		}
		return buf.toString();
	}

	private String getAllFieldDescs() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append("<th width=\"100\">" + vo.getDesc() + "</th>\n");
		}
		return buf.toString();
	}

	private String getAllFieldLists() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append("<td><s:property value=\"" + vo.getName()
					+ "\"/></td>\n");
		}
		return buf.toString();
	}

	private String getAllFields() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append("<div class=\"unit\"><label>" + vo.getDesc()
					+ ":</label>" + getFieldHtml(vo, false) + "</div>\n");
		}
		return buf.toString();
	}

	private String getAllEditFields() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			if (!vo.getNodeType().equals("id"))
				buf.append("<div class=\"unit\"><label>" + vo.getDesc()
						+ ":</label>" + getFieldHtml(vo, true) + "</div>\n");
		}
		return buf.toString();
	}

	private String getHiddenField() {
		StringBuffer buf = new StringBuffer();
		buf
				.append("<input type='hidden' name=\"" + idKey
						+ "\" value=\"<s:property value=\"{2}Vo." + idKey
						+ "\"/>\">\n");
		return buf.toString();
	}

	private String getFieldHtml(Config vo, boolean canEdit) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		String name = vo.getName();
		String name3 = name.toUpperCase();
		String length = CommonUtil.notBlank(vo.getLength(), "");
		String clas = CommonUtil.notBlank(vo.getClas(), "textInput");
		String valueStr = "";
		if (canEdit) {
			if (!clas.equals("editor"))
				valueStr = " value=\"<s:property value=\"{2}Vo." + vo.getName()
						+ "\"/>\" ";
			else
				valueStr = "<s:property value=\"{2}Vo." + vo.getName() + "\"/>";
		}
		if (clas.equals("textInput")) {
			buf.append("<input name=\"" + name
					+ "\" class=\"textInput\" type=\"text\" " + valueStr
					+ "/>\n");
		} else if (clas.equals("number")) {
			if (!CommonUtil.isEmpty(length))
				length = " length=\"" + length + "\" ";
			buf.append("<input name=\"" + name
					+ "\" class=\"number\" type=\"text\" " + length + ""
					+ valueStr + "/>\n");
		} else if (clas.equals("date")) {
			buf
					.append("<input type=\"text\" name=\""
							+ name
							+ "\" class=\"date\" size=\"30\" "
							+ valueStr
							+ "/>"
							+ "<a class=\"inputDateButton\" href=\"javascript:;\">选择</a>\n");
		} else if (clas.equals("editor")) {
			buf.append("<textarea class=\"editor\" name=\"" + name
					+ "\" rows=\"15\" cols=\"80\">" + valueStr
					+ "</textarea>\n");
		}
		return buf.toString();
	}

	private String getSql(Config vo) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		String name = vo.getName();
		String name3 = name.toUpperCase();
		if (type.equals("string")) {
			buf.append("  case " + name3 + ":\n");
			buf
					.append("sb.append(count == 0 ? \" where\" : \" and\").append(\" {2}."
							+ name + "like ? \");  ");
			buf
					.append("				argList.add(entry.getValue());                                          ");
			buf
					.append("				count++;                                                                ");
			buf
					.append("				break;                                                                  ");
		} else if (type.equals("int")) {
			buf.append("  case " + name3 + ":\n");
			buf
					.append("sb.append(count == 0 ? \" where\" : \" and\").append(\" {2}."
							+ name + "=? \");  ");
			buf
					.append("				argList.add(entry.getValue());                                          ");
			buf
					.append("				count++;                                                                ");
			buf
					.append("				break;                                                                  ");
		}
		return buf.toString();

	}

	private String getSearchSql() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(getSql(vo));
		}
		return buf.toString();
	}

	private String getSearchFields() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(vo.getName().toUpperCase() + ",");
		}
		return changeStr2(buf.toString());
	}

	private void generate() {
		String newDirName = entryName.toLowerCase();
		File f = new File(newDirName + "/");
		f.mkdirs();
		File srcDirFile = new File(srcDir);
		System.out.println(srcDirFile.getAbsolutePath());
		File[] childFiles = srcDirFile.listFiles();
		String newClassName = entryName;
		for (File fi : childFiles) {
			String newFileName = fi.getName().replace("Param", newClassName);
			saveNewFile(readModel(fi), f.getPath() + "/" + newFileName);
		}
	}

	public static final int strLength = 4;

	public static String FormatButtonText(String targetStr) {
		int curLength = targetStr.getBytes().length;
		if (targetStr != null && curLength > strLength) {
			return targetStr;
		}
		String newString = "";
		int cutLength = strLength - targetStr.getBytes().length;
		for (int i = 0; i < cutLength; i++)
			newString += "0";
		return newString + targetStr;
	}

	public static String SubStringByte(String targetStr) {
		while (targetStr.getBytes().length > strLength)
			targetStr = targetStr.substring(0, targetStr.length() - 1);
		return targetStr;
	}

	List attributes = null;
	String idKey = "";
	String entryName = "";
	String entryDesc = "";
	String idColumn = "";
	String idType = "";
	String tableName = "";

	public void parseXml(String fileName) {
		attributes = new ArrayList();
		Document doc = DomUtil.getXmlDocument(fileName);
		Node list = doc.getElementsByTagName("class").item(0);
		tableName = DomUtil.getAttribute(list, "table");
		entryName = DomUtil.getAttribute(list, "name");
		entryDesc = DomUtil.getAttribute(list, "desc");
		NodeList children = list.getChildNodes();
		for (int j = 0; j < children.getLength(); j++) {
			Node child = children.item(j);
			if ("id".equals(child.getNodeName())
					|| "property".equals(child.getNodeName())) {
				Config config = new Config();
				if ("id".equals(child.getNodeName())) {
					idKey = DomUtil.getAttribute(child, "name");
					idColumn = DomUtil.getAttribute(child, "column");
					idType = DomUtil.getAttribute(child, "type");
				}
				config.setNodeType(child.getNodeName());
				config.setDesc(DomUtil.getAttribute(child, "desc"));
				config.setName(DomUtil.getAttribute(child, "name"));
				config.setColumn(DomUtil.getAttribute(child, "column"));
				config.setClas(DomUtil.getAttribute(child, "class"));
				config.setLength(DomUtil.getAttribute(child, "length"));
				config.setType(DomUtil.getAttribute(child, "type"));
				attributes.add(config);
			}
		}
	}

	private String getAllf() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(vo.getName() + ",");
		}
		return changeStr2(buf.toString());
	}

	private String getAllfNoid() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			if (!vo.getNodeType().equals("id"))
				buf.append(vo.getName() + ",");
		}
		return changeStr2(buf.toString());
	}

	private String getFieldDesc(Config vo) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		String name = vo.getName();
		String name3 = name.toUpperCase();
		if (type.equals("string")) {
			buf.append(" String " + vo.getName());
		} else if (type.equals("int")) {
			buf.append(" int " + vo.getName());
		}
		return buf.toString();
	}

	private String getAllf2() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(getFieldDesc(vo) + ",");
		}
		return changeStr2(buf.toString());
	}

	private String getHibernate(Config vo) {
		StringBuffer buf = new StringBuffer();
		String type = vo.getType();
		if (type.equals("string")) {
			type = " type=\"string\" ";
		} else if (type.equals("int")) {
			type = " type=\"int\" ";
		} else
			type = "";
		buf.append("<property name=\"" + vo.getName() + "\" column=\""
				+ vo.getColumn() + "\" " + type + "/>\n");
		return buf.toString();
	}

	private String getHibernateConfig() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			if (!vo.getNodeType().equals("id"))
				buf.append(getHibernate(vo));
		}
		return buf.toString();
	}

	private String getAllfNoid2() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			if (!vo.getNodeType().equals("id"))
				buf.append(getFieldDesc(vo) + ",");
		}
		return changeStr2(buf.toString());
	}

	private String getAllf3() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			buf.append(" this." + vo.getName() + "=" + vo.getName() + ";\n");
		}
		return buf.toString();
	}

	private String getAllfNoid3() {
		StringBuffer buf = new StringBuffer();
		Iterator it = attributes.iterator();
		while (it.hasNext()) {
			Config vo = (Config) it.next();
			if (!vo.getNodeType().equals("id"))
				buf
						.append(" this." + vo.getName() + "=" + vo.getName()
								+ ";\n");
		}
		return buf.toString();
	}

	/**
	 * 字符串转换：AbcAbc -->abcAbc
	 * 
	 * @param str
	 * @return
	 */
	private String changeStr1(String str) {
		return str.substring(0, 1).toLowerCase()
				+ str.substring(1, str.length());
	}

	/**
	 * 删除字符串的最后一个，
	 * 
	 * @param str
	 * @return
	 */
	private String changeStr2(String str) {
		if (str.length() > 0)
			return str.substring(0, str.length() - 1);
		return "";
	}

	/**
	 * abcAbc-->AbcAbc
	 * 
	 * @param str
	 * @return
	 */
	private String changeStr3(String str) {
		return str.substring(0, 1).toUpperCase()
				+ str.substring(1, str.length());
	}

	@Override
	String changeString(String oldStr) {
		String modelName = entryName;
		String des = entryDesc;
		String lower = modelName.toLowerCase();
		String changeFirstLower = changeStr1(modelName);
		return oldStr.replaceAll("\\{idkey\\}", idKey).replaceAll(
				"\\{idkey2\\}", changeStr3(idKey)).replaceAll(
				"\\{tablename\\}", tableName).replaceAll("\\{idcolumn\\}",
				idColumn).replaceAll("\\{idtype\\}", idType).replaceAll(
				"\\{10\\}", getHibernateConfig()).replaceAll("\\{9\\}",
				getAllf3()).replaceAll("\\{8\\}", getAllfNoid3()).replaceAll(
				"\\{7\\}", getAllf2()).replaceAll("\\{6\\}", getAllfNoid2())
				.replaceAll("\\{5\\}", getAllf()).replaceAll("\\{4\\}",
						getAllfNoid()).replaceAll("\\{hidden\\}",
						getHiddenField()).replaceAll("\\{alleditfields\\}",
						getAllEditFields()).replaceAll("\\{allfields\\}",
						getAllFields()).replaceAll("\\{allfieldlists\\}",
						getAllFieldLists()).replaceAll("\\{allfielddescs\\}",
						getAllFieldDescs()).replaceAll("\\{getimpl\\}",
						getGetImpls()).replaceAll("\\{allgets\\}", getGets())
				.replaceAll("\\{searchsql\\}", getSearchSql()).replaceAll(
						"\\{1\\}", modelName).replaceAll("\\{2\\}",
						changeFirstLower).replaceAll("\\{3\\}", lower)
				.replaceAll("\\{msg\\}", des).replaceAll("\\{getsAndSets\\}",
						getGetsAndSets()).replaceAll("\\{fields\\}",
						getSearchFields());
	}

	public static void main(String[] args) {
		BestGetMyModel test = new BestGetMyModel();
		String fileName = "datamodle.xml";
		test.parseXml(fileName);
		test.generate();
		System.out.println("生成完毕!");
	}
}
