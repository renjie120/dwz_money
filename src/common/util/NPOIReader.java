package common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 读取POI的类.支持excel2007有效. 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class NPOIReader extends ExcelTool {
	private Hashtable ht = null;
	/**
	 * 工作簿
	 */
	private Workbook workbook;

	/**
	 * 日志记录
	 */
	private Logger log;

	/**
	 * excel表
	 */
	private Sheet sheet;

	/**
	 * 当前读取的sheet的页码
	 */
	private int currentSheetNum;
	private int currentRow;
	private int currentCell;

	/**
	 * excel文件流
	 */
	private FileInputStream fis;

	/**
	 * 消息
	 */
	private StringBuffer msg = null;

	/**
	 * 初始化对象.
	 * 
	 * @param exl
	 * @throws Exception 
	 */
	public NPOIReader(File exl) throws Exception {
		fis = new FileInputStream(exl);
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		msg = new StringBuffer();
		ht = new Hashtable();
	}

	public NPOIReader(String exlFileName) throws IOException {
		File file = new File(exlFileName);
		fis = new FileInputStream(file);
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		msg = new StringBuffer();
		ht = new Hashtable();
	}

	public NPOIReader(InputStream input) throws Exception {
		try {
			workbook = WorkbookFactory.create(input);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		msg = new StringBuffer();
		ht = new Hashtable();
	}

	public void destory() {
		try {
			msg = null;
			if (fis != null)
				fis.close();
		} catch (Exception ex) {
			msg.append(ex.getMessage());
		}
	}

	public String getSheetName(int num) {
		setCurrentSheet(num);
		String result = (String) ht.get("sheetName_" + num);
		if (result == null) {
			result = sheet.getSheetName();
			ht.put("sheetName_" + num, result);
		}
		return result;
	}

	public boolean setCurrentSheet(int num) {
		if (workbook != null && num < workbook.getNumberOfFonts()) {
			try {
				sheet = (Sheet) ht.get("sheet_" + num);
				if (sheet == null) {
					sheet = workbook.getSheetAt(num);
					if (sheet != null)
						ht.put("sheet_" + num, sheet);
				}
				currentSheetNum = num;
				return true;
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 可以看到根据下面的五个参数确定了一个excel文件页面里面的一个矩形区域。 
	 * @param sheetNum
	 *            文件的页面
	 * @param firstRowNum
	 *            第一行的行数
	 * @param lastRowNum
	 *            最后一行的行数
	 * @param firstColIndex
	 *            第一列的列数
	 * @param lastColIndex
	 *            最后一列的列数
	 * @return
	 */
	public String[][] readArea(int sheetNum, int firstRowNum, int lastRowNum,
			int firstColIndex, int lastColIndex) {
		String[][] cells = null;
		if (setCurrentSheet(sheetNum)) {
			int rLen = lastRowNum - firstRowNum + 1;
			int cLen = lastColIndex - firstColIndex + 1;
			cells = new String[rLen][cLen];
			for (int c1 = 0; c1 < rLen; c1++) {
				for (int c2 = 0; c2 < cLen; c2++) {
					try {
						cells[c1][c2] = getCellAsStringByIndex(
								c1 + firstRowNum, c2 + firstColIndex);
					} catch (Exception e) {
						e.printStackTrace();
						cells[c1][c2] = "";
					}
				}
			}
		}
		return cells;
	}

	private Row getRow(int rowId) {
		String sheetRowIndex = "sheet_" + currentSheetNum + "name_"
				+ sheet.getSheetName() + "_" + rowId;
		Row row = (Row) ht.get(sheetRowIndex);
		if (row == null) {
			row = sheet.getRow(rowId);
			if (row != null)
				ht.put(sheetRowIndex, row);
		}
		return row;
	}

	private Cell getCell(int rowId, int colId) {
		Row row = getRow(rowId);
		String sheetCellIndex = "sheet_" + currentSheetNum + "name_"
				+ sheet.getSheetName() + "_" + rowId + "_" + colId;
		Cell cell = (Cell) ht.get(sheetCellIndex);
		if (cell == null) {
			cell = row.getCell((short) colId);
			if (cell != null)
				ht.put(sheetCellIndex, cell);
		}
		return cell;
	}

	/**
	 * 返回指定位置的单元格.
	 * 
	 * @param rowId
	 *            设置单元格的行
	 * @param colId
	 *            设置单元格的列
	 * @return
	 */
	private String getCellAsStringByIndex(int rowId, int colId) {
		String cellStr = "";
		if (sheet != null && rowId < sheet.getLastRowNum() + 1) {
			try {
				Row row = getRow(rowId);
				if (row != null) {
					if (colId < row.getLastCellNum()) {
						Cell cell = getCell(rowId, colId);
						currentRow = rowId;
						currentCell = colId;
						cellStr = getCellContent(cell);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				cellStr = "";
			}
		}
		return cellStr;
	}

	/**
	 * 得到number单元格的内容.
	 * @param cell
	 * @return
	 */
	private String getNumber(Cell cell) {
		String str = NumberFormat.getNumberInstance().format(
				cell.getNumericCellValue());
		while (str.indexOf(",") > -1) {
			str = str.substring(0, str.indexOf(","))
					+ str.substring(str.indexOf(",") + 1);
		}
		return str;
	}

	/**
	 * 得到日期单元格的内容.
	 * @param cell
	 * @return
	 */
	private String getDate(Cell cell) {
		Date d = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(d);
	}

	private String getCellContent(Cell cell) {
		String cellStr = "";
		if (cell != null) {
			try {
				switch (cell.getCellType()) {
				case 0: {
					// 检查是不是日期类型.
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						cellStr = getDate(cell);
					} else {
						cellStr = getNumber(cell);
						cell.setCellValue(123);
					}
					break;
				}
				case 1: {
					cellStr = cell.getStringCellValue();
					break;
				}
				case 2: {
					String formula = cell.getCellFormula();
					if (formula.indexOf("DATE(") >= 0) {
						cellStr = getDate(cell);
					} else if (formula.indexOf("SUM(") >= 0) {
						cellStr = Double.toString(cell.getNumericCellValue());
					} else if (formula.indexOf("SIN(") >= 0) {
						cellStr = Double.toString(cell.getNumericCellValue());
					} else {
						//是数字的日期格式,就读取日期形式.
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							cellStr = getDate(cell);
						} else {
							try {
								cellStr = cell.getStringCellValue();
							} catch (Exception eee) {
								try {
									cellStr = getNumber(cell);
								} catch (Exception f) {
									error(f);
								}
							}
						}
					}
					break;
				}
				case 3: {
					cellStr = cell.getStringCellValue();
					break;
				}
				case 4: {
					cellStr = Boolean.toString(cell.getBooleanCellValue());
					break;
				}
				default: {
					cellStr = new String("");
				}
					if (cellStr == null) {
						cellStr = "";
					}
				}
			} catch (Exception e) {
				error(e);
				cellStr = "";
			}
		}
		return cellStr;
	}

	private void error(Exception f) {
		f.printStackTrace();
		System.out.println("出现错误!sheet:" + currentSheetNum + ";出错单元格:"
				+ ExcelTool.getCellInfo(currentRow, currentCell));
	}

	public void emptyCell(Cell cell) {
		System.out.println(cell.getCellType());
	}

	public String getErrorMessage() {
		return msg.toString();
	}

	/**
	 * 返回当前的页面
	 * 
	 * @return
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * 返回当前的工作簿
	 * 
	 * @return
	 */
	public Workbook getWorkbook() {
		return workbook;
	}

	/**
	 * 得到excel指定单元格的内容.
	 * @param sheetNum sheet索引.
	 * @param row 行索引.
	 * @param col 列索引.
	 * @return
	 */
	public String readCell(int sheetNum, int row, int col) {
		try {
			setCurrentSheet(sheetNum);
			return getCellAsStringByIndex((row), (col));
		} catch (Exception e) {
			e.printStackTrace();
			return "出现异常，可能是文件未找到！";
		}
	}

	/**
	 * 读取excel文件的一个表格里面的基本信息：最大行数. 
	 * @param sheetNum sheet索引.
	 * @return
	 */
	public int getRowNum(int sheetNum) {
		try {
			setCurrentSheet(sheetNum);
			Sheet sheet = getSheet(); 
			return sheet.getLastRowNum() + 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 得到excel中指定标识列不为空的最大行数.
	 * 从上往下数得到第一个空标识为止的行数.
	 * @param sheetNum sheet索引.
	 * @param idColumnId 标识列索引.
	 * @return
	 */
	public int getRowNum(int sheetNum,int idColumnId) {
		try {
			setCurrentSheet(sheetNum);
			Sheet sheet = getSheet();
			int count= 1;
			for (int start = 0;; start++,count++) {
				Row row = getRow(start);
				if (row != null) { 
					if (idColumnId >-1) {
						Cell c = row.getCell(idColumnId);
						if ("".equals(getCellContent(c))) {
							count = count - 1;
							break;
						}
					}
				}
			} 
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 读取excel中某一个表单的某一行的最大列数.
	 * @param fileName
	 *            文件名
	 * @param sheetNum
	 *            表单的数目
	 * @param row
	 *            行数
	 * @return
	 */
	public String getColNum(int sheetNum, int rowId) {
		Row row;
		int colNum;
		try {
			setCurrentSheet(sheetNum);
			row = getRow(rowId);
			colNum = row.getLastCellNum();
			return new Integer(colNum).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "检查输入的文件是否存在！";
		}
	}

	/**
	 * 获取文件的工作簿的数目.
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public String getSheetNum() {
		try {
			return new Integer(workbook.getNumberOfSheets()).toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "检查输入的文件是否存在！";
		}
	}

	/**
	 * 返回全部的sheet的名称.是集合类型.
	 * @return
	 */
	public List getSheetNames() {
		int num = workbook.getNumberOfSheets();
		List ans = new ArrayList();
		for (int i = 0; i < num; i++) {
			ans.add(workbook.getSheetName(i));
		}
		return ans;
	}

	/**
	 * 返回指定文件的页面的全部数据.
	 * 
	 * @param fileName
	 *            文件名
	 * @param sheetNum
	 *            文件的表数
	 * @return String[][]
	 */
	public String[][] read(int sheetNum) {
		return readWithIdColumn(sheetNum, null, false);
	}

	/**
	 * 返回指定文件的页面的全部数据,只返回标识列为非空的数据..
	 * @param sheetNum sheet索引
	 * @param idColumn 标识列所在索引 
	 * @return
	 */
	public String[][] readWithIdColumn(int sheetNum, int idColumn) {
		return readWithIdColumn(sheetNum, idColumn, true);
	}

	/**
	 * 返回指定文件页面全部数据.
	 * 
	 * @param fileName
	 *            文件名
	 * @param sheetNums
	 *            指定文件的sheetid的集合,对多个id组成的数组.
	 * @return
	 */
	public List<String[][]> read(int[] sheetNums) {
		return read(sheetNums, false);
	}

	/**
	 * 返回指定excel中的指定sheet内容.
	 * @param sheetNum sheet索引.
	 * @param idColumnId  标识列所在的列索引.
	 * @param returnMeetFirstNullRow  是否遇到第一个空行自动返回.
	 * @return
	 */
	public String[][] readWithIdColumn(int sheetNum, Integer idColumnId,
			boolean returnMeetFirstNullRow) {
		int[] sheetNums = new int[] { sheetNum };
		List ans = read(sheetNums, idColumnId, returnMeetFirstNullRow);
		return (String[][]) ans.get(0);
	}

	/**
	 * 返回指定excel中的指定sheet内容.
	 * @param sheetNum sheet索引
	 * @param returnMeetFirstNullRow 是否遇到第一个空行自动返回
	 * @param autoClose 读取完毕是否自动关闭
	 * @return
	 */
	public String[][] read(int sheetNum, boolean returnMeetFirstNullRow,
			boolean autoClose) {
		int[] sheetNums = new int[] { sheetNum };
		List ans = read(sheetNums, null, returnMeetFirstNullRow, autoClose);
		return (String[][]) ans.get(0);
	}

	public String[][] read(int sheetNum, int idColumnId,
			boolean returnMeetFirstNullRow, boolean autoClose) {
		int[] sheetNums = new int[] { sheetNum };
		List ans = read(sheetNums, idColumnId, returnMeetFirstNullRow,
				autoClose);
		return (String[][]) ans.get(0);
	}

	/**
	 * 根据sheet索引和行索引读取一行数据.
	 * @param sheetId
	 * @param rowId
	 * @return 返回字符串数组.
	 */
	public String[] readRow(int sheetId, int rowId) {
		setCurrentSheet(sheetId);
		Row row = getRow(rowId);
		int lastcell = row.getLastCellNum();
		String[] resultStrings = new String[lastcell];
		for (int i = 0; i < resultStrings.length; i++) {
			resultStrings[i] = getCellAsStringByIndex(rowId, i);
		}
		return resultStrings;
	}

	public void printStrArr(String[][] result) {
		int i = result.length;
		for (int j = 0; j < i; j++) {
			for (int k = 0, m = result[j].length; k < m; k++) {
				System.out.println(ExcelTool.getCellInfo(j, k) + ",val = "
						+ result[j][k]);
			}
		}
	}

	public void printStrArr(String[] result) {
		int i = result.length;
		for (int j = 0; j < i; j++) {
			System.out.print(result[j]);
			if (j < i - 1)
				System.out.print(",");
		}
	}

	/**
	 * 是否自动关闭excel.
	 * @param sheetNums
	 * @param returnMeetFirstNullRow
	 * @param autoClose
	 * @return
	 */
	private List read(int[] sheetNums, Integer idColumn,
			boolean returnMeetFirstNullRow, boolean autoClose) {
		// 如果遇到第一个空行自动返回,调用下面的方法.
		List ans = new ArrayList();
		int sheetId;
		Sheet sheet;
		int maxRowNum;
		Row row;
		int maxColNum;
		String[][] result;
		try {
			for (int i = sheetNums.length - 1; i >= 0; i--) {
				result = new String[][] { {} };
				sheetId = sheetNums[i];
				setCurrentSheet(sheetId);
				sheet = getSheet();
				// 如果设置了要遇到第一个空行就自动返回,就计算maxRowNum!
				if (returnMeetFirstNullRow) {
					maxRowNum = 0;
					row = null;
					// 得到从0行开始的第一个非空行数.
					for (;; maxRowNum++) {
						row = getRow(maxRowNum);
						if (row == null) {
							maxRowNum = maxRowNum - 1;
							break;
						} else {
							if (idColumn != null) {
								Cell c = row.getCell(idColumn);
								if ("".equals(getCellContent(c))) {
									maxRowNum = maxRowNum - 1;
									break;
								}
							}
						}
					}
				}
				// 否则直接取全部的excel的行数
				else {
					maxRowNum = sheet.getLastRowNum();
				}
				if (maxRowNum != 0) {
					// 得到第一行的列数.
					row = getRow(0);
					maxColNum = row.getLastCellNum();
					result = readArea(sheetId, 0, maxRowNum, 0, maxColNum - 1);
				}
				ans.add(result);
			}
			if (autoClose)
				destory();
			return ans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 一次返回多个sheet的内容.
	 * 
	 * @param excelRd
	 * @param sheetNums
	 * @param returnMeetFirstNullRow
	 * @return
	 */
	private List read(int[] sheetNums, boolean returnMeetFirstNullRow) {
		return read(sheetNums, null, returnMeetFirstNullRow, true);
	}

	private List<String[][]> read(int[] sheetNums, Integer idColumnId,
			boolean returnMeetFirstNullRow) {
		return read(sheetNums, idColumnId, returnMeetFirstNullRow, true);
	}

	public static String arrarToStr(String[] str) {
		StringBuffer buf = new StringBuffer("[");
		int len1 = str.length;
		for (int i = 0; i < len1; i++) {
			buf.append(str[i]);
			if (i != len1 - 1) {
				buf.append(",");
			} else {
				buf.append("\n");
			}
		}
		buf.append("]");
		return buf.toString();
	}

	public static String arrarToStr(String[][] str) {
		StringBuffer buf = new StringBuffer("[");
		int len1 = str.length;
		int len2 = str[0].length;
		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				buf.append(str[i][j]);
				if (j != len2 - 1) {
					buf.append(",");
				} else {
					buf.append("\n");
				}
			}
		}
		buf.append("]");
		return buf.toString();
	}

	public static void main(String[] arg) {
		try {
			//NPOIReader reader = new NPOIReader("e:\\工资模拟模板.xls");  
			NPOIReader reader = new NPOIReader("d:\\1.xlsx");
			List allSheetname = reader.getSheetNames();
			Iterator it = allSheetname.iterator();
			int count = 1;
			while (it.hasNext()) {
				System.out.println(count++ + ":" + it.next());
			}

			System.out.println("-----得到第一个sheet的第一行 ");
			System.out.println(reader.arrarToStr(reader.readRow(0, 0)));

			System.out.println("-----得到第一个sheet的第一行第一个单元格 ");
			System.out.println(reader.readCell(0, 0, 0));
			System.out.println("--------------------------------------------");

			System.out.println("-----得到第一个sheet的并且只得到id非空的数据! ");
			String[][] result = reader.readWithIdColumn(0, 0);
			System.out.println(reader.arrarToStr(result));

			System.out.println("-----得到第一个sheet的全部数据!-----------------");
			String[][] result2 = reader.read(0);
			System.out.println(reader.arrarToStr(result2));
			
			System.out.println("全部的sheet名:"+reader.getSheetNames());
			
			System.out.println("第一个sheet的行数:"+reader.getRowNum(0));
			
			System.out.println("第一个sheet的行数:"+reader.getRowNum(0,0));

			reader.destory();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}