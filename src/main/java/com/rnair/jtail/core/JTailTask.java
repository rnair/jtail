package com.rnair.jtail.core;

import java.util.concurrent.Callable;

public interface JTailTask<TailedData> extends Callable<TailedData> {

}
