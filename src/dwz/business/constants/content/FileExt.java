package dwz.business.constants.content;

import java.util.ArrayList;

public enum FileExt {
	jpg(MimeType.IMG), gif(MimeType.IMG), jpeg(MimeType.IMG), png(MimeType.IMG), bmp(
			MimeType.IMG), swf(MimeType.FLASH), flv(MimeType.FLASH), pdf(
			MimeType.PDF), txt(MimeType.TXT), csv(MimeType.TXT), html(MimeType.HTML),
			doc(MimeType.TXT), docx(MimeType.TXT), xls(MimeType.TXT), xlsx(MimeType.TXT),xml(MimeType.TXT), 
			orc(MimeType.TXT), nct(MimeType.TXT), dsb(MimeType.TXT), mht(MimeType.TXT), msg(MimeType.TXT),
			zip(MimeType.FILE);
	private final MimeType mimeType;

	FileExt(MimeType mimeType) {
		this.mimeType = mimeType;
	}

	public MimeType getMimeType() {
		return this.mimeType;
	}

	public boolean isImage() {
		return this.mimeType.equals(MimeType.IMG);
	}

	public boolean isFlash() {
		return this.mimeType.equals(MimeType.FLASH);
	}

	public static ArrayList<FileExt> values(MimeType mimeType) {
		ArrayList<FileExt> fileExtList = new ArrayList<FileExt>();

		FileExt[] fileExts = FileExt.values();
		for (FileExt fileExt : fileExts) {
			if (fileExt.mimeType.equals(mimeType)) {
				fileExtList.add(fileExt);
			}
		}

		return fileExtList;
	}
}
