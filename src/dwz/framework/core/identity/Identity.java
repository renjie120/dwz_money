/***********************************************************************
 * Module:  Identity.java
 * Author:  Zhang Huihua
 * Purpose: Defines the Interface Identity
 ***********************************************************************/

package dwz.framework.core.identity;

import java.io.Serializable;

/** @pdOid a64765cd-61d6-4709-baaa-e6ca5d5593de */
public interface Identity {

	/** @pdOid dc335790-0351-46c6-b1a5-b937ee997389 */
	public Serializable getAccessToken();

	/**
	 * @param token
	 * @pdOid 6934fe46-ebee-414f-a403-b2834c3086b2
	 */
	public void setAccessToken(Serializable accessToken);

}