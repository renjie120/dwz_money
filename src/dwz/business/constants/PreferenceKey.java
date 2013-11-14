package dwz.business.constants;

public enum PreferenceKey {
	TM_RdSAP_PDF_EW_en, TM_RdSAP_PDF_EW_cy, TM_RdSAP_PDF_EW_zh, 
	TM_RdSAP_PDF_NI_en,
	TM_RdSAP_RATING_EW,
	
	TM_RdSAP_LODGE_XML,	//lodge report
	TM_D_CANCEL_XML,TM_ND_CANCEL_XML,	//candel report
	
	TM_RdSAP_CAL_REQUEST, TM_RdSAP_CAL_RESPONSE, //RdSAP cal
	TM_XML_VALIDATE,
	
	TM_LODGE_DEA, TM_LODGE_NDEA, // iadmin lodge EAs
	
	TM_SAP_EA_WS, TM_RdSAP_EA_WS, // webservice
	
	TM_QA_CHECK, // QA check report
	
	CREDIT_VAT;
}
