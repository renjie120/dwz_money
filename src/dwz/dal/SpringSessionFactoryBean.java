package dwz.dal;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

public class SpringSessionFactoryBean extends LocalSessionFactoryBean {
	public SessionFactory getHibernateSessionFactory(){
		return this.getSessionFactory();
	}
}
