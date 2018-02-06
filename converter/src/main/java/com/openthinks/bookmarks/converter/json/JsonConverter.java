/**
 * 
 */
package com.openthinks.bookmarks.converter.json;

import com.openthinks.bookmarks.converter.Converter;
import com.openthinks.bookmarks.converter.Input;
import com.openthinks.libs.utilities.Checker;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class JsonConverter implements Converter<JsonOutput> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.openthinks.bookmarks.converter.Converter#convert(com.openthinks.bookmarks
	 * .converter.Input)
	 */
	@Override
	public JsonOutput convert(Input input) {
		Checker.require(input).notNull();
		if (input.isEmpty()) {
			throw new IllegalArgumentException();
		}
		return new JsonOutput(input.asText());
	}

}
