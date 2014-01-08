package common.cache;

public enum CacheEnum {
	ORGTREE("orgTree"), MONEYTYPE("moneyType"), ALLPARAMTYPE("allparamtype"), ALLPARAMTYPECODE(
			"allparamtypecode"), MONEYTYPETREE("moneyTypeTree"), MENUTREE(
			"menuTree");
	private String str;

	CacheEnum(String str) {
		this.str = str;
	}
	public String getName(){
		return this.str;
	}
}