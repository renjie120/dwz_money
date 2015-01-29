package common.base;

public enum AllSelectContants {
	QUESTION_SORT("questionSort"), MENULEVEL("menulevel"), ORDERSTATUS(
			"orderstatus"), PLAN_STATUS("planstatus"), YESORNO("yesorno"), PLAN_TYPE(
			"plantype"), USER_TYPE("usertype"), MENU_TARGET("menutarget"), QUESTION_STATUS(
			"questionStatus"), DIARY_TYPE("diarytype"), DEAL_TYPE("dealType"), INSURE_COMPANY(
			"insure_company"), YESORNO_STATUS("yesorno_status"), INSUREDCOMPANY_DICT(
			"insuredcompany_dict"),INSURED_COM_DICT(
					"insured_com_dict"), ALLPARAMTYPE("allparamtype"), MENUTREE(
			"menuTree"), ORGTREE("orgTree"), INSUREDTREE("insuredTree"), CITYTREE("cityTree"), INSURED_UNIT_TREE("insuredUnitTree"), INSUREDUNIT_DICT(
			"insuredunit_dict"), SHOPMAN_STATUS("shopman_status"), BUSINESSGROUP_DICT(
			"businessgroup_dict"),IDOUSER_DICT("idouser_dict"),AIDUYONGHU("aiduyonghu"),SHOPSTATUS("shopstatus"),PROV_TYPE("prov_type"), BUSINESSMAN_DICT("businessman_dict"),CITY_DICT("city_dict"),PROVINCE_DICT("province_dict");
	private String name;

	AllSelectContants(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
