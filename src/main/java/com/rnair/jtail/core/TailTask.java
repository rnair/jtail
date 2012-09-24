package com.rnair.jtail.core;

import java.io.File;

import com.rnair.jtail.config.TailTaskConfiguration;
import com.rnair.jtail.model.TailedData;
import com.rnair.jtail.reader.ContentReader;

public class TailTask<T extends TailedData> implements JTailTask<T> {

	protected TailTaskConfiguration taskConfig;

	public TailTask(TailTaskConfiguration taskConfig) {
		this.taskConfig = taskConfig;
	}

	public T call() throws Exception {
		ContentReader contentReader = this.taskConfig.getContentReader();
		contentReader.setTailEventCallback(this.taskConfig.getEventCallback());
		contentReader.read(new File(this.taskConfig.getFileUrl()));
		return null;
	}

	public void setTailTaskConfig(TailTaskConfiguration config) {
		this.taskConfig = config;
		
	}

}
