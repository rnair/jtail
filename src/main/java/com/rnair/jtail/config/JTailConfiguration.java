package com.rnair.jtail.config;

public class JTailConfiguration {

	private String file;

	private boolean force;

	private long showLines;

	private JTailConfiguration() {
		super();
	}
	
	/**
	 * static factory method for JTailConfiguration
	 * @param args CLI
	 */
	public static JTailConfiguration of(String... args) {
		JTailConfiguration config = new JTailConfiguration();
		for (String arg : args) {
			if (arg.equalsIgnoreCase("-f")) {
				config.setForce(true);
			}
			if (arg.startsWith("-n")) {
				String showLineArg = arg;
				String showlines = showLineArg.substring(2);
				config.setShowLines(Long.parseLong(showlines));
			}
		}
		config.setFile(args[2]);
		return config;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String files) {
		this.file = files;
	}

	public boolean isForce() {
		return force;
	}

	public void setForce(boolean force) {
		this.force = force;
	}

	public long getShowLines() {
		return showLines;
	}

	public void setShowLines(long showLines) {
		this.showLines = showLines;
	}

}
