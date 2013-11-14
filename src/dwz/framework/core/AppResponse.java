/***********************************************************************
 * Module:  AppResponse.java
 * Author:  Zhang Huihua
 * Purpose: Defines the Interface AppResponse
 ***********************************************************************/

package dwz.framework.core;

import java.util.Iterator;

public interface AppResponse {

	public Object getParameter(String name);

	void setParameter(String name, Object value);

	public Iterator<String> parameterNames();

	public void clear();

}