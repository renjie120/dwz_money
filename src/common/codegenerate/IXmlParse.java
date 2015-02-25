package common.codegenerate;

import java.util.List;

import common.util.IDatamodelXmlReader;

public interface IXmlParse {
	public IDatamodelXmlReader parse();
 
	public List<IDatamodelXmlReader> parseClasses();
}
