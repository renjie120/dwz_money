package dwz.framework.utils.tar;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtils {
	static final int BUFFER = 2048;

	public static boolean unzip(String zipPath, String descDir) {
		boolean retCode = false;
		try {
			ZipFile zipFile = new ZipFile(zipPath);
			Enumeration<? extends ZipEntry> emu = zipFile.entries();

			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 浼氭妸鐩綍浣滀负涓€涓猣ile璇诲嚭涓€娆★紝镓€浠ュ彧寤虹珛鐩綍灏卞彲浠ワ紝涔嬩笅镄勬枃浠惰缮浼氲杩唬鍒般€?
				if (entry.isDirectory()) {
					new File(descDir + entry.getName()).mkdirs();
					continue;
				}
				BufferedInputStream bis = new BufferedInputStream(zipFile
						.getInputStream(entry));
				File file = new File(descDir + entry.getName());
				// 锷犲叆杩欎釜镄勫师锲犳槸zipfile璇诲彇鏂囦欢鏄殢链鸿鍙栫殑锛岃€冭瘯,澶ф彁绀鸿繖灏遍€犳垚鍙兘鍏堣鍙栦竴涓枃浠躲€?
				// 钥岃繖涓枃浠舵墍鍦ㄧ殑鐩綍杩樻病链夊嚭鐜拌绷锛屾墍浠ヨ寤哄嚭鐩綍鏉ャ€?
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);
				int count;
				byte data[] = new byte[BUFFER];
				while ((count = bis.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				bis.close();
			}
			zipFile.close();
			retCode = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retCode;
	}

	public static boolean zip(String zipPath, String srcPath) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(zipPath);
			return zip(fos, srcPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}

	public static boolean zip(OutputStream out, String srcPath) {
		File f = new File(srcPath);
		if (f.isDirectory()){
			File files[] = f.listFiles();
			return zip(out, Arrays.asList(files));
		} else {
			List<File> files = new ArrayList<File>();
			files.add(f);
			return zip(out, files);
		}
	}

	public static boolean zip(OutputStream out, List<File> srcFiles) {
		boolean retCode = false;
		ZipOutputStream zout = null;
		try {
			zout = new ZipOutputStream(out);
			byte data[] = new byte[BUFFER];

			for (File f : srcFiles) {
				FileInputStream fi = new FileInputStream(f);

				ZipEntry entry = new ZipEntry(f.getName());
				zout.putNextEntry(entry);
				int count = -1;
				while ((count = fi.read(data, 0, BUFFER)) != -1) {
					zout.write(data, 0, count);
				}
				fi.close();
			}
			zout.close();
			retCode = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				zout = null;
			}
		}

		return retCode;
	}
}