/**
 * 
 */
package com.openthinks.bookmarks.converter.json;

import com.openthinks.bookmarks.converter.Converter;
import com.openthinks.bookmarks.converter.Input;
import com.openthinks.bookmarks.converter.Output;
import com.openthinks.libs.utilities.Checker;
import com.openthinks.libs.utilities.json.JSON;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class JsonConverter implements Converter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.openthinks.bookmarks.converter.Converter#convert(com.openthinks.bookmarks
	 * .converter.Input)
	 */
	@Override
	public Output convert(Input input) {
		Checker.require(input).notNull();
		if (input.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return new JsonOutput(JSON.parse(input.asText()));
	}

}
