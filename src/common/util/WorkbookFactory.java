package common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookFactory {
	public static Workbook create(POIFSFileSystem fs) throws IOException {
		return new HSSFWorkbook(fs);
	}

	public static Workbook create(OPCPackage pkg) throws IOException {
		return new XSSFWorkbook(pkg);
	}

	public static Workbook create(InputStream inp) throws Exception {
		if (!inp.markSupported()) {
			inp = new PushbackInputStream(inp, 8);
		}

		if (POIFSFileSystem.hasPOIFSHeader(inp)) {
			return new HSSFWorkbook(inp);
		}
		try {
			if (POIXMLDocument.hasOOXMLHeader(inp)) {
				return new XSSFWorkbook(OPCPackage.open(inp));
			}
		} catch (Exception e) {
			System.out.println("输入的不是excel2003文件");
			throw new Exception("你输入的excel不是合法的excel文件!");
		} 
		return null;
	}
}
