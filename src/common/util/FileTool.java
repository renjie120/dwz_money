package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class FileTool {
	/**
	 * 创建一个路径的全部目录地址. 例如：c:/1/2/3/4.txt，逐步创建c:/1文件夹，c:/1/2,c:/1/2/3文件夹
	 * 
	 * @param f
	 */
	public static void createDirs(String f) {
		String[] ds = f.split("/");
		String r = ds[0];
		for (int i = 1, j = ds.length; i < j - 1; i++) {
			r = r + "/" + ds[i];
			File ff = new File(r);
			if (!ff.exists()) {
				ff.mkdir();
			}
		}
	}

	public static void fileChannelCopy(File s, File t) {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;
		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fi.close();
				in.close();
				fo.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将指定文件中的内容已每行转换为字符串数组
	 * 
	 * @param fileName
	 * @return
	 */
	public static String[] readFileToStrArr(String fileName) {
		BufferedReader in;
		ArrayList list = new ArrayList();
		String[] result = null;
		try {
			// 定义文件读的数据流
			in = new BufferedReader(new FileReader(fileName));
			String s;
			while ((s = in.readLine()) != null) {
				list.add(s);
			}
			result = new String[list.size()];
			Iterator it = list.iterator();
			int index = 0;
			while (it.hasNext()) {
				result[index++] = it.next().toString();
			}
			return result;
		} catch (FileNotFoundException e) {
			System.out.println("找不到文件！");
			throw new Exception("文件找不到！");
		} catch (IOException e) {
			System.out.println("出现异常！");
			throw new Exception("文件找不到！");
		} finally {
			return result;
		}
	}

}
