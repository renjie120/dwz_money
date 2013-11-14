package dwz.framework.core.business;

import java.util.Iterator;

public class DatabaseObjectIterator<T, M> implements Iterator<M> {

	private DatabaseObjectLoader<T, M> databaseObject = null;

	private T[] keys = null;

	private int currentIndex = -1;

	private M nextObject = null;

	public DatabaseObjectIterator(T[] keys,
			DatabaseObjectLoader<T, M> databaseObject) {
		this.keys = keys;
		this.databaseObject = databaseObject;
	}

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	public boolean hasNext() {
		// If we are at the end of the list, there can't be any more elements
		// to iterate through.
		if (currentIndex + 1 >= keys.length && nextObject == null) {
			return false;
		}
		// Otherwise, see if nextElement is null. If so, try to load the next
		// element to make sure it exists.
		if (nextObject == null) {
			nextObject = this.getNextObject();
			if (nextObject == null) {
				return false;
			}
		}
		return true;
	}

	private M getNextObject() {
		while (currentIndex + 1 < keys.length) {
			currentIndex++;
			M object = databaseObject.loadObject(keys[currentIndex]);
			
			if (object != null) {
				return object;
			}
		}
		return null;
	}

	public M next() throws java.util.NoSuchElementException {
		M object = null;
		if (nextObject != null) {
			object = nextObject;
			nextObject = null;
		} else {
			object = getNextObject();
			if (object == null) {
				throw new java.util.NoSuchElementException();
			}
		}
		return object;
	}

}