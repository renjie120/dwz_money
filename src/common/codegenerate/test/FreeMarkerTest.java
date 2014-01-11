package common.codegenerate.test;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import common.codegenerate.Generate;
import common.codegenerate.IndexOfMethod;
import common.codegenerate.UpperDirective;

public class FreeMarkerTest {
	public static void main(String[] agrgs) {
//		Writer out = null;
//		try {
//			out = new FileWriter("d:\\out.txt");
//		} catch (IOException e) { 
//			e.printStackTrace();
//		}
		Writer out = new OutputStreamWriter(System.out);
//		new Generate("test1.ftl", out) {
//			public Object setData() {
//				Map m = new HashMap();
//				m.put("name", "李水清");
//				m.put("email", "llll@23.com");
//				//下面演示使用自定义函数
//				m.put("indexOf", new IndexOfMethod());
//				//下面演示使用自定义指令.
//				m.put("upper", new UpperDirective());
//				return m;
//			}
//		}.make();
		System.out.println("生成完毕");
	}
}
