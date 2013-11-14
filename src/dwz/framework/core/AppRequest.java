/***********************************************************************
 * Module:  AppRequest.java
 * Author:  Zhang Huihua
 * Purpose: Defines the Interface AppRequest
 ***********************************************************************/

package dwz.framework.core;

import java.util.Iterator;

/** @pdOid 09240d2a-350d-473b-a2b2-e5b9ea8484b2 */
public interface AppRequest {
	/**
	 * @param name
	 * @pdOid 6538aa0e-5850-4c36-a31c-d830ae72d474
	 */
	public Object getParameter(String name);

	/**
	 * @param name
	 * @param value
	 * @pdOid 7c51a3d4-3e0a-4d8e-8c4c-20bd0c534ee9
	 */
	public void setParameter(String name, Object value);

	public Iterator<String> parameterNames();

	public void clear();

}