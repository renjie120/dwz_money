package dwz.business.website;

import java.io.Serializable;

public interface LayoutKey extends Serializable, Comparable<LayoutKey> {
	public Serializable getValue();
}
