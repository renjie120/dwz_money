package dwz.framework.utils.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 
 * @author zhanghuihua
 *
 */
public class XlsExport { 
	// private static short XLS_ENCODING = HSSFWorkbook.ENCODING_UTF_16;

	private static enum XlsFormatEm {
		DATE("m/d/yy"), NUMBER("0.00"), CURRENCY("#,##0.00"), PERCENT("0.00%");
		private final String	pattern;

		XlsFormatEm(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return this.pattern;
		}
	}

	private HSSFWorkbook	workbook;

	private HSSFSheet		sheet;

	private HSSFRow			row;

	public XlsExport() {
		this.workbook = new HSSFWorkbook();
		this.sheet = workbook.createSheet();
		this.sheet.setDefaultColumnWidth(15);
	}
	
	public XlsExport(String tem) {
		try {
			InputStream input = new FileInputStream(tem);
			POIFSFileSystem fs = new POIFSFileSystem(input);
			this.workbook = new HSSFWorkbook(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.sheet = workbook.createSheet();
		this.sheet.setDefaultColumnWidth(15);
	}
 
	public void exportXls(String xlsFileName) throws RuntimeException {
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(xlsFileName);
			workbook.write(fOut);
			fOut.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("出错!", e);
		} catch (IOException e) {
			throw new RuntimeException("出错!", e);
		} finally {
			try {
				if (fOut != null)
					fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void exportXls(HttpServletResponse response) throws RuntimeException {
		ServletOutputStream os = null;
		try {
			os = response.getOutputStream();
			workbook.write(os);
			os.flush();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("出错!", e);
		} catch (IOException e) {
			throw new RuntimeException("出错!", e);
		} finally {
			try {
				if (os != null)
					os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	public void createRow(int index) {
		this.row = this.sheet.createRow(index);
	}
 
	public void setCell(int index, String value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		// cell.setEncoding(XLS_ENCODING);
		cell.setCellValue(value);
	}

	public void setCell(int index, Calendar value) {
		if (value != null) {
			setCell(index, value.getTime());
		}
	}

	public void setCell(int index, Date value) {
		if (value != null) {
			HSSFCell cell = this.row.createCell(index);
			// cell.setEncoding(XLS_ENCODING);
			cell.setCellValue(value);
			HSSFCellStyle cellStyle = workbook.createCellStyle(); 
			cellStyle.setDataFormat(HSSFDataFormat
					.getBuiltinFormat(XlsFormatEm.DATE.getPattern()));  

			cell.setCellStyle(cellStyle); 
		}
	}

	public void setCell(int index, int value) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
	}

	private void setCell(int index, double value, XlsFormatEm formatEm) {
		HSSFCell cell = this.row.createCell(index);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(value);
		HSSFCellStyle cellStyle = workbook.createCellStyle();  
		HSSFDataFormat format = workbook.createDataFormat();
		cellStyle.setDataFormat(format.getFormat(formatEm.getPattern())); 
		cell.setCellStyle(cellStyle);  
	}

	public void setCell(int index, double value) {
		setCell(index, value, XlsFormatEm.NUMBER);
	}

	public void setCurrency(int index, double value) {
		setCell(index, value, XlsFormatEm.CURRENCY);
	}

	public void setPercent(int index, double value) {
		setCell(index, value, XlsFormatEm.PERCENT);
	}

}
