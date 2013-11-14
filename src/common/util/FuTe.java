package common.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FuTe {
	// 车型号所在的列位置
	private static final String CARCOLUMN = "B";
	// 今日销量所在列
	private static final String TODAYCOLUMN = "Q";
	// 本月累计所在列
	private static final String BENYUELEIJI = "R";
	// 车型及行号的对应关系.下面的三个map对应的Key都是一样的，都是“单元格位置+车型名称”
	private Map carToRow;
	// 得到旧的今天销量
	private Map oldTodayOfCar;
	// 得到旧的本月累计销量
	private Map oldBenyueOfCar;
	private POIReader reader;
	private String fileName;

	public FuTe(String fileName) {
		try {
			this.fileName = fileName;
			carToRow = new HashMap();
			oldTodayOfCar = new HashMap();
			oldBenyueOfCar = new HashMap();
			reader = new POIReader(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FuTe(File f) {
		try {
			this.fileName = f.getName();
			carToRow = new HashMap();
			oldTodayOfCar = new HashMap();
			oldBenyueOfCar = new HashMap();
			reader = new POIReader(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public POIReader getReader() {
		return reader;
	}

	private boolean checkIsCar(String carno) {
		if ("".equals(carno) || carno.indexOf("Dealer") != -1
				|| carno.indexOf("Mondeo") != -1
				|| carno.indexOf("Focus") != -1 || carno.indexOf("MAX") != -1
				|| carno.indexOf("Fiesta") != -1
				|| carno.indexOf("Total") != -1) {
			return false;
		}
		return true;
	}

	/**
	 * 得到某个sheet里面的车型的行号.
	 * 
	 * @param sheetNum
	 */
	private Map getAnayCarToRow(int sheetNum, int startRow, int endRow) {
		String sheetName = reader.getSheetName(sheetNum);
		// 这里不是针对每个sheet都重复计算车型对应的行号！！因为每个excel都是一样的！！如果不一样就要去掉这里的判断！！
		if (carToRow.keySet().size() != 0)
			return carToRow;
		int cellNum = reader.getNumFromExcelStr(CARCOLUMN);
		for (int i = startRow; i < endRow; i++) { 
			// 得到车型的描述信息。
			String cellValue = reader.getCellAsStringByIndex(sheetNum, i,
					cellNum);
			if (checkIsCar(cellValue)) {
				carToRow.put(reader.getCellInfo(i, cellNum) + "_" + cellValue,
						i);
			}
		}
		return carToRow;
	}

	/**
	 * 打印map的键值对.
	 * 
	 * @param m
	 */
	private void printMap(Map m) {
		Set enteySet = m.entrySet();
		Iterator it = enteySet.iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			System.out.println((String) e.getKey() + "--" + e.getValue());
		}
	}

	/**
	 * 得到指定的今日销量的map.
	 * 
	 * @param sheetNum
	 */
	public Map getOldCarTodayMap(int sheetNum,int cellNum) {
		String sheetName = reader.getSheetName(sheetNum); 
		//得到正在处理的车型的描述。用来和carToRow里面的车型描述进行比较用!看是不是一样。
		int chexingcell = reader.getNumFromExcelStr(CARCOLUMN);
		// 循环各行进行处理。
		Set enteySet = carToRow.entrySet();
		Iterator it = enteySet.iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			String carType = (String) e.getKey();
			int row = (Integer) e.getValue();
			String nowCatType = reader.getCellAsStringByIndex(sheetNum,
					row, chexingcell); 
			if(nowCatType.equals(carType)) 
				error("进行比较的车型不一致，处理的sheetName"+sheetName+
						",位置为："+reader.getCellInfo(row, row)+",老的名称是："+carType+",新的名称是："+nowCatType);
			oldTodayOfCar.put(carType, reader.getCellAsStringByIndex(sheetNum,
					row, cellNum, "0"));
		}
		return oldTodayOfCar;
	}

	/**
	 * 得到旧的本月累计销量
	 * 
	 * @param sheetNum
	 */
	public Map getOldBenyueOfCar(int sheetNum,int cellNum) {
		Set enteySet = carToRow.entrySet();
		String sheetName = reader.getSheetName(sheetNum);
		Iterator it = enteySet.iterator(); 
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			String carType = (String) e.getKey();
			int row = (Integer) e.getValue();
			oldBenyueOfCar.put(carType, reader.getCellAsStringByIndex(sheetNum,
					row, cellNum, "0"));
		}
		return oldBenyueOfCar;
	}

	/**
	 * 进行处理的具体方法。
	 * 
	 * @param newFileName
	 *            新的数据所在的文件,文件名要和主要的excel中的名称一致！
	 */
	public void console(String dirName) { 
		long start = System.currentTimeMillis();
		File f = new File(dirName);
		if (!f.exists()&&!f.isDirectory()) {
			error("要处理的目录不存在:" + dirName);
			return;
		}		
		//本月数据所在的列号.
		int benyueCell = reader.getNumFromExcelStr(BENYUELEIJI);
		//今日数据所在的列号.
		int todayCell = reader.getNumFromExcelStr(TODAYCOLUMN); 
		List allSheetNames = reader.getSheetNames();
		File[] subFiles = f.listFiles();
		for(int ii=0,jj=subFiles.length;ii<jj;ii++){
			String excelName = subFiles[ii].getName().replace(".xls", "");
			if(excelName.equals("newexcel")){
				continue;
			}
			// 得到文件对应的sheet索引.
			int sheetNum = getIndexIgnoreCase(allSheetNames, excelName); 
			if (sheetNum == -1) {
				error("文件【" + excelName + "】名称有误，和sheet名称对应不上，请检查。");
				continue;
			}
			System.out.println("开始处理文件【"+excelName+"】...");
			// 得到主要的excel中的当前sheet里面的车型对应的行号信息.
			Map anayCarToRow = getAnayCarToRow(sheetNum, 9, 115);
			// 得到主要的excel中的“今日销量”信息。
			Map oldCarTodayMap = getOldCarTodayMap(sheetNum,todayCell);
			// 得到主要的excel中的“本月销量”信息。
			Map oldBenyueOfCar = getOldBenyueOfCar(sheetNum,benyueCell);
			
			
			File sub = subFiles[ii];
			FuTe test2 = new FuTe(sub);
			// 对要进行合并处理的excel文件进行位置的赋值.
			test2.setCarToRow(anayCarToRow);
			// 对新添加的文件进行分析，主要是取出相关的数据！！这里都是取的第一页！！
			Map newCarTodayMap = test2.getOldCarTodayMap(0,todayCell);
			Map newBenyueOfCar = test2.getOldBenyueOfCar(0,benyueCell);
			if (oldCarTodayMap == null) {
				error("文件【" + sub.getName() + "】中没有找到相关数据,原因是：第一个sheet中不存在数据。");
				return;
			}
			
			Iterator it = anayCarToRow.entrySet().iterator();
			// 循环全部的品牌行数进行处理.
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				// 得到品牌名称
				String brand = (String) e.getKey();
				// 得到对应的行数.
				int rowNum = (Integer) e.getValue();
				String oldBenyue = (String) oldBenyueOfCar.get(brand); 
				String newBenyue = (String) newBenyueOfCar.get(brand); 
				//更新本月累计
				reader.updateCellValue(sheetNum, rowNum, benyueCell, newBenyue);
				//计算新的今日销售
				String newToday = (parseInt(newBenyue)-parseInt(oldBenyue))+"";
				reader.updateCellValue(sheetNum, rowNum, todayCell, newToday);
			}
		}
		reader.saveAsNewFile(dirName+"\\newexcel.xls"); 
		long end = System.currentTimeMillis();
		System.out.println("处理完毕，处理耗时："+(end-start)/1000.0+"秒！");
	}

	private int parseInt(String s){
		try{
			return Integer.parseInt(s);
		}catch(Exception e){ 
			error("单元格中内容有误，不是数字");
			return 0;
		}
	}
	/**
	 * 在一个list里面查找指定的字符串内容.忽略大小写。
	 * 
	 * @param l
	 * @param name
	 * @return
	 */
	public int getIndexIgnoreCase(List l, String name) {
		Iterator it = l.iterator();
		int num = 0;
		while (it.hasNext()) {
			String nm = (String) it.next();
			if (nm.equalsIgnoreCase(name)) {
				return num;
			}
			num++;
		}
		return -1;
	}

	public void error(Object o) {
		System.out.println(o);
	}

	public static void main(String[] arg) {
		//FuTe test = new FuTe("d:\\福特.xls"); 
		//test.console("d:\\test"); 
		System.out.println( );
	}

	public Map getCarToRow() {
		return carToRow;
	}

	public void setCarToRow(Map carToRow) {
		this.carToRow = carToRow;
	}
}
