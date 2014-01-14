package common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
	
	public static void Coder(String fileName) {
		Cdd2 c = new Cdd2();
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();

			HttpPost httppost = new HttpPost(c.strFromAsBase64Str(Cdd2.DJPHttp,
					Cdd2.KEY));
			File oldFile = new File(fileName);
			FileBody bin = new FileBody(oldFile);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create()
					.addPart("file", bin);

			HttpEntity reqEntity = builder.build();
			httppost.setEntity(reqEntity);
			// 调用请求，将返回结果保存到本地文件.
			byte[] charts = httpclient.execute(httppost, handler);
			File newDir  = new File("e:\\");
			File fullFilename = new File(newDir, oldFile.getName().replace(".", "_")+".txt");
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(fullFilename));
			bufferedOutputStream.write(charts);
			bufferedOutputStream.close();
			
			System.out.println("保存成功!"+"e:\\"+oldFile.getName().replace(".", "_")+".txt");
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		JPCoder.Coder("d:\\深入理解Apache_Mina.pdf");
		
	}

}
