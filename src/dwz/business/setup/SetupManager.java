package dwz.business.setup;

import dwz.business.constants.PreferenceKey;
import dwz.framework.core.business.BusinessObjectManager;

public interface SetupManager extends BusinessObjectManager {

	public String getStringValue(PreferenceKey refKey);

	public int getIntValue(PreferenceKey refKey);

	public double getDoubleValue(PreferenceKey refKey);

	public String getStringValue(int companyId, PreferenceKey refKey);

	public int getIntValue(int companyId, PreferenceKey refKey);

	public double getDoubleValue(int companyId, PreferenceKey refKey);

	public void setProperty(PreferenceKey refKey, String refValue);

	public void setProperty(int companyId, PreferenceKey refKey, String refValue);

	public String generateNo(PreferenceKey refKey, int length);
}
