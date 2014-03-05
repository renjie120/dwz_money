package com.renjie120.hello;
 

import java.util.List;

import org.activiti.engine.HistoryService; 
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.renjie120.hello.UserBean;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"spring_activiti.cfg.xml");
		RepositoryService repositoryService = (RepositoryService) applicationContext
				.getBean("repositoryService"); 
		// String deploymentId = repositoryService.createDeployment()
		// .addClasspathResource("hello.bpmn20.xml").deploy().getId();

		UserBean userBean = (UserBean) applicationContext.getBean("userBean");
		userBean.hello();
		HistoryService historyService = (HistoryService) applicationContext
				.getBean("historyService");

		/**
		 * 查询已经审批完成的流程.
		 */
		List<HistoricProcessInstance> historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery().list();
		int i = 0;
		for (HistoricProcessInstance ins : historicProcessInstance)
			System.out.println("Process" + (i++) + " instance end time: "
					+ ins.getEndTime());
	}
}
