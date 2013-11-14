package dwz.framework.config;

import java.io.Serializable;

public class ResourceKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 83482194316953300L;

	public String resourceName = null;

	public String resourceType = null;

	public boolean equals(Object other) {
		if (!(other instanceof ResourceKey)) {
			return false;
		}

		if (this == other) {
			return true;
		}

		if (this.hashCode() == other.hashCode()) {
			return true;
		}

		ResourceKey o = (ResourceKey) other;
		if (this.resourceType.compareTo(o.resourceType) == 0
				&& this.resourceName.equals(o.resourceName)) {
			return true;
		}

		return false;
	}

	public int hashCode() {
		return this.resourceName.hashCode() + 7 * this.resourceType.hashCode();
	}

}
