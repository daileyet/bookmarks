/**
 * 
 */
package com.openthinks.bookmarks.converter.json;

import com.openthinks.bookmarks.converter.AbstractOutput;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.support.JSONElement;

/**
 * @author dailey.yet@outlook.com
 *
 */
public class JsonOutput extends AbstractOutput {

	protected final JSONElement jsonElement;

	public JsonOutput(String jsonStr) {
		pureOutput = jsonStr;
		this.jsonElement = JSON.parse(pureOutput);
	}

	public final JSONElement getJsonElement() {
		return jsonElement;
	}

}
