package com.renjie120.workflow;

import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class ActivitiTest extends
		AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/activiti_spring.cfg.xml" };
		return config;
	}

	public void testDeployList() {
		RepositoryService repositoryService = (RepositoryService) applicationContext
				.getBean("repositoryService");
		FormService formService = (FormService) applicationContext
				.getBean("formService");
		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc();
		long count = processDefinitionQuery.count();
		System.out.println("已经发布的流程：" + count);
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
				.listPage(0, Integer.MAX_VALUE);
		for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			System.out.println("deploymentId:" + deploymentId+",processDefinition.getId()="+processDefinition
					.getId());
			StartFormData data = formService.getStartFormData(processDefinition
					.getId());
			System.out.println("data.getFormKey()-" + data.getFormKey());
			List<FormProperty> dataes = data.getFormProperties();
			for (FormProperty f : dataes) {
				System.out.println(f.getId() + "," + f.getName());
			}
			System.out.println("--------------------");
			// System.out.println(json.toString());
		}
	}
}
