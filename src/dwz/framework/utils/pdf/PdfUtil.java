package dwz.framework.utils.pdf;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import dwz.framework.utils.FileUtil;

import com.lowagie.text.Element;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

public class PdfUtil {
	public static void buildPDFWater(String srcPath,
			String destPath, String waterText) {
		try {
			PdfReader reader = new PdfReader(srcPath);
			int n = reader.getNumberOfPages();
			PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
					destPath));
			int i = 0;
			// Image img = Image.getInstance("d:/WAV.png");
			// img.setAbsolutePosition(200, 400);
			BaseFont base = BaseFont.createFont(BaseFont.HELVETICA_BOLD,
					"utf-8", BaseFont.EMBEDDED);

			while (i < n) {
				i++;
				PdfContentByte under = stamp.getUnderContent(i);
				Rectangle pageSize = reader.getPageSizeWithRotation(i);
				// under.addImage(img);
				under.beginText();
				under.setFontAndSize(base, 100);
				under.setColorFill(new Color(210, 211, 213));
//				under.showTextAligned(Element.ALIGN_LEFT, waterText, 140, 180, 45);
				under.showTextAligned(Element.ALIGN_LEFT, waterText, 180, 180, 45);
				under.endText();
			}
			stamp.close();
		} catch (Exception de) {
			de.printStackTrace();
		}
	}

	public static void xhtml2Pdf(String basePath, String pdfPath,
			File xhtmlFile) {
		System.out.println("basePath: "+basePath);
		System.out.println("pdfPath: "+pdfPath);
		OutputStream os = null;
		try {
			os = new FileOutputStream(pdfPath);
			ITextRenderer renderer = new ITextRenderer();
//			ITextUserAgent callback = new ITextUserAgent(renderer.getOutputDevice());
//			callback.setSharedContext(renderer.getSharedContext());
//			renderer.getSharedContext().setUserAgentCallback(callback);

			ITextFontResolver fontResolver = renderer.getFontResolver();
			fontResolver.addFontDirectory(basePath + "/fonts",
					BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

			renderer.setDocument(xhtmlFile);
			renderer.layout();
			renderer.createPDF(os, true);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void xhtml2Pdf(String basePath, String pdfPath,
			String content) {

		File tmpFile = new File(basePath + "/" + UUID.randomUUID()+".html");
		
		FileUtil.setFileContent(tmpFile.getPath(), content);
		xhtml2Pdf(basePath, pdfPath, tmpFile);

		tmpFile.delete();
	}
}
