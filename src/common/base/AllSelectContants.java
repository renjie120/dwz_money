package common.base;

public enum AllSelectContants {
	QUESTION_SORT("questionSort"), DRUGTYPE_DICT("drugtype_dict"), PAYTYPE(
			"paytype"), ADDMONEYTYPE("addmoneytype"), ROLE("role"), SYS_DUIJIE(
			"sys_duijie"), MENULEVEL("menulevel"), INSUREFILE_STATE(
			"insurefile_state"), ORDERSTATUS("orderstatus"), BUGTYPE("bugtype"), PLAN_STATUS(
			"planstatus"), YESORNO("yesorno"), TOUBAOUSER_STATUS(
			"toubaouser_status"), PLAN_TYPE("plantype"), USER_TYPE("usertype"), MENU_TARGET(
			"menutarget"), ROLETYPE("roletype"), QUESTION_STATUS(
			"questionStatus"), DIARY_TYPE("diarytype"), BINDUSERTYPE(
			"bindusertype"), DEAL_TYPE("dealType"), INSURE_COMPANY(
			"insure_company"), YESORNO_STATUS("yesorno_status"), INSUREDCOMPANY_DICT(
			"insuredcompany_dict"), SEX("sex"), INSURED_USER("insured_user"), FILE_TYPE(
			"file_type"), INSURED_COM_DICT("insured_com_dict"), ALLPARAMTYPE(
			"allparamtype"), MENUTREE("menuTree"), ORGTREE("orgTree"), INSUREDTREE(
			"insuredTree"), CITYTREE("cityTree"), INSURED_UNIT_TREE(
			"insuredUnitTree"), INSUREDUNIT_DICT("insuredunit_dict"), SHOPMAN_STATUS(
			"shopman_status"), BUSINESSGROUP_DICT("businessgroup_dict"), IDOUSER_DICT(
			"idouser_dict"), AIDUYONGHU("aiduyonghu"), SHOPSTATUS("shopstatus"), PROV_TYPE(
			"prov_type"), BUSINESSMAN_DICT("businessman_dict"), CITY_DICT(
			"city_dict"), PROVINCE_DICT("province_dict");
	private String name;

	AllSelectContants(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
