package dwz.framework.utils.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.util.XMLErrorHandler;

public class XmlUtil {
	public static List<XSDErrorVO> validateXmlByXsd(String xsdFilePath,
			String xmlFilePath) {
		SAXReader xmlReader = new SAXReader();
		try {
			Document xmlDocument = (Document) xmlReader.read(new File(
					xmlFilePath));
			return validateXmlByXsd(xsdFilePath, xmlDocument);
		} catch (Exception ex) {
			System.out.println("XML鏂囦欢: " + xmlFilePath + " 阃氲绷XSD鏂囦欢:"
					+ xsdFilePath + "妫€楠屽け璐ャ€俓n铡熷洜锛?" + ex.getMessage());
			ex.printStackTrace();
			return new ArrayList<XSDErrorVO>();
		}
	}

	public static List<XSDErrorVO> validateXmlByXsd(String xsdFilePath, Document xmlDocument) {
		List<XSDErrorVO> xsdErrorVOList = new ArrayList<XSDErrorVO>();

		try {
			// 鍒涘缓榛樿镄刋ML阌栾澶勭悊鍣?
			XMLErrorHandler errorHandler = new XMLErrorHandler();

			// 銮峰彇鍩轰簬 SAX 镄勮В鏋愬櫒镄勫疄渚?
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 瑙ｆ瀽鍣ㄥ湪瑙ｆ瀽镞堕獙璇?XML 鍐呭銆?
			factory.setValidating(true);
			// 鎸囧畾鐢辨浠ｇ爜鐢熸垚镄勮В鏋愬櫒灏嗘彁渚涘 XML 鍚岖О绌洪棿镄勬敮鎸并€?
			factory.setNamespaceAware(true);
			// 浣跨敤褰揿墠閰岖疆镄勫伐铡傚弬鏁板垱寤?SAXParser 镄勪竴涓柊瀹炰緥銆?
			SAXParser parser = factory.newSAXParser();
			// 鍒涘缓涓€涓鍙栧伐鍏?
			
			// 璁剧疆 XMLReader 镄勫熀纭€瀹炵幇涓殑鐗瑰畾灞炴€с€傛牳蹇冨姛鑳藉拰灞炴€у垪琛ㄥ彲浠ュ湪
			// [url]http://sax.sourceforge.net/?selected=get-set[/url] 涓垒鍒般€?
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					new File(xsdFilePath).toURL().toString());
			// 鍒涘缓涓€涓猄AXValidator镙￠獙宸ュ叿锛屽苟璁剧疆镙￠獙宸ュ叿镄勫睘镐?
			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			// 璁剧疆镙￠獙宸ュ叿镄勯敊璇鐞嗗櫒锛屽綋鍙戠敓阌栾镞讹紝鍙互浠庡鐞嗗櫒瀵硅薄涓缑鍒伴敊璇俊鎭€?
			validator.setErrorHandler(errorHandler);

			// 镙￠獙
			validator.validate(xmlDocument);

			if (errorHandler.getErrors().hasContent()) {
				// 楠岃瘉澶辫触
				String strErrors = errorHandler.getErrors().asXML();
				Document errorDocument = DocumentHelper.parseText(strErrors);
				xsdErrorVOList = getErrors(errorDocument);
			}
			return xsdErrorVOList;
				
		} catch (Exception ex) {
			return xsdErrorVOList;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<XSDErrorVO> getErrors(Document errorDoc) {
		if (errorDoc == null) {
			throw new java.lang.IllegalArgumentException();
		}
		List<XSDErrorVO> xsdErrorVOList = new ArrayList<XSDErrorVO>();
		Element errorsElement = errorDoc.getRootElement();
		Iterator<Element> elementIterator = (Iterator<Element>) errorsElement
				.elementIterator("error");
		while (elementIterator.hasNext()) {
			Element errorElement = elementIterator.next();
			String errorMsg = errorElement.getTextTrim();
			String columnAttr = errorElement.attributeValue("column");
			String lineAttr = errorElement.attributeValue("line");
			XSDErrorVO xsdErrorVO = new XSDErrorVO(errorMsg, Integer
					.parseInt(columnAttr), Integer.parseInt(lineAttr));
			xsdErrorVOList.add(xsdErrorVO);
		}
		return xsdErrorVOList;
	}

	
	public static void main(String[] args) {

		String systemDir = System.getProperty("user.dir");
		String xmlPath = systemDir + "/doc/ws_xml/RdSapRequest.xml";
		String xsdPath = systemDir + "/doc/ws_xml/RdSapRequest.xsd";
		
		List<XSDErrorVO> list = validateXmlByXsd(xsdPath, xmlPath);
		for (XSDErrorVO vo:list){
			System.out.println(vo.getColumn());
			System.out.println(vo.getLine());
			System.out.println(vo.getErrorMsg());
		}
		
//		SAXReader xmlReader = new SAXReader();
//		try {
//			Document document = xmlReader.read(new ByteArrayInputStream(FileUtil.getFileContent(xmlPath).getBytes()));
//
//			List<XSDErrorVO> list = validateXmlByXsd(xsdPath, document);
//			for (XSDErrorVO vo:list){
//				System.out.println(vo.getColumn());
//				System.out.println(vo.getLine());
//				System.out.println(vo.getErrorMsg());
//			}
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
	}

}
