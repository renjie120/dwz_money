package common.codegenerate;

import java.util.List;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 改变驼峰风格的字符串为_隔开.
 * @author lisq
 *
 */
public class ChangeStrMethod implements TemplateMethodModel{

	public Object exec(List arguments) throws TemplateModelException {
		if(arguments.size()!=1)
			throw new TemplateModelException("参数错误!");
		//返回一个数值类型的结果.
		return new SimpleNumber(((String)arguments.get(1)).indexOf((String)arguments.get(0)));
	}

}
