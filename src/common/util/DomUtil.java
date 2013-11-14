package common.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 不借助jar包对xml进行解析的小工具.
 * @author renjie120 419723443@qq.com
 *
 */
public class DomUtil {
	protected static Log log = LogFactory.getLog("DomUtil");
	public static void main(String args[]) {
		Document doc;
		Element root;
		String elementname;
		String filename;
		try {
			filename = System.getProperty("user.dir");
			filename = filename + "/WebRoot/WEB-INF/classes/struts.xml";

			doc = getXmlDocument(filename);
			// 获取xml文档的根节点
			// root = getRoot(doc);
			// System.out.println(root.getElementsByTagName("action").getLength());
			// elementname = root.getNodeName();//获得根节点名称
			// System.out.println("输出根节点名称：" + elementname);
			// 打印根节点的属性和值
			// printAllAttributes(root);
			// 打印该文档全部节点
			// System.out.println("打印全部节点");
			// printElement(root, 0);
			NodeList packages = doc.getElementsByTagName("package");
			if (packages != null && packages.getLength() > 0) {
				for (int i = 0; i < packages.getLength(); i++) {
					Node _package = packages.item(i);
					NodeList actions = _package.getChildNodes();
					for (int j = 0; j < actions.getLength(); j++) {
						Node _action = actions.item(j);
						if (_action.getNodeName().equals("action")) {
							if (getAttribute(_action,"name").equals("hello")) {
								NodeList results = _action.getChildNodes();
								for (int k = 0; k < results.getLength(); k++) {
									Node _result = results.item(k);
									if(_result.getNodeName().equals("result")&&getAttribute(_result,"name").equals("success"))
									{}
								}
							}
						}
					}
				}
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	/**
	 * 得到文档对象的根节点.
	 * @param doc 文档对象
	 * @return
	 */
	public static Element getRoot(Document doc){
		return doc.getDocumentElement();
	}
	
	/**
	 * 得到指定节点的指定属性值.
	 * @param node
	 * @param attrName
	 * @return
	 */
	public static String getAttribute(Node node,String attrName){
		if(node.hasAttributes()){
			Node _node = node.getAttributes().getNamedItem(attrName);
			if(_node!=null)
				return _node.getNodeValue();
			else{
				return "";
			}
		}
		else
			return "";
	}
	
	/**
	 * 得到指定节点的文本内容.
	 * @param node
	 * @return
	 */
	public static String getText(Node node){
		return node.getNodeValue();
	}
	
	/**
	 * 根据xml文件地址得到xml对象.
	 * @param fileName xml地址
	 * @return
	 */
	public static Document getXmlDocument(String fileName){
		Document doc = null;
		DocumentBuilderFactory factory;
		DocumentBuilder docbuilder;

		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			// 解析xml文件,生成document对象
			factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			docbuilder = factory.newDocumentBuilder();
			doc = docbuilder.parse(in);			
		} catch (Exception e) {
			log.error("DomUtil---getXmlDocument", e);
		}
		return doc;
	}
	
	/**
	 *  根据xml文件流地址得到xml对象.
	 * @param in
	 * @return
	 */
	public static Document getXmlDocument(InputStream in){
		Document doc = null;
		DocumentBuilderFactory factory;
		DocumentBuilder docbuilder;
		try {
			// 解析xml文件,生成document对象
			factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			docbuilder = factory.newDocumentBuilder();
			doc = docbuilder.parse(in);			
		} catch (Exception e) {
			log.error("DomUtil---getXmlDocument", e);
		}
		return doc;
	}
	
	/**
	 * 打印指定节点的全部属性.
	 * @param elem 节点对象
	 */
	public static void printAllAttributes(Element elem) {
		NamedNodeMap attributes;//根节点所有属性
		int i, max;
		String name, value;
		Node curnode;

		attributes = elem.getAttributes();
		max = attributes.getLength();

		for (i = 0; i < max; i++) {
			curnode = attributes.item(i);
			name = curnode.getNodeName();
			value = curnode.getNodeValue();
			System.out.println("输出节点名称和值：" + name + " = " + value);
		}
	}
	
	/**
	 * 得到指定节点的所有属性,返回结果是一个map对象.
	 * @param elem 节点对象
	 * @return 
	 */
	public static Map getAllAttributes(Element elem) {
		Map map = new HashMap();
		NamedNodeMap attributes;//根节点所有属性
		int i, max;
		String name, value;
		Node curnode;

		attributes = elem.getAttributes();
		max = attributes.getLength();

		for (i = 0; i < max; i++) {
			curnode = attributes.item(i);
			name = curnode.getNodeName();
			value = curnode.getNodeValue();
			map.put(name, value);
		}
		return map;
	}

	/**
	 * 打印节点的所有节点的名称和值.
	 * @param elem 节点对象
	 * @param depth 深度
	 */
	public static void printElement(Element elem, int depth) {
		String elementname;
		NodeList children;
		int i, max;
		Node curchild;
		Element curelement;
		String nodename, nodevalue;

		// elementname = elem.getnodename();
		// 获取输入节点的全部子节点
		children = elem.getChildNodes();

		// 按一定格式打印输入节点
		for (int j = 0; j < depth; j++) {
			//System.out.print(" ");
		}
		printAllAttributes(elem);

		// 采用递归方式打印全部子节点
		max = children.getLength();
		System.out.println("输出子节点的长度：" + elem.getNodeName() + ":::" + max);
		//输出全部子节点
		for (int j = 0; j < max; j++) {
			System.out.println("tt:" + children.item(j));
		}

		for (i = 0; i < max; i++) {

			curchild = children.item(i);

			// 递归退出条件
			if (curchild instanceof Element) {
				curelement = (Element) curchild;
				printElement(curelement, depth + 1);
			} else {
				nodename = curchild.getNodeName();
				nodevalue = curchild.getNodeValue();

				for (int j = 0; j < depth; j++) {
					System.out.print(" ");
					System.out.println(nodename + " = " + nodevalue);
				}
			}
		}
	}
}
