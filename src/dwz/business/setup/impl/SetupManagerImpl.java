package dwz.business.setup.impl;

import java.util.Collection;
import java.util.Date;

import dwz.business.constants.PreferenceKey;
import dwz.business.setup.SetupManager;
import dwz.framework.constants.Constants;
import dwz.framework.core.business.AbstractBusinessObjectManager;
import dwz.persistence.beans.SetPreference;
import dwz.persistence.daos.SetPreferenceDao;

public class SetupManagerImpl extends AbstractBusinessObjectManager implements
		SetupManager {
	private SetPreferenceDao preferenceDao;

	public SetupManagerImpl(SetPreferenceDao preferenceDao) {
		this.preferenceDao = preferenceDao;
	}

	public double getDoubleValue(PreferenceKey refKey) {
		return this.getDoubleValue(Constants.SYSTEM_COMPANY_ID, refKey);
	}

	public double getDoubleValue(int companyId, PreferenceKey refKey) {
		String value = this.getStringValue(companyId, refKey);
		try {
			if (value != null && value.length() > 0)
				return Double.parseDouble(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getIntValue(PreferenceKey refKey) {
		return this.getIntValue(Constants.SYSTEM_COMPANY_ID, refKey);
	}

	public int getIntValue(int companyId, PreferenceKey refKey) {
		String value = this.getStringValue(companyId, refKey);
		try {
			if (value != null && value.length() > 0)
				return Integer.parseInt(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getStringValue(PreferenceKey refKey) {
		return this.getStringValue(Constants.SYSTEM_COMPANY_ID, refKey);
	}

	public String getStringValue(int companyId, PreferenceKey refKey) {
		try {
			Collection<String> values = preferenceDao.findValueByPrefKey(companyId,
					refKey.toString());
			if (values != null && values.size() > 0)
				return values.toArray(new String[values.size()])[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void setProperty(PreferenceKey refKey, String refValue) {
		this.setProperty(Constants.SYSTEM_COMPANY_ID, refKey, refValue);
	}

	public void setProperty(int companyId, PreferenceKey refKey,
			String refValue) {
		Collection<SetPreference> pos = preferenceDao.findByPrefKey(companyId, refKey
				.toString());
		Date now = new Date();
		if (pos == null || pos.size() == 0) {
			SetPreference pref = new SetPreference(companyId,
					refKey.toString(), now);
			pref.setRefValue(refValue);
			pref.setUpdateDate(now);
			preferenceDao.insert(pref);
		} else {
			SetPreference pref = pos
					.toArray(new SetPreference[pos.size()])[0];
			pref.setRefValue(refValue);
			pref.setUpdateDate(now);

			preferenceDao.update(pref);
		}
	}

	public String generateNo(PreferenceKey refKey, int length) {
		int intValue = this.getIntValue(refKey);
		String theNo = String.valueOf(intValue);
		for (int i = theNo.length(); i < length; i++)
			theNo = "0" + theNo;

		this.setProperty(refKey, String.valueOf(intValue + 1));
		return theNo;
	}

}
