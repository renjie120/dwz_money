package com.renjie120.workflow;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import com.opensymphony.xwork2.ActionContext;

import dwz.present.BaseAction;

public class DeploymentAction extends BaseAction {
	private RepositoryService repositoryService;

	public String processList() {
		List<Object[]> objects = new ArrayList<Object[]>();
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		ProcessDefinitionQuery processDefinitionQuery = repositoryService
				.createProcessDefinitionQuery().orderByDeploymentId().desc();
		long count = processDefinitionQuery.count();
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
				.listPage(startIndex, numPerPage);
		for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery()
					.deploymentId(deploymentId).singleResult();
			objects.add(new Object[] { processDefinition, deployment });
		}

		ActionContext.getContext().put("list", objects);
		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		ActionContext.getContext().put("totalCount", count);
		return "list";
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
}
