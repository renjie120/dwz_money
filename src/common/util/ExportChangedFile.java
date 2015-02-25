package common.util;

import java.io.File;

/**
 * 导出变化的文件按照目录新建到新的地址.
 * 
 * @author Administrator
 * 
 */
public class ExportChangedFile {
	public static void main(String[] args) {
		String rootDir = "E:/dwz_money/";
		String d=  DateUtil.toString(DateUtil.now(),"yyyyMMdd_HHmm");
		String desDir = "d:/des"+d+"/";
		File desFile = new File(desDir);
		if (!desFile.exists()) {
			desFile.mkdir();
		}
		String fileListName = "E:\\dwz_money\\src\\common\\util\\changedFiles.txt";
		String[] files = FileTool
				.readFileToStrArr(fileListName);
		FileTool.fileChannelCopy(new File(fileListName), new File(desDir+"changedFiles.txt"));
		for (String s : files) {
			String[] _t = s.split(":");
			if (_t.length > 1) {
				s = _t[1].trim();
				String temp = rootDir + s;
				String desTemp = desDir + s;
				FileTool.createDirs(desTemp);
				File f = new File(temp);
				File newF = new File(desTemp);
				FileTool.fileChannelCopy(f, newF);
			}
		}
	}

}
