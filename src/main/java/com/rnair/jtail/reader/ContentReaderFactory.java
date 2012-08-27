package com.rnair.jtail.reader;

import com.rnair.jtail.config.JTailConfiguration;

public abstract class ContentReaderFactory {

	public static ContentReader get(JTailConfiguration config){ 

		return new RandomAccessContentReader(config);
	}
}
