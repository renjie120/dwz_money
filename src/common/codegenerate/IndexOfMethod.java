package common.codegenerate;

import java.util.List;

import freemarker.template.SimpleNumber;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 自定义的方法解析模块.
 * @author lisq
 *
 */
public class IndexOfMethod implements TemplateMethodModel{

	public Object exec(List arguments) throws TemplateModelException {
		if(arguments.size()!=2)
			throw new TemplateModelException("参数错误!");
		//返回一个数值类型的结果.
		return new SimpleNumber(((String)arguments.get(1)).indexOf((String)arguments.get(0)));
	}

}
