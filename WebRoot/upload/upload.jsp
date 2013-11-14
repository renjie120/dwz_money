<%@ page contentType="text/html; charset=gb2312" language="java"  %> 
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.lang.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>  
<%@ page import="com.jspsmart.upload.*"%>
<%

SmartUpload su = new SmartUpload();
su.initialize(pageContext);
su.upload();
int count = su.save("d:\\test");
out.println("上传成功!"); 

for(int i=0,j=su.getFiles().getCount();i<j;i++){
	com.jspsmart.upload.File f = su.getFiles().getFile(i);
	if(f.isMissing())//如果文件不存在就继续
		continue;
    System.out.println("表单项名："+f.getFieldName());
	System.out.println("文件长度"+f.getSize());
	System.out.println("文件名："+f.getFileName());
	System.out.println("文件扩展名："+f.getFileExt());
	System.out.println("文件全名："+f.getFilePathName());

	//f.saveAs(f.getFileName());
} 
  out.println("jsonpcallback([{\"id\":\"1\",\"name\":\"test1\"},{\"id\":\"2\",\"name\":\"test2\"}])"); 
%>