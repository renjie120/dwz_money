package common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaSourceUtil {
	public static void main(String[] args) {
		// 统计文件路径
		String folderPath = "E:\\lishuiqing\\svn_zhongtai6\\src\\atoz_2014\\src\\com\\atoz\\action\\baseinfo";
		// 路径文件
		File folder = new File(folderPath);
		// 源文件统计工具
		JavaSourceUtil util = new JavaSourceUtil();
		// 根据路径得到统计结果列表
		List<SourceBean> cntList = util.countJavaSource(folder);
		// 根据统计行数计算注释率、空行率、有效代码率的结果
		StringBuffer resultSbuf = util.outputCountResult(cntList);
		// 输出统计结果
		System.out.println(resultSbuf.toString());
		// 保存结果到文件
		util.saveFile(resultSbuf, "D:\\java_source_cnt.txt");
	}

	public StringBuffer outputCountResult(List<SourceBean> listBean) {
		StringBuffer sbuf = new StringBuffer();
		SourceBean totalCntBean = new SourceBean("全部文件统计");
		for (SourceBean bean : listBean) {
			int tolCnt = bean.getTotalLine();
			if (tolCnt == 0) {
				continue;
			}
			// 注释率、空行率、有效代码率的结果算出来
			sbuf.append(bean.fileName + "代码统计：").append("\r\n");
			sbuf.append("空行率：").append(bean.blankLine * 1.0 / tolCnt)
					.append("\r\n");
			sbuf.append("注释率：")
					.append((bean.singlgCmtLine + bean.multCmtLine) * 1.0
							/ tolCnt).append("\r\n");
			sbuf.append("有效代码率：").append(bean.codeLine * 1.0 / tolCnt)
					.append("\r\n\r\n");

			totalCntBean.blankLine += bean.blankLine;
			totalCntBean.codeLine += bean.codeLine;
			totalCntBean.documtLine += bean.documtLine;
			totalCntBean.multCmtLine += bean.multCmtLine;
			totalCntBean.singlgCmtLine += bean.singlgCmtLine;
		}

		sbuf.append(totalCntBean.fileName + "代码统计：").append("\r\n");
		sbuf.append("总空行率：")
				.append(totalCntBean.blankLine * 1.0
						/ totalCntBean.getTotalLine()).append("\r\n");
		sbuf.append("总注释率：")
				.append((totalCntBean.singlgCmtLine + totalCntBean.multCmtLine)
						* 1.0 / totalCntBean.getTotalLine()).append("\r\n");
		sbuf.append("总有效代码率：")
				.append(totalCntBean.codeLine * 1.0
						/ totalCntBean.getTotalLine()).append("\r\n\r\n");
		return sbuf;
	}

	/**
	 * Java代码统计
	 * 
	 * @param folder
	 *            基本路径
	 * @return 统计结果
	 */
	public List<SourceBean> countJavaSource(File folder) {
		List<SourceBean> cntList = new ArrayList<SourceBean>();
		if (!folder.exists() || !folder.isDirectory()) {
			return cntList;
		}
		File[] files = folder.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				cntList.addAll(countJavaSource(folder));
			} else {
				SourceBean cntBean = countCodeLine(files[i]);
				if (cntBean != null) {
					cntList.add(cntBean);
				}
			}
		}
		return cntList;
	}

	/**
	 * 单个文件代码统计
	 * 
	 * @param javaFile
	 *            .java文件
	 * @return 统计结果
	 */
	public SourceBean countCodeLine(File javaFile) {
		if (!javaFile.exists() || !javaFile.isFile()
				|| !javaFile.getName().endsWith(".java")) {
			return null;
		}
		SourceBean cntBean = new SourceBean(javaFile.getAbsolutePath());
		// 代码状态
		int status = 0;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					javaFile));
			String str = "";
			BufferedReader bufReader = new BufferedReader(isr);

			while ((str = bufReader.readLine()) != null) {
				str = str.trim();
				if (str.startsWith("/**")) {
					// 文档注释
					status = 10;
				} else if (str.startsWith("/*")) {
					// 多行注释
					status = 20;
				}

				if (isEmpty(str)) {
					// 空白航
					cntBean.blankLine++;
				} else if (status == 10) {
					// 文档注释
					cntBean.documtLine++;
				} else if (status == 20) {
					// 多行注释
					cntBean.multCmtLine++;
				} else if (str.startsWith("//")) {
					// 单行注释
					cntBean.singlgCmtLine++;
				} else {
					// 代码行
					cntBean.codeLine++;
				}

				if (str.endsWith("*/")) {
					// 单行或文本注释结束
					status = 0;
				}
			}
			bufReader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cntBean;
	}

	/**
	 * 空行判断
	 * 
	 * @param strValue
	 * @return 是否为空行
	 */
	public boolean isEmpty(String strValue) {
		if (strValue == null || strValue.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 读取文本文件
	 * 
	 * @param path
	 * @return
	 */
	public void saveFile(StringBuffer builder, String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(builder.toString().getBytes("utf-8"));
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
