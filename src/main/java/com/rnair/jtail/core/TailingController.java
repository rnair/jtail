package com.rnair.jtail.core;

import com.rnair.jtail.config.JTailConfiguration;

public interface TailingController {
	
	void initialize(JTailConfiguration config);
	
	void start();
	
	void stop();

}
