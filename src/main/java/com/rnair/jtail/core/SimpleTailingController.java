package com.rnair.jtail.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import com.rnair.jtail.JTailErrorCode;
import com.rnair.jtail.JTailException;
import com.rnair.jtail.config.JTailConfiguration;
import com.rnair.jtail.config.TailTaskConfiguration;
import com.rnair.jtail.model.TailedData;
import com.rnair.jtail.reader.ContentReader;
import com.rnair.jtail.reader.ContentReaderFactory;

public class SimpleTailingController implements TailingController {

	protected TaskRunner<JTailTask<TailedData>> taskRunner;

	protected ContentReader contentReader;

	protected TailEventCallback eventCallBack;

	private Future<TailedData> tailTaskFuture;

	private Future<TailedData> printTaskFuture;

	public SimpleTailingController() {

	}

	public void start() {

		TailTaskConfiguration taskConfig = new TailTaskConfiguration();
		taskConfig.setContentReader(this.contentReader);
		taskConfig.setEventCallback(this.eventCallBack);

		JTailTask<TailedData> tailTask = new TailTask<TailedData>(taskConfig);
		JTailTask<TailedData> printTask = new PrintTask<TailedData>(taskConfig);
		this.tailTaskFuture = this.taskRunner.run(tailTask);
		this.printTaskFuture = this.taskRunner.run(printTask);

	}

	public void stop() {
		this.tailTaskFuture.cancel(true);
		this.printTaskFuture.cancel(true);
	}

	public void initialize(JTailConfiguration config) {
		this.taskRunner = new TaskRunner<JTailTask<TailedData>>();
		this.contentReader = ContentReaderFactory.get(null);
		this.eventCallBack = new TailEventCallback() {
			private BlockingQueue<TailedData> queue = new LinkedBlockingQueue<TailedData>();

			public void onTailing(TailedData data) {
				queue.offer(data);
			}

			public TailedData poll() {
				TailedData tailedData = null;
				try {
					tailedData = queue.take();
					if (tailedData != null) {
						return tailedData;
					}
				} catch (InterruptedException e) {
					throw new JTailException(JTailErrorCode.POLL_ERROR,
							"Could not take tailed data from the queue", e);
				}
				return null;
			}
		};
	}

}
