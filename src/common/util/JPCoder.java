package common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class JPCoder {
	static ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() {
		public byte[] handleResponse(HttpResponse response)
				throws ClientProtocolException, IOException {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toByteArray(entity);
			} else {
				return null;
			}
		}
	};

	private static byte[] jiemi(File file) {
		Cdd2 c = new Cdd2();
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost;
		try {
			httppost = new HttpPost(
					c.strFromAsBase64Str(Cdd2.DJPHttp, Cdd2.KEY));
			FileBody bin = new FileBody(file);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create()
					.addPart("file", bin);

			HttpEntity reqEntity = builder.build();
			httppost.setEntity(reqEntity);
			// 调用请求，将返回结果保存到本地文件.
			byte[] charts = httpclient.execute(httppost, handler);
			return charts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void saveFile(byte[] charts, String dirName,
			String newFileName) {
		File newDir = new File(dirName);
		File fullFilename = new File(newDir, newFileName);
		BufferedOutputStream bufferedOutputStream;
		try {
			bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(fullFilename));
			bufferedOutputStream.write(charts);
			bufferedOutputStream.close();
			System.out.println("保存成功!" + dirName + newFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void Coder(String fileName) {
		File oldFile = new File(fileName);
		if (!oldFile.isDirectory())
			saveFile(jiemi(oldFile), "e:\\", oldFile.getName()
					.replace(".", "_"));
		else
			CoderDir(fileName);
	}

	private static void createDir(String dirName) {
		File f = new File(dirName);
		if (!f.exists()) {
			f.mkdir();
		}
	}

	private static void CoderDir(String dirName) {
		String panfu = dirName.split(":")[0];
		File d = new File(dirName);
		List<File> dir_list = new ArrayList<File>();
		dir_list.add(d);
		String rootDir = "e:\\" + d.getName();
		createDir(rootDir);
		while (!dir_list.isEmpty()) {
			d = dir_list.remove(0);
			String _dirname = d.getAbsolutePath();
			File[] files = d.listFiles();
			for (File f : files) {
				if (f.isDirectory())
					dir_list.add(f);
				else {
					String pth = _dirname.replace(f.getName(), "").replace(
							panfu, "e");
					createDir(pth);
					saveFile(jiemi(f), pth + "\\", f.getName().replace(".","_"));
					//saveFile(jiemi(f), pth + "\\", f.getName());
				}
			}
		}
	}

	public static void main(String[] args) {
		// JPCoder.Coder("D:\\main_mid.png");
		JPCoder.Coder("d:\\temp1");
	}

}
