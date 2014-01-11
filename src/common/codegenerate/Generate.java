package common.codegenerate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class Generate {
	String ftlname;
	Writer out;
	String dirName;
	String beanName;
	String outFile;
	String packageName;
	int dirType;

	public Generate(String ftlname, String beanName, String outFile,
			String packageName) {
		this.ftlname = ftlname;
		this.packageName = packageName;
		this.beanName = beanName;
		this.outFile = outFile;
	}

	private void returnOut(String file, boolean delete) {
		File _f = new File(file);
		if (_f.exists() && delete)
			_f.delete();
		else {
			if (_f.exists() && !delete)
				throw new RuntimeException("文件已经存在：" + file);
		}
		try {
			out = new FileWriter(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getOut(boolean delete) {
		String fileName;
		if (dirName == null) {
			File f = new File("d:\\" + beanName);
			if (!f.exists())
				f.mkdir();
			fileName = "d:\\" + beanName + "\\" + (beanName + outFile);
			returnOut(fileName, delete);
		} else {
			if (dirType == 1) {// java文件
				String[] strs = packageName.split("\\.");
				for (String str : strs) {
					dirName += "\\" + str;
					File f = new File(dirName);
					if (!f.exists())
						f.mkdir();
				}
				fileName = dirName + "\\" + (beanName + outFile);
				returnOut(fileName, delete);
			} else if (dirType == 2) {// jsp文件
				dirName += "\\" + beanName.toLowerCase();
				File f = new File(dirName);
				if (!f.exists())
					f.mkdir();
				returnOut(dirName + "\\" + (beanName + outFile), delete);
			} else {// hbm文件
				returnOut(dirName + "\\" + (beanName + outFile), delete);
			}
		}
	}

	public abstract Object setData();

	public Generate setDirName(String dirName, int dirType) {
		this.dirName = dirName;
		this.dirType = dirType;
		return this;
	}

	public void make() {
		make(true);
	}

	/**
	 * 如果存在同名文件，是否删除重新创建！ 默认是true！！
	 * 
	 * @param delete
	 */
	public void make(boolean delete) {
		try {
			Configuration cfg = new Configuration();
			// 设置模板文件所在的文件夹
			File f = new File("code_template");
			cfg.setDirectoryForTemplateLoading(f);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			// 读取模板进行解析.
			Template temp = cfg.getTemplate(ftlname);
			Object data = setData();
			getOut(delete);
			temp.process(data, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
