/**
 * 
 */
package com.openthinks.bookmarks.converter.xhtml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.openthinks.bookmarks.converter.AbstractInput;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class XhtmlInput extends AbstractInput {

	public XhtmlInput(File file) throws IOException {
		super(file);
	}

	public XhtmlInput(InputStream inputStream) throws IOException {
		super(inputStream);
	}

	public XhtmlInput(String strInput) {
		super(strInput);
	}


}
