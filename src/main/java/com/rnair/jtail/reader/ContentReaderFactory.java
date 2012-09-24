package com.rnair.jtail.reader;

import com.rnair.jtail.config.JTailConfiguration;

public abstract class ContentReaderFactory {

	public static ContentReader get(JTailConfiguration config){ 
		//TODO:JTail can be enhanced for random access reading,less etc...So that 
		//time we can use different readers with configs.These enhancements will 
		//start with JTailConfiguration.
		return new TailedContentReader(config);
	}
}
