package com.rnair.jtail.core;

import com.rnair.jtail.model.TailedData;

public interface TailEventCallback {

	void onTailing(TailedData data);

	TailedData poll();
}
