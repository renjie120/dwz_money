package dwz.framework.core.business;

import java.util.Collection;
import java.util.Iterator;

public class CollectionAdapter<T, E> implements Collection<E> {

	private DatabaseObjectLoader<T, E> databaseObject = null;

	private Collection<T> collection = null;

	private T[] o = null;

	public CollectionAdapter(DatabaseObjectLoader<T, E> databaseObject,
			Collection<T> collection, T[] o) {
		this.databaseObject = databaseObject;
		this.collection = collection;
		this.o = o;
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
		return this.collection.contains(o);
	}

	public boolean containsAll(Collection<?> c) {
		return this.collection.containsAll(c);
	}

	public boolean isEmpty() {
		return this.collection.isEmpty();
	}

	public Iterator<E> iterator() {
		return new DatabaseObjectIterator<T, E>(
				this.collection.toArray(this.o), databaseObject);
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
		return this.collection.toArray();
	}

	public <M> M[] toArray(M[] a) {
		return this.collection.toArray(a);
	}

}
