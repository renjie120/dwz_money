package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 读取一个文本内容,并将其内容进行替换. 具体替换的方式需要子类实现changeString().
 * 
 * @author lsq
 * 
 */
public abstract class BaseChangeText {
	/**
	 * 读取指定文件结果到list中.
	 * 
	 * @param modeFile
	 * @return
	 */
	public List readModel(String modeFile) {
		List contentLine = new ArrayList();
		String line = null;
		BufferedReader read;
		try {
			read = new BufferedReader(new InputStreamReader(
					new FileInputStream(modeFile), "UTF-8"));
			while ((line = read.readLine()) != null) {
				contentLine.add(line);
			}
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentLine;
	}

	public List readModel(String modeFile, String encoding) {
		List contentLine = new ArrayList();
		String line = null;
		BufferedReader read;
		try {
			read = new BufferedReader(new InputStreamReader(
					new FileInputStream(modeFile), encoding));
			while ((line = read.readLine()) != null) {
				contentLine.add(line);
			}
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentLine;
	}

	private static void copyfile(String sourcefile, String targetfile)
			throws IOException {
		FileChannel sourcefc = new FileInputStream(sourcefile).getChannel();
		FileChannel targetfc = new FileOutputStream(targetfile).getChannel();

		sourcefc.transferTo(0, sourcefc.size(), targetfc);
	}

	public void bakFile(String file, String file2) {
		try {
			copyfile(file, file2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List readModel(File modeFile) {
		List contentLine = new ArrayList();
		String line = null;
		BufferedReader read;
		try {
			read = new BufferedReader(new InputStreamReader(
					new FileInputStream(modeFile), "UTF-8"));
			int count = 0;
			while ((line = read.readLine()) != null) { 
				contentLine.add(line);
				count++;
			}
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentLine;
	}

	abstract String changeString(String oldStr);

	abstract String beforeChangeString();

	abstract String afterChangeString();

	/**
	 * 将模板填充内容之后保存到指定文件.
	 * 
	 * @param modelList
	 *            模板集合
	 * @param fileName
	 *            输出文件名
	 * @param modelName
	 *            新的模型关键字,必须为首字母大写的串.例如EmployeeBO
	 * @param des
	 *            新的模型的描述,例如人员模块
	 */
	protected void saveNewFile(List modelList, String fileName) {
		PrintWriter streamWrite = null;
		String line = null;
		Iterator it = modelList.iterator();
		try {
			streamWrite = new PrintWriter(fileName, "utf-8");
			streamWrite.println(beforeChangeString());
			while (it.hasNext()) {
				String temp = (String) it.next();
				streamWrite.println(changeString(temp));
			}
			streamWrite.println(afterChangeString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		streamWrite.close();
	}

	/**
	 * 不保存空行!
	 * 
	 * @param modelList
	 * @param fileName
	 * @throws UnsupportedEncodingException 
	 */
	protected void saveNewFileNoEmpty(List modelList, String fileName) throws UnsupportedEncodingException {
		PrintWriter streamWrite = null;
		String line = null;
		Iterator it = modelList.iterator();
		try {
			streamWrite = new PrintWriter(fileName, "utf-8");
			if (beforeChangeString() != null
					&& !"".equals(beforeChangeString()))
				streamWrite.println(beforeChangeString());
			while (it.hasNext()) {
				String temp = (String) it.next();
				temp = changeString(temp);
				if (temp != null && !"".equals(temp))
					streamWrite.println(temp);
			}
			if (afterChangeString() != null && !"".equals(afterChangeString()))
				streamWrite.println(afterChangeString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		streamWrite.close();
	}

	/**
	 * 将list插入保存到指定文件输出
	 * 
	 * @param modelList
	 * @param fileName
	 */
	public void saveListToNewFile(List modelList, String fileName) {
		PrintWriter streamWrite = null;
		String line = null;
		Iterator it = modelList.iterator();
		try {
			streamWrite = new PrintWriter(fileName);
			while (it.hasNext()) {
				streamWrite.println(it.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		streamWrite.close();
	}
}
