package com.rnair.jtail.reader;

import java.io.File;

import com.rnair.jtail.core.TailEventCallback;

public interface ContentReader {

	void read(File file);

	void setTailEventCallback(TailEventCallback callback);
}
