package common.codegenerate;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class Generate {
	String ftlname;
	Writer out;

	public Generate(String ftlname, Writer out) {
		this.ftlname = ftlname;
		this.out = out;
	}

	abstract Object setData();

	public void make() {
		try {
			Configuration cfg = new Configuration();
			File f = new File("template");
			cfg.setDirectoryForTemplateLoading(f);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			
			Template temp = cfg.getTemplate(ftlname);
			Object data = setData(); 
			temp.process(data, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
