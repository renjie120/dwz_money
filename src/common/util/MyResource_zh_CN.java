package common.util;

import java.util.ListResourceBundle;

/**
 * 资源文件测试类。
 * @author renjie120
 * connect my:(QQ)1246910068
 *
 */
public class MyResource_zh_CN extends ListResourceBundle{
	private final Object myData[][] = {
			{"msg","{0}，您好。今天是{1}"},{"hello","您好，吃饭没有啊？"}
	};
	@Override
	protected Object[][] getContents() { 
		return myData;
	}

}
