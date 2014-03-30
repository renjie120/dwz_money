package common.struts2;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

import dwz.constants.BeanManagerKey;
import dwz.present.BaseAction;

/**
 * 测试.
 * 
 * @author renjie120 connect my:(QQ)1246910068
 * 
 */
public class UploadAction extends BaseAction {
	private File upload;
	FileManager fMgr = bf.getManager(BeanManagerKey.fileManager);

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	private String arg;
	// 下面的两个属性是用来接收依赖注入的属性----如果定义了file上传的属性为xxx，还要再定义两个属性用来封装类型和文件名！（如果不写怎么样？）
	private String uploadContentType;
	private String uploadFileName;
	private String fileId;
	// 设置允许上传的文件类型
	private String allowTypes;
	// 下面的属性可以通过配置文件来配置，允许动态设置---典型的依赖注入---见这个action的配置文件。
	private String savePath;

	/**
	 * 对上传文件进行过滤。 如果失败，就返回INPUT视图！
	 * 
	 * @param types
	 * @return
	 */
	private String filterTypes(String[] types) {
		String fileType = getUploadContentType();
		System.out.println("上传的文件类型是：" + fileType);
		for (String type : types) {
			if (type.equals(fileType)) {
				return null;
			}
		}
		return INPUT;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getFileSizes(File f) throws Exception {// 取得文件大小
		int s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	/**
	 * 保存文件到数据库.
	 * 
	 * @return
	 * @throws Exception
	 */
	public String saveFileToDb() throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 最大缓存
		factory.setSizeThreshold(5 * 1024);
		// 设置临时文件目录
		factory.setRepository(new File(getSavePath()));
		ServletFileUpload uu = new ServletFileUpload(factory);
		// 文件最大上限
		uu.setSizeMax(2 * 1024 * 1024);
		// 下面的文件上传类型的是通过Action配置文件里面的属性注入的！param
		String destFileName = getSavePath() + "\\" + uploadFileName;

		FileOutputStream fos = new FileOutputStream(destFileName);
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.close();
		File f2 = new File(destFileName);
		fileId = UUID.randomUUID().toString();
		int fileLen = getFileSizes(upload);
		String fileName = uploadFileName;
		MyFile f = new MyFile();
		f.setFileId(fileId);
		f.setFileName(fileName);
		f.setFileLen(fileLen);
		f.setContent(f2);
		fMgr.saveFile(f);
		return "success";
	}
	
	public InputStream getFile() throws Exception {  
		MyFile file = fMgr.getFile(fileId);
		File f = file.getContent();
		Byte b = new 
		//2、创建OutputStream类，并为此实例化对象  
        OutputStream out=new FileOutputStream();   
        byte b[]=str.getBytes();  
        out.write(b);  
        //4、关闭输入流  
        out.close();  
		return new ByteArrayInputStream(b);
	}

	public String saveFile() throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 最大缓存
		factory.setSizeThreshold(5 * 1024);
		// 设置临时文件目录
		factory.setRepository(new File(getSavePath()));
		ServletFileUpload uu = new ServletFileUpload(factory);
		// 文件最大上限
		uu.setSizeMax(2 * 1024 * 1024);
		System.out.println("saveFile===");
		// 下面的文件上传类型的是通过Action配置文件里面的属性注入的！param
		String destFileName = getSavePath() + "\\" + uploadFileName;
		// 获取所有文件列表
		// List<FileItem> items = uu.parseRequest(request);
		// for (FileItem item : items) {
		// System.out.println("82");
		// String fileName = item.getName();
		// //检查文件后缀格式
		// String fileEnd =
		// fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
		// //创建文件唯一名称
		// String uuid = UUID.randomUUID().toString();
		// //真实上传路径
		// StringBuffer sbRealPath = new StringBuffer();
		// sbRealPath.append(getSavePath()).append(uuid).append(".").append(fileEnd);
		// System.out.println("sbRealPath---"+sbRealPath);
		// File file = new File(sbRealPath.toString());
		// item.write(file);
		// }
		FileOutputStream fos = new FileOutputStream(destFileName);
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fis.close();
		fos.close();
		// 将上传的文件压缩之后，删除原文件.
		// File destFile = new File(destFileName);
		// if (destFile.exists()) {
		// // 如果是excel文件，进行读取.
		// if (uploadFileName.endsWith(".xls")
		// || uploadFileName.endsWith(".xlsx")) {
		//
		// }
		// ZipCompressor zipCompressor = new ZipCompressor(getSavePath()
		// + "\\" + System.currentTimeMillis() + ".zip");
		// zipCompressor.compress(destFileName);
		//
		// }
		// destFile.delete();
		return "success";
	}

	public String init() throws Exception {
		return "init";
	}

	public String getArg() {
		return arg;
	}

	public void setArg(String arg) {
		this.arg = arg;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getAllowTypes() {
		return allowTypes;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}
}
