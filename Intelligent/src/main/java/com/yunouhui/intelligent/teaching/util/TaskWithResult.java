package com.yunouhui.intelligent.teaching.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;

public class TaskWithResult implements Callable<ConcurrentMap<String, Object>> {
	private String exeCommon;
	private JConlementService service; 

	public TaskWithResult(JConlementService service,String exeCommon) {
		this.service = service;
		this.exeCommon = exeCommon;
	}

	@Override
	public ConcurrentMap<String, Object> call() throws Exception {
		return service.push(exeCommon);
	}

}
