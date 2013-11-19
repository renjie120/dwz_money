package common.codegenerate;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 演示将字符变大写的自定义指令.
 * 
 * @author lisq
 * 
 */
public class UpperDirective implements TemplateDirectiveModel {

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		if (!params.isEmpty())
			throw new TemplateModelException("指令没有接收到参数");
		if (loopVars.length != 0)
			throw new TemplateModelException("指令不允许循环变量");
		if (body != null) {
			body.render(new UpperCaseFilterWriter(env.getOut()));
		} else {
			throw new RuntimeException("缺少主体内容");
		}
	}

	private static class UpperCaseFilterWriter extends Writer {
		private final Writer out;

		UpperCaseFilterWriter(Writer out) {
			this.out = out;
		}

		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			char[] transFormedCbuf = new char[len];
			for (int i = 0; i < len; i++) {
				transFormedCbuf[i] = Character.toUpperCase(cbuf[i + off]);
			}
			out.write(transFormedCbuf);
		}

		@Override
		public void flush() throws IOException {
			out.flush();
		}

		@Override
		public void close() throws IOException {
			out.close();
		}
	}

}
