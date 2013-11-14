/**
 * 
 */
package dwz.framework.core.business;


/**
 * @author Zhang Huihua
 * 
 */

public interface DatabaseObjectLoader<T, M> {

	/**
	 * Returns the object associated with <code>id</code> or null if the
	 * object could not be loaded.
	 * 
	 * @param key
	 *            the key of the object to load.
	 * @return the object specified by <code>id</code> or null if it could not
	 *         be loaded.
	 */
	public M loadObject(T key);

}
