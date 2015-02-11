package common.util;

public class SourceBean {
	// 空白行
	public int blankLine;
	// 单行注释
	public int singlgCmtLine;
	// 多行注释
	public int multCmtLine;
	// 代码行数
	public int codeLine;
	// 文挡注释
	public int documtLine;
	// 文件名
	public String fileName;

	public SourceBean(String fileName) {
		this.fileName = fileName;
	}

	public int getTotalLine() {
		int totalLine = blankLine + singlgCmtLine + multCmtLine + codeLine
				+ documtLine;
		return totalLine;
	}
}
