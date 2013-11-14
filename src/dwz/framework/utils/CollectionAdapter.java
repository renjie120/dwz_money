package dwz.framework.utils;

import java.util.Collection;
import java.util.Iterator;

public class CollectionAdapter<T, E> implements Collection<E> {

	private ObjectTranslator<T, E> objectTranslator = null;

	private Collection<T> collection = null;

	public CollectionAdapter(ObjectTranslator<T, E> objectTranslator,
			Collection<T> collection) {
		this.objectTranslator = objectTranslator;
		this.collection = collection;
	}

	public boolean add(E o) {
		throw new UnsupportedOperationException();
	}

	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	public void clear() {
		throw new UnsupportedOperationException();
	}

	public boolean contains(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		return this.collection.isEmpty();
	}

	public Iterator<E> iterator() {
		return new TranslatorIterator<T, E>(this.collection.iterator(),
				objectTranslator);
	}

	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return this.collection.size();
	}

	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	public <M> M[] toArray(M[] a) {
		throw new UnsupportedOperationException();
	}

}
