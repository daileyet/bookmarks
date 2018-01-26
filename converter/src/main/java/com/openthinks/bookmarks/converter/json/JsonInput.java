/**
 * 
 */
package com.openthinks.bookmarks.converter.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.openthinks.bookmarks.converter.AbstractInput;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class JsonInput extends AbstractInput {

	public JsonInput(File file) throws IOException {
		super(file);
	}

	public JsonInput(InputStream inputStream) throws IOException {
		super(inputStream);
	}

	public JsonInput(String strInput) {
		super(strInput);
	}

	
}
