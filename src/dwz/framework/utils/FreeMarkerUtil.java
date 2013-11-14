package dwz.framework.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class FreeMarkerUtil {

	private static Log log = LogFactory.getLog(FreeMarkerUtil.class);

//	private static final String LT = "<";
//	private static final String LT_CHAR = "&lt;";
//	private static final String GT = ">";
//	private static final String GT_CHAR = "&gt;";
//	private static final String AMP = "&";
//	private static final String AMP_CHAR = "&amp;";
//	private static final String APOS = "'";
//	private static final String APOS_CHAR = "&apos;";
//	private static final String QUOT = "&quot;";
//	private static final String QUOT_CHAR = "\"";

	/**
	 * Template to String Method Note
	 * 
	 * @param templateContent
	 *            template content
	 * @param map
	 *            tempate data map
	 * @return
	 */
	public static String template2String(String templateContent,
			Map<String, Object> map, boolean isNeedFilter) {
		if (StringUtils.isBlank(templateContent)) {
			return null;
		}
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		Map<String, Object> newMap = new HashMap<String, Object>();

		Set<String> keySet = map.keySet();
		if (keySet != null && keySet.size() > 0) {
			for (String key : keySet) {
				Object o = map.get(key);
				if (o != null) {
					if (o instanceof String) {
						String value = o.toString();
						if (value != null)
							value = value.trim();
						if (isNeedFilter){
//							value = HtmlUtils.htmlEscape(value);
							value = StringEscapeUtils.escapeXml(value);
						}
						newMap.put(key, value);
					} else {
						newMap.put(key, o);
					}
				}
			}
		}
		Template t = null;
		try {
			t = new Template("", new StringReader(templateContent),
					new Configuration());
			StringWriter writer = new StringWriter();
			t.process(newMap, writer);
			return writer.toString();
		} catch (IOException e) {
			log.error("TemplateUtil -> template2String IOException.");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (TemplateException e) {
			log.error("TemplateUtil -> template2String TemplateException.");
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if (newMap != null) {
				newMap.clear();
				newMap = null;
			}
		}

	}

//	public static void main(String[] args) {
//		String str = "芒'\"<>,.&";
//		System.out.println(StringEscapeUtils.escapeXml(str));
//	}

//	protected static String filterXmlString(String str) {
//		str = str.replaceAll(LT, LT_CHAR);
//		str = str.replaceAll(GT, GT_CHAR);
//		str = str.replaceAll(AMP, AMP_CHAR);
//		str = str.replaceAll(APOS, APOS_CHAR);
//		str = str.replaceAll(QUOT, QUOT_CHAR);
//		return str;
//	}
}
