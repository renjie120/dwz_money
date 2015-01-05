<#include "/com.renjie120.codegenerate.common.ftl">
\src\resource\struts-money.xml
<action name="${nm}" class="${model.packageName}.${bignm}Action">
	<result name="list">
		/WEB-INF/jsp/${nm}/${bignm}list.jsp
	</result>
	<result name="detail">
		/WEB-INF/jsp/${nm}/${bignm}info.jsp
	</result>
	<result name="editdetail">
		/WEB-INF/jsp/${nm}/${bignm}edit.jsp
	</result>
</action>

spring_manager_money.cfg.xml
<bean id="${nm}Manager"
	class="${model.packageName}.${bignm}ManagerImpl">
	<constructor-arg index="0" ref="${nm}Dao"></constructor-arg>
</bean>

\src\spring_dao_money.cfg.xml

<bean id="${nm}Dao" parent="daoProxy">
	<property name="proxyInterfaces">
		<value>${model.packageName}.${bignm}Dao</value>
	</property>
	<property name="target">
		<bean parent="baseDao">
			<constructor-arg value="${model.packageName}.${bignm}VO">
			</constructor-arg>
		</bean>
	</property>
</bean>

hibernate_mysql.cfg.xml
<value>
	hbm/${nm}.hbm.xml
</value>

/money/${nm}!query.do