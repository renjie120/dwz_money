package com.renjie120.hello;

import org.activiti.engine.RuntimeService;
import org.springframework.transaction.annotation.Transactional;

public class UserBean {

	/** 由Spring注入 */
	private RuntimeService runtimeService;

	@Transactional
	public void hello() {
		// 这里，你可以在你们的领域模型中做一些事物处理。
		// 当在调用Activiti RuntimeService的startProcessInstanceByKey方法时，
		// 它将会结合到同一个事物中。
		runtimeService.startProcessInstanceByKey("helloProcess");

		System.out.println("运行中的流程数量: "
				+ runtimeService.createProcessInstanceQuery().count());

	}

	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}

}
