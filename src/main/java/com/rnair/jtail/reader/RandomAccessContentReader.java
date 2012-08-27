package com.rnair.jtail.reader;

import java.io.File;
import java.io.RandomAccessFile;

import com.rnair.jtail.config.JTailConfiguration;
import com.rnair.jtail.core.TailEventCallback;
import com.rnair.jtail.model.TailedData;

public class RandomAccessContentReader implements ContentReader {

	protected JTailConfiguration tailConfig;

	protected TailEventCallback tailEvent;

	private static char newLine = System.getProperty("line.separator")
			.charAt(0);

	private static long showLines = 10;

	public RandomAccessContentReader(JTailConfiguration config) {
		this.tailConfig = config;
	}

	public void read(File file) {
		try {
			RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
			long fileLength = file.length() - 1;
			StringBuilder sb = new StringBuilder();
			int line = 0;

			for (long filePointer = fileLength; filePointer != -1; filePointer--) {
				fileHandler.seek(filePointer);
				int readByte = fileHandler.readByte();

				if (readByte == newLine) {
					if (line == showLines) {
						if (filePointer == fileLength) {
							continue;
						} else {
							break;
						}
					}
				} else if (readByte == 0xD) {
					line = line + 1;
					if (line == showLines) {
						if (filePointer == fileLength - 1) {
							continue;
						} else {
							break;
						}
					}
				}
				sb.append((char) readByte);
			}

			sb.deleteCharAt(sb.length() - 1);
			String lastLine = sb.reverse().toString();
			TailedData tailedData = new TailedData();
			tailedData.setContent(lastLine);
			this.tailEvent.onTailing(tailedData);
		} catch (java.io.FileNotFoundException e) {

		} catch (java.io.IOException e) {

		}
	}

	public void setTailEventCallback(TailEventCallback callback) {
		this.tailEvent = callback;

	}
}
