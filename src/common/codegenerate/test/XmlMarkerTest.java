package common.codegenerate.test;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import common.codegenerate.Generate;

/**
 * 解析xml文档的例子.
 * 
 * @author lisq
 * 
 */
public class XmlMarkerTest {
	public static void main(String[] agrgs) {
		Writer out = new OutputStreamWriter(System.out);
//		new Generate("book.ftl", out) {
//			public Object setData() {
//				Map m = new HashMap();
//				try {
//					m.put("doc", freemarker.ext.dom.NodeModel.parse(new File(
//							"F:\\github\\dwz_money\\template\\data.xml")));
//					// "D:\\My Documents\\GitHub\\dwz_money\\template\\data.xml")));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				return m;
//			}
//		}.make();
		System.out.println("\n\n生成完毕");
	}
}
