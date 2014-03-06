package com.renjie120.workflow;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;

import com.opensymphony.xwork2.ActionContext;
import common.base.SpringContextUtil;

import dwz.present.BaseAction;

/**
 *  
 *
 */
public class ProcessAction extends BaseAction {
	private String processType;
	private RepositoryService repositoryService;

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	private String processDefinitionId;
	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	/**
	 * 流程激活
	 * @return
	 */
	public String processActive() {
		repositoryService = (RepositoryService) SpringContextUtil
				.getBean("repositoryService");
		repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}
	
	/**
	 * 流程挂起
	 * @return
	 */
	public String processSuspend() {
		repositoryService = (RepositoryService) SpringContextUtil
				.getBean("repositoryService");
		repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
		return ajaxForwardSuccess(getText("msg.operation.success"));
	}

	
	/**
	 * 查询全部的流程列表
	 * 
	 * @return
	 */
	public String processList() {
		RepositoryService repositoryService = (RepositoryService) SpringContextUtil
				.getBean("repositoryService");
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;
		// 动态表单工作流onQuery query1 = repositoryService
//					.createProcessDefinitionQuery()
//					.processDefinitionKey("leave-dynamic-from").active()
//					.orderByDeploymentId().desc();
//			List<ProcessDefinition> list = query1.listPage(startIndex,
//					numPerPage);
//			ProcessDefinitionQuery query2 = repositoryService
//					.createProcessDefinitionQuery()
//					.processDefinitionKey("dispatch").active()
//					.orderByDeploymentId().desc();
//			List<ProcessDefinition> dispatchList = query2.listPage(startIndex,
//					numPerPage);
//			list.addAll(dispatchList);
//			ActionContext.getContext().put("list", list);
//			ActionContext.getContext().put("totalCount",
//					query1.count() + query2.count());流程列表
		if ("all".equals(processType)) {
//			ProcessDefiniti
		} else {
			// 读取所有流程
			ProcessDefinitionQuery query = repositoryService
					.createProcessDefinitionQuery().active()
					.orderByDeploymentId().desc();
			List<ProcessDefinition> list = query.list();
			ActionContext.getContext().put("list", list);
			ActionContext.getContext().put("totalCount", query.count());
		}

		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		return "allList";
	}

	/**
	 * 正在运行中的工作流.
	 * 
	 * @return
	 */
	public String processRunning() {
		RuntimeService runtimeService = (RuntimeService) SpringContextUtil
				.getBean("runtimeService");
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;

		// 动态表单工作流流程列表
		if ("all".equals(processType)) {
//			ProcessInstanceQuery leaveDynamicQuery = runtimeService
//					.createProcessInstanceQuery()
//					.processDefinitionKey("leave-dynamic-from")
//					.orderByProcessInstanceId().desc().active();
//			List<ProcessInstance> list = leaveDynamicQuery.listPage(startIndex,
//					numPerPage);
//			ProcessInstanceQuery dispatchQuery = runtimeService
//					.createProcessInstanceQuery()
//					.processDefinitionKey("dispatch").active()
//					.orderByProcessInstanceId().desc();
//			List<ProcessInstance> list2 = dispatchQuery.listPage(startIndex,
//					numPerPage);
//			list.addAll(list2);
//
//			ActionContext.getContext().put("list", list);
//			ActionContext.getContext().put("totalCount",
//					leaveDynamicQuery.count() + dispatchQuery.count());
		} else {
			ProcessInstanceQuery dynamicQuery = runtimeService
					.createProcessInstanceQuery().orderByProcessInstanceId()
					.desc().active();
			List<ProcessInstance> list = dynamicQuery.listPage(startIndex,
					numPerPage);

			ActionContext.getContext().put("list", list);
			ActionContext.getContext().put("totalCount", dynamicQuery.count());
		}

		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		return "runningList";
	}

	/**
	 * 已经结束的工作流.
	 * 
	 * @return
	 */
	public String processFinished() {
		HistoryService historyService = (HistoryService) SpringContextUtil
				.getBean("historyService");
		int pageNum = getPageNum();
		int numPerPage = getNumPerPage();
		int startIndex = (pageNum - 1) * numPerPage;

		// 动态表单工作流流程列表
		if ("all".equals(processType)) {
//			HistoricProcessInstanceQuery leaveDynamicQuery = historyService
//					.createHistoricProcessInstanceQuery()
//					.processDefinitionKey("leave-dynamic-from").finished()
//					.orderByProcessInstanceEndTime().desc();
//			List<HistoricProcessInstance> list = leaveDynamicQuery.listPage(startIndex,
//					numPerPage);
//			HistoricProcessInstanceQuery dispatchQuery = historyService
//					.createHistoricProcessInstanceQuery()
//					.processDefinitionKey("dispatch").finished()
//					.orderByProcessInstanceEndTime().desc();
//			List<HistoricProcessInstance> list2 = dispatchQuery.listPage(startIndex,
//					numPerPage);
//			list.addAll(list2);
//
//			ActionContext.getContext().put("list", list);
//			ActionContext.getContext().put("totalCount",
//					leaveDynamicQuery.count() + dispatchQuery.count());
		} else {
			HistoricProcessInstanceQuery dynamicQuery = historyService
					.createHistoricProcessInstanceQuery().finished()
					.orderByProcessInstanceEndTime().desc();
			List<HistoricProcessInstance> list = dynamicQuery.listPage(startIndex,
					numPerPage);

			ActionContext.getContext().put("list", list);
			ActionContext.getContext().put("totalCount", dynamicQuery.count());
		}

		ActionContext.getContext().put("pageNum", pageNum);
		ActionContext.getContext().put("numPerPage", numPerPage);
		return "historyList";
	}
}
