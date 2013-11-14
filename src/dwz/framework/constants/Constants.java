package dwz.framework.constants;

public interface Constants {

	String FACTORY_CLASS_KEY = "app.factory.class";

	String FACTORY_CLASS = "dwz.framework.core.factory.DefaultBusinessFoctory";

	String AUTHENTICATION_KEY = "SESSION_AUTHENTICATION";

	String AUTHENTICATION_TOKEN = "SESSION_AUTHENTICATION_TOKEN";

	String PARAM_IS_FORWARDED = "IS_FORWARDED";

	String CONFIG_FILE_KEY = "app.config.file";

	String DEFAULT_CONFIG_FILE = "app-config.xml";

	String SECURITY_CONTEXT_HOLDER_MODE_KEY = "app.security.context.holder.mode";

	String STANDARD_PASSPORT_CLASS = "dwz.framework.core.passport.StandardPassport";

//	String X509_PASSPORT_CLASS = "dwz.framework.core.passport.X509Passport";

	String PASSPORT_USERNAME = "app_username";

	String PASSPORT_PASSWORD = "app_password";

	String PASSPORT_USER_TYPE = "app_usertype";

	String PASSPORT_COMPANY = "app_company";

	String PASSPORT_HOST = "app_host";

	String AUTHENTICATOR_KEY = "passport.authentication-service.authenticator-class";

	String DEFAULT_AUTHENTICATOR_CLASS = "dwz.framework.core.authentication.AuthenticatorImpl";

	String CREDENTIAL_STORE_CLASS_KEY = "passport.authentication-service.credential-store";

	String DB_CREDENTIAL_STORE_CLASS = "dwz.framework.core.authentication.store.db.DbCredentialStore";

	String PRINCIPAL_STORE_KEY = "passport.authentication-service.principal-store";

	String DB_PRINCIPAL_STORE_CLASS = "dwz.framework.core.authorization.store.db.DbPrincipalStore";

	String AUTH_SCHEMAS_KEY = "app.authentication.schemas";

	String PRINCIPAL_OPERATION_CACHE_KEY = "Acl Of Principal And Operation";

	String AUTHENTICATION_OPERATION_CACHE_KEY = "Acls Of Authentication And Operation";

	String OPERATION_NEEDED_CACHE_KEY = "Permissions Of Operation Needed";

	int ACL_OF_PRINCIPAL_IN_CACHE = 10;

	int ACLS_OF_AUTHENTICATION_IN_CACHE = 10;

	int PERMISSIONS_OF_OPERATION_NEEDED_IN_CACHE = 10;

	boolean OVER_FLOW_TO_DISK = false;

	boolean ETERNAL = false;

	long TIME_TO_IDLE_SECONDS = 100l;

	long TIME_TO_LIVE_SECONDS = 100l;

	String OPTIONS_DELIM = "\n";

	String VALUEOFOPTIONS_DELIM = ",";

	String SESSION_ID_KEY = "APP_SESSION_ID_KEY";

	long SECOND = 1000;

	long MINUTE = 60 * SECOND;

	long HOUR = 60 * MINUTE;

	long DAY = 24 * HOUR;

	long WEEK = 7 * DAY;

	String DEFAULT_REGISTER = "register_user";

	int TEMP_PASSWORD_LENGTH = 8;

	String DOMAIN_KEY = "app.server.domain";

	String AVAILABLE_USER_TYPE = "app.user.available";

	String SYSTEM_ERROR_PAGE = "/systemerror.html";

	int SYSTEM_COMPANY_ID = 1; // system database reference key

	String VALIDATION_CODE = "validation_code";
}