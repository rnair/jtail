package com.rnair.jtail.config;

public class JTailConfiguration {

	protected String[] files;
	
	public JTailConfiguration(){
		super();
	}
	
	public static JTailConfiguration build(String...args){
		return null;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}
	
	
}
