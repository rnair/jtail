package com.rnair.jtail.core;

import com.rnair.jtail.config.JTailConfiguration;

public interface TailingController {

	/**
	 * Intialize the controller with {@link JTailConfiguration}.This method is
	 * responsible to setup the infrastructure for tailing.
	 * 
	 * @param config
	 *            JTailConfiguration
	 */
	void initialize(JTailConfiguration config);

	/**
	 * start the controller
	 */
	void start();

	/**
	 * stop the controller
	 */
	void stop();

}
