package com.rnair.jtail.core;

import java.util.concurrent.Callable;

import com.rnair.jtail.config.TailTaskConfiguration;

public interface JTailTask<TailedData> extends Callable<TailedData> {

	void setTailTaskConfig(TailTaskConfiguration config);
}
