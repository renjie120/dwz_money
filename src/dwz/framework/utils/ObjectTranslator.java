package dwz.framework.utils;

public interface ObjectTranslator<T, M> {

	public M translateObject(T key) throws TranslatorException;

	public T restoreObject(M key) throws TranslatorException;

}
