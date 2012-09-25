package com.rnair.jtail.core;

import java.io.PrintStream;

import com.rnair.jtail.config.TailTaskConfiguration;
import com.rnair.jtail.model.TailedData;

public class PrintTask<T extends TailedData> implements JTailTask<T> {

	protected TailTaskConfiguration taskConfig;

	protected PrintStream ps;

	public PrintTask(){}

	public T call() throws Exception {
		
		TailedData tailedData = null;
		while ((tailedData = this.taskConfig.getEventCallback().poll()) != null) {
			System.out.println(tailedData.getContent());
		}
		return null;
	}

	public void setTailTaskConfig(TailTaskConfiguration config) {
		this.taskConfig = config;
	}

}
