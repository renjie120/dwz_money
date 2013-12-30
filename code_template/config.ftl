<#include "/com.renjie120.codegenerate.common.ftl">
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

<bean id="${nm}Manager"
	class="money.${nm}.${bignm}ManagerImpl">
	<constructor-arg index="0" ref="${nm}Dao"></constructor-arg>
</bean>


<bean id="${nm}Dao" parent="daoProxy">
	<property name="proxyInterfaces">
		<value>money.${nm}.${bignm}Dao</value>
	</property>
	<property name="target">
		<bean parent="baseDao">
			<constructor-arg value="money.${nm}.${bignm}VO">
			</constructor-arg>
		</bean>
	</property>
</bean>