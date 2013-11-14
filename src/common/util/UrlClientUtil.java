package common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 连接网络程序的工具类.
 * @author renjie120 419723443@qq.com
 *
 */
public class UrlClientUtil {
	/**
	 * 使用get方法取得远程服务器方法的执行结果.
	 * @param urlString
	 * @return
	 */
	public static String getDocumentAt(String urlString){
		StringBuffer document = new StringBuffer();
		try{
			URL url = new URL(urlString);
			//URLConnection 使用套接字从我们指定的 URL 中读取信息（它只是解析成 IP 地址），但我们无须了解它
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			
			String line = null;
			while((line = reader.readLine())!=null)
			{
				document.append(line+"\n");
			}
			reader.close();
		}
		catch(MalformedURLException e){
			System.out.println("不可以链接到网址："+urlString);
			e.printStackTrace();
		}
		catch(IOException e){
			System.out.println("链接到网址时出现IO异常："+urlString);
			e.printStackTrace();
		}
		return document.toString();
	}
}
