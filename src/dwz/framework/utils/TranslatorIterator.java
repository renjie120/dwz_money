package dwz.framework.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TranslatorIterator<T, M> implements Iterator<M> {

	private ObjectTranslator<T, M> objectTranslator = null;

	private Iterator<T> initialIterator = null;

	public TranslatorIterator(Iterator<T> initialIterator,
			ObjectTranslator<T, M> objectTranslator) {
		this.initialIterator = initialIterator;
		this.objectTranslator = objectTranslator;
	}

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public boolean hasNext() {
		return this.initialIterator.hasNext();
	}

	public M next() throws NoSuchElementException {
		T t = this.initialIterator.next();
		if (t == null) {
			throw new NoSuchElementException("init object is null.");
		}

		M m = null;
		try {
			m = this.objectTranslator.translateObject(t);
		} catch (TranslatorException e) {
			throw new NoSuchElementException("TranslatorException: ");
		}

		if (m == null) {
			throw new NoSuchElementException("translate object is null.");
		}

		return m;
	}

}
