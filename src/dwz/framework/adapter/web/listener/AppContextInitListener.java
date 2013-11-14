package dwz.framework.adapter.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dwz.framework.config.Configuration;
import dwz.framework.core.factory.BusinessFactory;

public class AppContextInitListener implements ServletContextListener,
		HttpSessionListener {

	private static final String	ETC_FILE	= "etc_file";

	public AppContextInitListener() {

	}

	public void contextDestroyed(ServletContextEvent event) {
		BusinessFactory factory = BusinessFactory.getFactory();
		Configuration config = factory.retrieveConfiguration();
		config.clear();
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String configFile = context.getInitParameter(ETC_FILE);
		Configuration config = new Configuration(
				new ClassPathXmlApplicationContext("spring.cfg.xml"));
		try {
			config.load(context.getRealPath(configFile));
		} catch (ConfigurationException e) {
			event.getServletContext().log("ConfigurationException: ", e);
		}

	}

	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		session.invalidate();
	}

}
