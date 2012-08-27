package com.rnair.jtail.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.rnair.jtail.model.TailedData;

public class TaskRunner<T extends JTailTask<TailedData>> {

	protected ExecutorService taskService;

	protected static int defaultTasks = 2;

	public TaskRunner() {
		this(defaultTasks);
	}

	public TaskRunner(int tasks) {
		this.taskService = Executors.newFixedThreadPool(tasks);
	}

	public Future<TailedData> run(JTailTask<TailedData> jtailTask) {
		return this.taskService.submit(jtailTask);
	}
	
	public void stop(){
		this.taskService.shutdown();
	}
}
