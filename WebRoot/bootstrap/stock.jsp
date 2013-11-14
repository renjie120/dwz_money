<%@ page language="java" import="java.util.*" pageEncoding="GBK"%> 
<%@ page import="java.net.*,java.io.*"%>
<% 
		String[] stocks = new String[]{"sh600000","sh000001","sh600718","sh600000","sz399001"};
		String[] stocks2 = new String[]{"sh600383","sh000002","sh600635","sh601118","sz000799"};
		int i1 = (int)(Math.random()*5);
		int i2 =  (int)(Math.random()*5);
		String _str = "http://hq.sinajs.cn/list="+stocks[i1]+","+stocks2[i2]; 
		String str = "";
 		try {
			URL url = new URL( _str);
			StringBuffer _s = new StringBuffer();
			BufferedReader in = new BufferedReader(new InputStreamReader(url
					.openStream(),"gbk")); 
			while ((str = in.readLine()) != null)
				_s.append(str);
			in.close();
			str = _s.toString()+"var vars=[hq_str_"+stocks[i1]+",hq_str_"+stocks2[i2]+"];";
			response.setContentType("text/html;charset=GBK"); 
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		} 
%>
 