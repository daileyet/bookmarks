/**
 * 
 */
package com.openthinks.bookmarks.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class AbstractInput implements Input {
	private final StringBuffer content = new StringBuffer();

	public AbstractInput() {

	}

	public AbstractInput(String strInput) {
		content.append(strInput);
	}

	public AbstractInput(InputStream inputStream) throws IOException {
		readFromStream(inputStream);
	}

	public AbstractInput(File file) throws IOException {
		this(new FileInputStream(file));
	}

	private void readFromStream(InputStream inputStream) throws IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.openthinks.bookmarks.converter.Input#asText()
	 */
	@Override
	public String asText() {
		return content.toString();
	}

	@Override
	public boolean isEmpty() {
		return content==null || content.length()==0;
	}

}
