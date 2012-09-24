package com.rnair.jtail.reader;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;

import com.rnair.jtail.JTailErrorCode;
import com.rnair.jtail.JTailException;
import com.rnair.jtail.config.JTailConfiguration;
import com.rnair.jtail.core.TailEventCallback;
import com.rnair.jtail.model.TailedData;

public class TailedContentReader implements ContentReader {

	protected JTailConfiguration tailConfig;

	protected TailEventCallback tailEvent;

	private static char newLine = System.getProperty("line.separator")
			.charAt(0);

	private static long defaultShowLines = 10;

	public TailedContentReader(JTailConfiguration config) {
		this.tailConfig = config;
	}

	public void read(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			long showLineCount = this.tailConfig.getShowLines() > 0 ? this.tailConfig
					.getShowLines() : defaultShowLines;
			if (showLineCount > 0) {
				FilePointer fp = this.getFilePointer(file, showLineCount);
				long currentPtr = fp.getCurrent();
				br.skip(currentPtr);
			}
			while (this.tailConfig.isForce() || showLineCount-- > 0) {
				String line = br.readLine();
				if (line == null) {
					Thread.sleep(200);
					continue;
				}
				TailedData data = new TailedData();
				data.setContent(line);
				this.tailEvent.onTailing(data);
			}
		} catch (Throwable t) {
			throw new JTailException(JTailErrorCode.FILE_READ_ERROR,
					"Error occured while reading", t);
		}

	}

	private FilePointer getFilePointer(File file, long showLineCount) {
		InputStream is = null;
		long count = 0;
		try {
			is = new BufferedInputStream(new FileInputStream(file));
			long[] lineChars = new long[(int) showLineCount + 1];
			byte[] c = new byte[1024];
			int index = 0;
			int readChars = 0;
			long totalCharsRead = 0;
			while ((readChars = is.read(c)) != -1) {
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == newLine) {
						++count;
						if (index == lineChars.length)
							index = 0;
						lineChars[index++] = totalCharsRead + i + 1;
					}
				}
				totalCharsRead += readChars;
			}
			if (count >= showLineCount) {
				return new FilePointer(
						(index == lineChars.length) ? lineChars[0]
								: lineChars[index]);
			}
		} catch (Exception e) {
			throw new JTailException(JTailErrorCode.FILE_READ_ERROR,
					"Error occured while reading", e);
		}
		return new FilePointer(0);
	}

	private static class FilePointer {

		long current = 0;

		public FilePointer(long current) {
			this.current = current;

		}

		public long getCurrent() {
			return this.current;
		}
	}

	public void setTailEventCallback(TailEventCallback callback) {
		this.tailEvent = callback;

	}
}
