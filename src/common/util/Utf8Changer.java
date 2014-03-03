package common.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

public class Utf8Changer {
	public static void main(String[] args) {
		File f = new File(
				"E:\\dpap\\workspaces\\dwz\\frame\\src\\main\\java\\dwz");
		Utf8Changer.convert(f);
	}

	public static String readFile(File f) {
		StringBuilder bu = new StringBuilder();
		// 指定读取文件时以UTF-8的格式读取
		try {
			FileInputStream in = new FileInputStream(f);
			// BufferedReader br = new BufferedReader(new InputStreamReader(in,
			// "UTF-8"));
			BufferedReader br = new BufferedReader(new UnicodeReader(in,
					Charset.defaultCharset().name()));
			String line = br.readLine();
			while (line != null) {
				bu.append(line).append("\n");
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bu.toString();
	}

	public static void saveFile(File f, String data, boolean append)
			throws IOException {

		BufferedWriter bw = null;

		OutputStreamWriter osw = null;

		FileOutputStream fos = new FileOutputStream(f, append);

		try {

			// write UTF8 BOM mark if file is empty

			if (f.length() < 1) {

				// final byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB,
				// (byte) 0xBF };
				//
				// fos.write(bom);

			}

			osw = new OutputStreamWriter(fos, "UTF-8");

			bw = new BufferedWriter(osw);

			if (data != null)
				bw.write(data);

		} catch (IOException ex) {

			throw ex;

		} finally {

			try {
				bw.close();
				fos.close();
			} catch (Exception ex) {
			}

		}

	}

	private static void convert(File file) {
		if (file.isDirectory()) {
			File[] ch = file.listFiles();
			for (int i = 0; i < ch.length; i++) {
				convert(ch[i]);
			}

		} else {
			try {
				String s = readFile(file);
				System.out.println("convert " + file.getPath());
				saveFile(file, s, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
