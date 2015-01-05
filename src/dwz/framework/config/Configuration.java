package dwz.framework.config;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import money.tree.TreeManager;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;

import common.base.AllSelect;
import common.base.SpringContextUtil;

import dwz.business.website.util.WebSiteUtils;
import dwz.constants.BeanManagerKey;
import dwz.constants.MailTemplateKey;
import dwz.framework.constants.Constants;
import dwz.framework.core.factory.BusinessFactory;
import dwz.framework.http.session.mdb.mysqldb.schedule.Session;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionException;
import dwz.framework.http.session.mdb.mysqldb.schedule.SessionManager;
import dwz.framework.http.session.mdb.mysqldb.schedule.impl.SessionImpl;
import dwz.framework.mail.Template;
import dwz.framework.mail.impl.DefaultTemplateImpl;
import dwz.framework.timer.TaskFactory;

public class Configuration {
	protected final Log log = LogFactory.getLog(getClass()); 
	private ApplicationContext appContext = null;

	private org.apache.commons.configuration.Configuration conf = null;

	private String securityContextHolderMode = null;

	private Map<String, Template> vmTemplates = null;
	private Map<String, dwz.business.website.Template> templates = null;

	private Session session = null;

	public Configuration(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	public ApplicationContext getApplicationContext() {
		return this.appContext;
	}

	public String getFactoryClass() {
		if (conf == null) {
			throw new NullPointerException("Config file not loaded.");
		}

		return conf.getString(Constants.FACTORY_CLASS_KEY);
	}

	public void load(String configFile) throws ConfigurationException  {
		String file = configFile;

		if (file == null) {
			file = Constants.DEFAULT_CONFIG_FILE;
		}
		System.out.println("file " + file);
		ConfigurationFactory factory = new ConfigurationFactory(file);
		this.conf = factory.getConfiguration();
		this.buildFactory();

		this.fetchVmTemplateSetting();

		this.fetchCommonTemplate();

		int pIndex = file.lastIndexOf("/");
		if (pIndex == -1)
			pIndex = file.lastIndexOf("\\");
		String basePath = file.substring(0, pIndex);
		// this.fetchSearchSetting(basePath);

		this.fetchTimerTask(basePath);

		this.fetchSessionSetting();

		System.out.println("start to build session manager timer task");
		SessionManager sessionManager = (SessionManager) getApplicationContext()
				.getBean(BeanManagerKey.sessionManager.toString());
		try {
			sessionManager.clearSession();
		} catch (SessionException e) {
			e.printStackTrace();
		}
	}

	private void fetchTimerTask(String basePath) {
		String path = this.conf.getString("app.timer.task.config");
		File file = new File(basePath + path);
		if(file.exists()) {
			TaskFactory factory = TaskFactory.getFactory();
			factory.initTasks(basePath + path);
			factory.startAllTasks();
		}
	}
	
//	private void fetchSearchSetting(String basePath) {
//		this.parser = new IndexConfigurationFileParserImpl(basePath
//				+ this.conf.getString("app.search.index.config"));
//	}

	@SuppressWarnings("unchecked")
	private void fetchSessionSetting() {
		Object sessionFileNames = this.conf.getProperty("session.name");
		Collection<String> names = null;
		if (sessionFileNames == null)
			return;

		if (!(sessionFileNames instanceof Collection)) {
			names = new ArrayList<String>();
			names.add((String) sessionFileNames);
		} else {
			names = (Collection<String>) sessionFileNames;
		}
		int i = 0;
		for (String name : names) {
			boolean autoEnable = this.conf.getBoolean("session(" + i
					+ ").auto-clear-enable");
			int autoInterval = this.conf.getInt("session(" + i
					+ ").auto-clear-interval");
			int timeout = this.conf.getInt("session(" + i + ").timeout");

			this.session = new SessionImpl(name, autoInterval, timeout,
					autoEnable);

			i++;
			break;
		}
		
		log.info("init cache....");
		//初始化全部的缓存
		AllSelect allselect  = (AllSelect)SpringContextUtil.getBean(AllSelect.BEANNAME);
//		allselect.getAllMoneyType();
		allselect.getAllParamType();
		allselect.getAllParamTypeCode();
		allselect.cacheAllParams();
		
		//初始化金额树.
//		TreeManager tMgr = (TreeManager)SpringContextUtil.getBean(BeanManagerKey.treeManager.toString());
//		tMgr.getMoneyTypeTree(); 
		log.info("finishing setting...");
	}

	@SuppressWarnings("unchecked")
	private void fetchVmTemplateSetting() {

		Object oNames = this.conf.getProperty("vm-template.name");
		Collection<String> names = null;
		if (!(oNames instanceof Collection)) {
			names = new ArrayList<String>();
			names.add((String) oNames);
		} else {
			names = (Collection<String>) oNames;
		}

		this.vmTemplates = new HashMap<String, Template>();

		int i = 0;
		for (String name : names) {
			String subject = this.conf.getString("vm-template(" + i
					+ ").subject");
			String body = this.conf.getString("vm-template(" + i + ").body");

			Template template = new DefaultTemplateImpl(name, subject, body);
			this.vmTemplates.put(name, template);

			i++;
		}
	}

	@SuppressWarnings("unchecked")
	private void fetchCommonTemplate() {
		Map<String, Object> properties = new Hashtable<String, Object>();

		Object oNames = this.conf
				.getProperty("web-template-list.template.name");
		if (oNames == null) {
			return;
		}
		this.templates = new TreeMap<String, dwz.business.website.Template>();

		Collection<String> templateNames = null;
		if (!(oNames instanceof Collection)) {
			templateNames = new ArrayList<String>();
			templateNames.add((String) oNames);
		} else {
			templateNames = (Collection<String>) oNames;
		}

		int i = 0;
		for (String templateName : templateNames) {
			dwz.business.website.Template template = null;

			String templateLabel = this.conf
					.getString("web-template-list.template(" + i + ").label");
			String description = this.conf
					.getString("web-template-list.template(" + i
							+ ").description");

			String prePicBase = this.conf
					.getString("web-template-list.template(" + i
							+ ").pre-pic-base");
			String prePicExt = this.conf
					.getString("web-template-list.template(" + i
							+ ").pre-pic-ext");
			properties.put("name", templateName);
			properties.put("label", templateLabel);
			properties.put("description", description);
			properties.put("prePicBase", prePicBase);
			properties.put("prePicExt", prePicExt);

			template = WebSiteUtils.buildTemplate(properties);

			Object oLayoutNames = this.conf
					.getProperty("web-template-list.template(" + i
							+ ").layout-list.layout.name");
			Collection<String> layoutNames = null;
			if (!(oLayoutNames instanceof Collection)) {
				layoutNames = new ArrayList<String>();
				layoutNames.add((String) oLayoutNames);
			} else {
				layoutNames = (Collection<String>) oLayoutNames;
			}
			int j = 0;
			for (String layoutName : layoutNames) {
				String css = this.conf.getString("web-template-list.template("
						+ i + ").layout-list.layout(" + j + ").css");
				String indexStr = this.conf
						.getString("web-template-list.template(" + i
								+ ").layout-list.layout(" + j + ").index");
				String desc = this.conf.getString("web-template-list.template("
						+ i + ").layout-list.layout(" + j + ").description"); //
				System.out.println("layoutName: " + templateName + " -> " + //
						layoutName);

				int index = 0;
				try {
					index = Integer.parseInt(indexStr);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				template.addLayout(layoutName, css, index, desc);
				j++;
			}

			Object oThemeNames = this.conf
					.getProperty("web-template-list.template(" + i
							+ ").theme-list.theme.name");
			Collection<String> themeNames = null;
			if (!(oThemeNames instanceof Collection)) {
				themeNames = new ArrayList<String>();
				themeNames.add((String) oThemeNames);
			} else {
				themeNames = (Collection<String>) oThemeNames;
			}
			int k = 0;
			for (String themeName : themeNames) {
				String css = this.conf.getString("web-template-list.template("
						+ i + ").theme-list.theme(" + k + ").css");
				String label = this.conf
						.getString("web-template-list.template(" + i
								+ ").theme-list.theme(" + k + ").label");
				String desc = this.conf.getString("web-template-list.template("
						+ i + ").theme-list.theme(" + k + ").description");

				template.addTheme(themeName, css, label, desc);
				k++;
			}

			this.templates.put(templateName, template);
			i++;
		}
		properties.clear();
		properties = null;
	}

	public Template getVmTemplate(String name) {
		Template template = null;
		if (this.vmTemplates == null) {
			return null;
		}

		template = this.vmTemplates.get(name);

		if (template == null || template.getBody() == null) {
			template = this.vmTemplates
					.get(MailTemplateKey.defaultVm);
		}

		return template;
	}

	public Set<String> getVmTemplateNames() {
		if (this.vmTemplates == null) {
			return new HashSet<String>(0);
		}

		return this.vmTemplates.keySet();
	}

	public Map<String, dwz.business.website.Template> getTemplates() {
		return this.templates;
	}

	public String getSearchIndexRootPath() {
		return this.conf.getString("app.search.index.path");
	}

	public Session getSession() {
		return this.session;
	}

	public boolean isLogged() {
		return false;
	}

	private BusinessFactory buildFactory() {
		if (conf == null) {
			throw new NullPointerException("Config file not loaded.");
		}

		BusinessFactory bf = BusinessFactory.init(this);

		if (bf == null) {
			throw new NullPointerException("Some unknowned exception.");
		}

		return bf;
	}

	public void clear() {
		if (this.vmTemplates != null) {
			this.vmTemplates.clear();
			this.vmTemplates = null;
		}

		if (this.conf != null) {
			this.conf.clear();
			this.conf = null;
		}
	}

	public String getSecurityContextHolderMode() {
		return this.securityContextHolderMode;
	}


	public String getStaticServer() {
		return this.conf.getString("app.server.static");
	}

	public String getSystemServer() {
		return this.conf.getString("app.server.system");
	}

	public String getWWWServer() {
		return this.conf.getString("app.server.www");
	}
	public String getQasServer() {
		return this.conf.getString("app.server.qas");
	}
	public String getManagementServer() {
		return this.conf.getString("app.server.management");
	}

	public String getSystemSecureServer() {
		return this.conf.getString("app.secure.system");
	}

	public String getWWWSecureServer() {
		return this.conf.getString("app.secure.www");
	}

	public String getManagementSecureServer() {
		return this.conf.getString("app.secure.management");
	}

	public String getStaticRootPath() {
		return this.conf.getString("app.server.static.root.path");
	}

	public String getStaticContentUri() {
		return this.conf.getString("app.server.static.content.uri");
	}

	public String getStaticPagePath() {
		return this.getStaticRootPath() + this.getStaticPageUri();
	}

	public String getStaticPageUri() {
		return this.conf.getString("app.server.static.page.uri");
	}

	public String getReportBasePath() {
		return this.conf.getString("app.report.basepath");
	}
	
	public String getTempPath() {
		return this.conf.getString("app.temp.path");
	}
	
	public String getDomain() {
		return this.conf.getString("app.server.domain");
	}

	public boolean containsKey(String arg0) {
		return this.conf.containsKey(arg0);
	}

	public BigDecimal getBigDecimal(String arg0) {
		return this.conf.getBigDecimal(arg0);
	}

	public BigDecimal getBigDecimal(String arg0, BigDecimal arg1) {
		return this.conf.getBigDecimal(arg0, arg1);
	}

	public BigInteger getBigInteger(String arg0) {
		return this.conf.getBigInteger(arg0);
	}

	public BigInteger getBigInteger(String arg0, BigInteger arg1) {
		return this.conf.getBigInteger(arg0, arg1);
	}

	public boolean getBoolean(String arg0) {
		return this.conf.getBoolean(arg0);
	}

	public boolean getBoolean(String arg0, boolean arg1) {
		return this.conf.getBoolean(arg0, arg1);
	}

	public Boolean getBoolean(String arg0, Boolean arg1) {
		return this.conf.getBoolean(arg0, arg1);
	}

	public byte getByte(String arg0) {
		return this.conf.getByte(arg0);
	}

	public byte getByte(String arg0, byte arg1) {
		return this.conf.getByte(arg0, arg1);
	}

	public Byte getByte(String arg0, Byte arg1) {
		return this.conf.getByte(arg0, arg1);
	}

	public double getDouble(String arg0) {
		return this.conf.getDouble(arg0);
	}

	public double getDouble(String arg0, double arg1) {
		return this.conf.getDouble(arg0, arg1);
	}

	public Double getDouble(String arg0, Double arg1) {
		return this.conf.getDouble(arg0, arg1);
	}

	public float getFloat(String arg0) {
		return this.conf.getFloat(arg0);
	}

	public float getFloat(String arg0, float arg1) {
		return this.conf.getFloat(arg0, arg1);
	}

	public Float getFloat(String arg0, Float arg1) {
		return this.conf.getFloat(arg0, arg1);
	}

	public int getInt(String arg0) {
		return this.conf.getInt(arg0);
	}

	public int getInt(String arg0, int arg1) {
		return this.conf.getInt(arg0, arg1);
	}

	public Integer getInteger(String arg0, Integer arg1) {
		return this.conf.getInteger(arg0, arg1);
	}

	public Iterator getKeys() {
		return this.conf.getKeys();
	}

	public Iterator getKeys(String arg0) {
		return this.conf.getKeys(arg0);
	}

	public List getList(String arg0) {
		return this.conf.getList(arg0);
	}

	public List getList(String arg0, List arg1) {
		return this.conf.getList(arg0, arg1);
	}

	public long getLong(String arg0) {
		return this.conf.getLong(arg0);
	}

	public long getLong(String arg0, long arg1) {
		return this.conf.getLong(arg0, arg1);
	}

	public Long getLong(String arg0, Long arg1) {
		return this.conf.getLong(arg0, arg1);
	}

	public Properties getProperties(String arg0) {
		return this.conf.getProperties(arg0);
	}

	public Object getProperty(String arg0) {
		return this.conf.getProperty(arg0);
	}

	public short getShort(String arg0) {
		return this.conf.getShort(arg0);
	}

	public short getShort(String arg0, short arg1) {
		return this.conf.getShort(arg0, arg1);
	}

	public Short getShort(String arg0, Short arg1) {
		return this.conf.getShort(arg0, arg1);
	}

	public String getString(String arg0) {
		return this.conf.getString(arg0);
	}

	public String getString(String arg0, String arg1) {
		return this.conf.getString(arg0, arg1);
	}

	public String[] getStringArray(String arg0) {
		return this.conf.getStringArray(arg0);
	}

	public boolean isEmpty() {
		return this.conf.isEmpty();
	}

	public org.apache.commons.configuration.Configuration subset(String arg0) {
		return this.conf.subset(arg0);
	}



	
}
