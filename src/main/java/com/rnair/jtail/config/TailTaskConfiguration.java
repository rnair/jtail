package com.rnair.jtail.config;

import com.rnair.jtail.core.TailEventCallback;
import com.rnair.jtail.reader.ContentReader;

public class TailTaskConfiguration {

	private String fileUrl;
	
	private String taskName;
	
	private TailEventCallback eventCallback;
	
	private ContentReader contentReader;
	
	public TailTaskConfiguration(){}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public TailEventCallback getEventCallback() {
		return eventCallback;
	}

	public void setEventCallback(TailEventCallback eventCallback) {
		this.eventCallback = eventCallback;
	}

	public ContentReader getContentReader() {
		return contentReader;
	}

	public void setContentReader(ContentReader contentReader) {
		this.contentReader = contentReader;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	
}
